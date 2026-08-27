import java.util.Arrays;

public class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] counts = new int[26];
        for (int i = 0; i < n; i++) {
            counts[s.charAt(i) - 'a']++;
        }

        // Try to find the longest common prefix match
        int[] currentCounts = counts.clone();
        int matchLen = 0;
        
        while (matchLen < n) {
            int charIdx = target.charAt(matchLen) - 'a';
            if (currentCounts[charIdx] > 0) {
                currentCounts[charIdx]--;
                matchLen++;
            } else {
                break;
            }
        }

        // Backtrack from the longest match length to find a valid larger permutation
        for (int i = matchLen; i >= 0; i--) {
            // If we are at index i, it means the prefix of length i matches target exactly.
            // We want to make the character at index i strictly greater than target.charAt(i).
            int targetCharIdx = (i < n) ? (target.charAt(i) - 'a') : -1;

            // Look for the smallest available character greater than targetCharIdx
            for (int c = targetCharIdx + 1; c < 26; c++) {
                if (currentCounts[c] > 0) {
                    // Found a valid character to place at position i!
                    StringBuilder sb = new StringBuilder();
                    
                    // 1. Append the matching prefix
                    sb.append(target.substring(0, i));
                    
                    // 2. Append the strictly greater character
                    sb.append((char) ('a' + c));
                    currentCounts[c]--;
                    
                    // 3. Append the remaining available characters in sorted order
                    for (int j = 0; j < 26; j++) {
                        while (currentCounts[j] > 0) {
                            sb.append((char) ('a' + j));
                            currentCounts[j]--;
                        }
                    }
                    return sb.toString();
                }
            }

            // If we can't find a greater character at position i, 
            // we must backtrack by expanding the pool of available characters.
            if (i > 0) {
                int prevCharIdx = target.charAt(i - 1) - 'a';
                currentCounts[prevCharIdx]++;
            }
        }

        return "";
    }
}
