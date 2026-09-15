class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int start = 0; // Tracks the start boundary for the next valid substring
        
        for (int i = 0; i < n; i++) {
            // 1. Check for a palindrome of length k ending at index i
            if (i - start + 1 >= k && isPalindrome(s, i - k + 1, i)) {
                count++;
                start = i + 1; // Move the boundary past the current palindrome
            } 
            // 2. Check for a palindrome of length k + 1 ending at index i
            else if (i - start + 1 >= k + 1 && isPalindrome(s, i - k, i)) {
                count++;
                start = i + 1; // Move the boundary past the current palindrome
            }
        }
        
        return count;
    }
    
    // Helper method to check if a substring is a palindrome
    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
