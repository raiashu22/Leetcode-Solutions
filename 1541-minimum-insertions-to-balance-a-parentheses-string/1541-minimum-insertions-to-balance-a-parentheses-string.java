class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openCount = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                openCount++;
            } else {
                // If the next character is also ')', we have a consecutive "))"
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++; // Consume the second ')'
                } else {
                    // Single ')' found; insert one ')' to make it "))"
                    insertions++;
                }

                // Match with an opening '(' if available
                if (openCount > 0) {
                    openCount--;
                } else {
                    // Need an opening '(' for this "))"
                    insertions++;
                }
            }
        }

        // Each unmatched '(' requires two ')'
        insertions += openCount * 2;

        return insertions;
    }
}