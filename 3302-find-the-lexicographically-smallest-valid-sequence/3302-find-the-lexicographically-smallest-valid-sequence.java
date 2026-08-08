import java.util.Arrays;

public class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        // suffix[i] stores the max length of a suffix of word2 
        // that matches a subsequence of word1[i...]
        int[] suffix = new int[n + 1];
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suffix[i] = m - 1 - j;
        }
        
        int[] ans = new int[m];
        int matchCount = 0;
        boolean changed = false;
        
        for (int i = 0; i < n; i++) {
            if (matchCount == m) {
                break;
            }
            
            // Case 1: Perfect character match
            if (word1.charAt(i) == word2.charAt(matchCount)) {
                ans[matchCount] = i;
                matchCount++;
            } 
            // Case 2: Mismatch, check if we can safely use our 1 character change here
            else if (!changed && suffix[i + 1] >= m - 1 - matchCount) {
                ans[matchCount] = i;
                matchCount++;
                changed = true;
            }
        }
        
        // If we matched the entire word2, return the sequence; otherwise return an empty array
        return matchCount == m ? ans : new int[0];
    }
}
