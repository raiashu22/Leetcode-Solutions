class Solution {
    public int maximumLengthSubstring(String s) {
        int[] charCounts = new int[26];
        int maxLen = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            // Map character to index 0-25 and increment count
            int rightCharIdx = s.charAt(right) - 'a';
            charCounts[rightCharIdx]++;
            
            // Shrink window from the left if current character count exceeds 2
            while (charCounts[rightCharIdx] > 2) {
                int leftCharIdx = s.charAt(left) - 'a';
                charCounts[leftCharIdx]--;
                left++;
            }
            
            // Calculate and update the maximum length
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}
