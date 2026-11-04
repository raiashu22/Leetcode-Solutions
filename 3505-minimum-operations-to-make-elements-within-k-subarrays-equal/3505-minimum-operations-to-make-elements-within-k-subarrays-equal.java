
import java.util.*;

class Solution {

    class Fenwick {
        int n;
        long[] count;
        long[] sum;

        Fenwick(int n) {
            this.n = n;
            count = new long[n + 1];
            sum = new long[n + 1];
        }

        void update(int i, int val, int delta) {
            while (i <= n) {
                count[i] += delta;
                sum[i] += (long) val * delta;
                i += i & -i;
            }
        }

        long queryCount(int i) {
            long ans = 0;

            while (i > 0) {
                ans += count[i];
                i -= i & -i;
            }

            return ans;
        }

        long querySum(int i) {
            long ans = 0;

            while (i > 0) {
                ans += sum[i];
                i -= i & -i;
            }

            return ans;
        }

        // Find the index of the kth smallest element.
        int kth(long k) {
            int idx = 0;

            for (int bit = Integer.highestOneBit(n);
                 bit > 0; bit >>= 1) {

                int next = idx + bit;

                if (next <= n && count[next] < k) {
                    idx = next;
                    k -= count[next];
                }
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] values = Arrays.stream(sorted)
                .distinct()
                .toArray();

        int m = values.length;

        // Map every element to its compressed index
        int[] id = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = Arrays.binarySearch(values, nums[i]) + 1;
        }

        Fenwick bit = new Fenwick(m);

        // Calculate cost of every window of size x
        int windows = n - x + 1;
        long[] cost = new long[windows];

        for (int i = 0; i < n; i++) {

            bit.update(id[i], nums[i], 1);

            // Remove element outside the window
            if (i >= x) {
                bit.update(id[i - x], nums[i - x], -1);
            }

            if (i >= x - 1) {

                // Lower median
                long medianRank = (x + 1L) / 2;

                int medianIndex = bit.kth(medianRank);
                long median = values[medianIndex - 1];

                long leftCount =
                        bit.queryCount(medianIndex);

                long leftSum =
                        bit.querySum(medianIndex);

                long totalSum =
                        bit.querySum(m);

                long rightCount = x - leftCount;
                long rightSum = totalSum - leftSum;

                long leftCost =
                        median * leftCount - leftSum;

                long rightCost =
                        rightSum - median * rightCount;

                cost[i - x + 1] =
                        leftCost + rightCost;
            }
        }

        // Dynamic Programming with space optimization
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        // prev[i] = 0 for selecting zero subarrays
        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Do not select a window ending at i-1
                curr[i] = curr[i - 1];

                // Select a window of size x
                if (i >= x && prev[i - x] != INF) {

                    long take =
                            prev[i - x] + cost[i - x];

                    curr[i] = Math.min(curr[i], take);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
