import java.util.Arrays;

public class Solution {
    private int[][] memo;
    private int[] prefixSum;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n][n];
        // Initialize memoization table with -1
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Precompute prefix sums for O(1) subarray sum queries
        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stoneValue[i];
        }

        return solve(0, n - 1);
    }

    private int solve(int i, int j) {
        // Base case: only one stone left, no points can be scored
        if (i == j) {
            return 0;
        }

        // Return cached result if already calculated
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int maxScore = 0;

        // Try all possible split points k where the left row is [i...k] and right row is [k+1...j]
        for (int k = i; k < j; k++) {
            int leftSum = prefixSum[k + 1] - prefixSum[i];
            int rightSum = prefixSum[j + 1] - prefixSum[k + 1];

            if (leftSum < rightSum) {
                // Bob throws away the right row
                maxScore = Math.max(maxScore, leftSum + solve(i, k));
            } else if (leftSum > rightSum) {
                // Bob throws away the left row
                maxScore = Math.max(maxScore, rightSum + solve(k + 1, j));
            } else {
                // Sums are equal; Alice chooses the optimal row to keep
                int keepLeft = leftSum + solve(i, k);
                int keepRight = rightSum + solve(k + 1, j);
                maxScore = Math.max(maxScore, Math.max(keepLeft, keepRight));
            }
        }

        return memo[i][j] = maxScore;
    }
}
