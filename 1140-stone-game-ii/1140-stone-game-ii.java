import java.util.HashMap;
import java.util.Map;

class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n + 1];
        
        // Calculate suffix sums to quickly get total remaining stones
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        // Use a 2D array for fast memoization cache
        // i can go from 0 to n, M can scale up to n
        int[][] memo = new int[n][n + 1];
        
        return dp(0, 1, suffixSum, memo, n);
    }
    
    private int dp(int i, int m, int[] suffixSum, int[][] memo, int n) {
        // Base case: If the current player can take all remaining piles
        if (i + 2 * m >= n) {
            return suffixSum[i];
        }
        
        // Return cached result if already calculated
        if (memo[i][m] > 0) {
            return memo[i][m];
        }
        
        int minOpponentStones = Integer.MAX_VALUE;
        
        // Try taking X piles where 1 <= X <= 2M
        for (int x = 1; x <= 2 * m; x++) {
            int nextM = Math.max(m, x);
            int opponentStones = dp(i + x, nextM, suffixSum, memo, n);
            minOpponentStones = Math.min(minOpponentStones, opponentStones);
        }
        
        // Current player's score is the total remaining stones minus the opponent's best score
        memo[i][m] = suffixSum[i] - minOpponentStones;
        return memo[i][m];
    }
}
