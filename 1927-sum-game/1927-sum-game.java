class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;
        
        int leftSum = 0;
        int leftQ = 0;
        int rightSum = 0;
        int rightQ = 0;
        
        // Process the first half
        for (int i = 0; i < half; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                leftQ++;
            } else {
                leftSum += c - '0'; // Convert char to int
            }
        }
        
        // Process the second half
        for (int i = half; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                rightQ++;
            } else {
                rightSum += c - '0'; // Convert char to int
            }
        }
        
        // Bob wins if: 2 * (leftSum - rightSum) == 9 * (rightQ - leftQ)
        // If Bob wins, Alice loses -> return false
        if (2 * (leftSum - rightSum) == 9 * (rightQ - leftQ)) {
            return false;
        }
        
        // Otherwise, Alice wins
        return true;
    }
}
