public class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int totalPoints = n + k - 1;
        int chosenPoints = 2 * k;

        // If we need to choose more points than available, it's impossible
        if (chosenPoints > totalPoints) {
            return 0;
        }

        // DP table to compute combinations: dp[i][j] represents iCj
        int[][] dp = new int[totalPoints + 1][chosenPoints + 1];

        for (int i = 0; i <= totalPoints; i++) {
            dp[i][0] = 1; // nC0 is always 1
            for (int j = 1; j <= Math.min(i, chosenPoints); j++) {
                // Pascal's Identity: nCr = (n-1)Cr-1 + (n-1)Cr
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }

        return dp[totalPoints][chosenPoints];
    }
}
