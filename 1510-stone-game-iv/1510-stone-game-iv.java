class Solution {
    public boolean winnerSquareGame(int n) {
        // dp[i] is true if the player facing i stones can force a win
        boolean[] dp = new boolean[n + 1];
        
        for (int i = 1; i <= n; i++) {
            // Try removing every possible perfect square j*j
            for (int j = 1; j * j <= i; j++) {
                // If the remaining state is a losing state for the next player,
                // the current player can force a win.
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break; // No need to check further squares for this i
                }
            }
        }
        
        return dp[n];
    }
}
