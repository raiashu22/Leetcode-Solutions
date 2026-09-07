class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int n = s.length();
        
        // dp[i] stores the number of distinct subsequences using the first i characters
        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case: 1 empty subsequence ""
        
        // Track the last seen position of each character 'a' through 'z'
        int[] last = new int[26];
        java.util.Arrays.fill(last, -1);
        
        for (int i = 0; i < n; i++) {
            int charIndex = s.charAt(i) - 'a';
            
            // Double the number of subsequences formed so far
            dp[i + 1] = (dp[i] * 2) % MOD;
            
            // If this character has appeared before, remove duplicate counts
            if (last[charIndex] != -1) {
                dp[i + 1] = (dp[i + 1] - dp[last[charIndex]] + MOD) % MOD;
            }
            
            // Record the current position as the last seen index for this character
            last[charIndex] = i;
        }
        
        // Subtract 1 to exclude the empty subsequence ""
        return (dp[n] - 1 + MOD) % MOD;
    }
}
