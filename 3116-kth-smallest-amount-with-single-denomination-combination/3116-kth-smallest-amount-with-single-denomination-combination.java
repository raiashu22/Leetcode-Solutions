import java.util.Arrays;

public class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        // The maximum possible answer is the minimum coin value multiplied by k
        long minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }
        long high = minCoin * k;
        long ans = high;

        // Binary search for the kth smallest amount
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countAmounts(mid, coins) >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1;  // Increase the search range
            }
        }
        return ans;
    }

    // Counts how many multiples exist <= maxVal using Inclusion-Exclusion Principle
    private long countAmounts(long maxVal, int[] coins) {
        long totalCount = 0;
        int n = coins.length;
        int totalSubsets = 1 << n; // 2^n subsets

        // Iterate through all possible non-empty subsets of coins
        for (int i = 1; i < totalSubsets; i++) {
            long lcmVal = 1;
            int bitsCount = 0;
            boolean overflow = false;

            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    bitsCount++;
                    lcmVal = lcm(lcmVal, coins[j]);
                    // If LCM exceeds maxVal, its contribution to count will be 0
                    if (lcmVal > maxVal) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            // Inclusion-Exclusion logic
            if (bitsCount % 2 == 1) {
                totalCount += maxVal / lcmVal;
            } else {
                totalCount -= maxVal / lcmVal;
            }
        }
        return totalCount;
    }

    // Helper method to find Great Common Divisor (GCD)
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Helper method to find Least Common Multiple (LCM)
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
