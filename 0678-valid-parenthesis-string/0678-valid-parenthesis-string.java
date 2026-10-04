class Solution {
    public boolean checkValidString(String s) {
        int low = 0;  // Minimum possible open parentheses count
        int high = 0; // Maximum possible open parentheses count

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else { // c == '*'
                low--;  // Treat '*' as ')'
                high++; // Treat '*' as '('
            }

            // 'high' falling below 0 means too many ')' have been encountered
            if (high < 0) {
                return false;
            }

            // 'low' cannot be negative since we cannot have negative open parentheses
            if (low < 0) {
                low = 0;
            }
        }

        // Valid if it's possible to end with 0 open parentheses
        return low == 0;
    }
}