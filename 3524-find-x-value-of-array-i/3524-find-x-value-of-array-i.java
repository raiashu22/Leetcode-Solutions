class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int val = num % k;

            // Start a new subarray at current element
            nextDp[val]++;

            // Extend existing subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nextRem = (r * val) % k;
                    nextDp[nextRem] += dp[r];
                }
            }

            dp = nextDp;

            // Accumulate counts to total answer
            for (int r = 0; r < k; r++) {
                ans[r] += dp[r];
            }
        }

        return ans;
    }
}