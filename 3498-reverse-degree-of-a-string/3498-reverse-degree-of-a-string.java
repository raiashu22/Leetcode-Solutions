class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 'a' becomes 26, 'b' becomes 25, ..., 'z' becomes 1
            int revAlphabetIndex = 26 - (ch - 'a');
            // 1-indexed position in the string
            int stringIndex = i + 1;
            
            totalSum += revAlphabetIndex * stringIndex;
        }
        
        return totalSum;
    }
}
