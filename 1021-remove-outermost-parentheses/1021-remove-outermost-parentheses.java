class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If count > 0, it means this '(' is not the outermost parenthesis of a primitive string
                if (count > 0) {
                    result.append(c);
                }
                count++;
            } else {
                count--;
                // If count > 0 after decrementing, it means this ')' is not the outermost parenthesis
                if (count > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}