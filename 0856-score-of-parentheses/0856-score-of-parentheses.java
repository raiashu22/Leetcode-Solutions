class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // Holds the total score at current depth level

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0); // Start a new nested level
            } else {
                int innerScore = stack.pop();
                int outerScore = stack.pop();
                // If innerScore is 0, it means "()", score is 1.
                // Otherwise, it's (A), score is 2 * innerScore.
                stack.push(outerScore + Math.max(2 * innerScore, 1));
            }
        }

        return stack.pop();
    }
}