import java.util.Arrays;

public class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }

        // Step 1: Validate if a palindrome can be formed
        int oddCount = 0;
        char oddChar = 0;
        for (int i = 0; i < 26; i++) {
            if (counts[i] % 2 != 0) {
                oddCount++;
                oddChar = (char) ('a' + i);
            }
        }
        if (oddCount > 1) {
            return "";
        }

        // Step 2: Build the pool for the first half
        int[] halfPool = new int[26];
        for (int i = 0; i < 26; i++) {
            halfPool[i] = counts[i] / 2;
        }
        int halfLen = n / 2;

        String bestPal = null;

        // Step 3: Try all possible prefix match lengths with target's first half
        for (int matchLen = halfLen; matchLen >= 0; matchLen--) {
            int[] currentPool = Arrays.copyOf(halfPool, 26);
            char[] prefix = new char[halfLen];
            boolean possible = true;

            // Try to perfectly match the target prefix up to matchLen
            for (int i = 0; i < matchLen; i++) {
                char tChar = target.charAt(i);
                if (currentPool[tChar - 'a'] > 0) {
                    currentPool[tChar - 'a']--;
                    prefix[i] = tChar;
                } else {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                continue;
            }

            // Case A: Perfect match for the entire first half
            if (matchLen == halfLen) {
                String candidate = rebuild(prefix, oddCount > 0, oddChar);
                if (candidate.compareTo(target) > 0) {
                    if (bestPal == null || candidate.compareTo(bestPal) < 0) {
                        bestPal = candidate;
                    }
                }
                continue;
            }

            // Case B: Deviate at position 'matchLen' to a strictly larger character
            char targetChar = target.charAt(matchLen);
            boolean foundLarger = false;

            for (int i = (targetChar - 'a') + 1; i < 26; i++) {
                if (currentPool[i] > 0) {
                    currentPool[i]--;
                    prefix[matchLen] = (char) ('a' + i);
                    foundLarger = true;
                    break;
                }
            }

            if (!foundLarger) {
                continue;
            }

            // Fill the rest of the half string with the smallest available characters
            int idx = matchLen + 1;
            for (int i = 0; i < 26; i++) {
                while (currentPool[i] > 0) {
                    prefix[idx++] = (char) ('a' + i);
                    currentPool[i]--;
                }
            }

            String candidate = rebuild(prefix, oddCount > 0, oddChar);
            if (candidate.compareTo(target) > 0) {
                if (bestPal == null || candidate.compareTo(bestPal) < 0) {
                    bestPal = candidate;
                }
            }
        }

        return bestPal == null ? "" : bestPal;
    }

    // Helper method to reconstruct the full palindrome from the first half
    private String rebuild(char[] half, boolean hasOdd, char oddChar) {
        StringBuilder sb = new StringBuilder(new String(half));
        String firstHalf = sb.toString();
        if (hasOdd) {
            sb.append(oddChar);
        }
        sb.append(new StringBuilder(firstHalf).reverse());
        return sb.toString();
    }
}
