import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0;
        int rightRem = 0;

        // Step 1: Calculate the minimum number of misplaced '(' and ')'
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--; // Valid pair matched
                } else {
                    rightRem++; // Misplaced closing parenthesis
                }
            }
        }

        Set<String> result = new HashSet<>();
        backtrack(s, 0, 0, 0, leftRem, rightRem, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void backtrack(
        String s, 
        int index, 
        int leftCount, 
        int rightCount, 
        int leftRem, 
        int rightRem, 
        StringBuilder path, 
        Set<String> result
    ) {
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0 && leftCount == rightCount) {
                result.add(path.toString());
            }
            return;
        }

        char currentChar = s.charAt(index);
        int len = path.length();

        // Option 1: Discard the current character (if it's a bracket that can be removed)
        if (currentChar == '(' && leftRem > 0) {
            backtrack(s, index + 1, leftCount, rightCount, leftRem - 1, rightRem, path, result);
        } else if (currentChar == ')' && rightRem > 0) {
            backtrack(s, index + 1, leftCount, rightCount, leftRem, rightRem - 1, path, result);
        }

        // Option 2: Keep the current character
        path.append(currentChar);

        if (currentChar != '(' && currentChar != ')') {
            // Keep letters without condition
            backtrack(s, index + 1, leftCount, rightCount, leftRem, rightRem, path, result);
        } else if (currentChar == '(') {
            backtrack(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, path, result);
        } else if (currentChar == ')' && leftCount > rightCount) {
            // Only keep ')' if there's a matching '(' before it
            backtrack(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, path, result);
        }

        // Backtrack: restore path
        path.setLength(len);
    }
}