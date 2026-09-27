class Solution {
    public String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ')') {
                // Collect characters back to the last '('
                StringBuilder temp = new StringBuilder();
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) != '(') {
                    temp.append(sb.charAt(sb.length() - 1));
                    sb.deleteCharAt(sb.length() - 1);
                }
                // Remove the '('
                sb.deleteCharAt(sb.length() - 1);
                // Append the reversed substring back
                sb.append(temp);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}