class Solution {
    public int minOperations(String s) {
        int n = s.length();
        if (n == 2) {
            return s.charAt(0) > s.charAt(1) ? -1 : 0;
        }
        
        // Check if already sorted
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;
        
        char first = s.charAt(0);
        char last = s.charAt(n - 1);
        char minChar = 'z';
        char maxChar = 'a';
        
        for (int i = 1; i < n - 1; i++) {
            char c = s.charAt(i);
            minChar = (char) Math.min(minChar, c);
            maxChar = (char) Math.max(maxChar, c);
        }
        
        // 1 operation check
        if ((first <= minChar && first <= last) || (last >= maxChar && last >= first)) {
            return 1;
        }
        
        // 3 operations check
        if (maxChar < first && minChar > last) {
            return 3;
        }
        
        // Otherwise, 2 operations
        return 2;
    }
}