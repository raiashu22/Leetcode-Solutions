import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Character> ops = new Stack<>();
        Stack<Set<String>> vals = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Implicit concatenation check:
            // Insert a multiplication/concatenation operator '*' if current token follows
            // a letter or '}' and starts with a letter or '{'.
            if (i > 0) {
                char prev = expression.charAt(i - 1);
                if ((Character.isLowerCase(prev) || prev == '}') && (Character.isLowerCase(c) || c == '{')) {
                    while (!ops.isEmpty() && ops.peek() == '*') {
                        evaluate(ops, vals);
                    }
                    ops.push('*');
                }
            }

            if (Character.isLowerCase(c)) {
                Set<String> set = new HashSet<>();
                set.add(String.valueOf(c));
                vals.push(set);
            } else if (c == '{') {
                ops.push(c);
            } else if (c == ',') {
                while (!ops.isEmpty() && ops.peek() != '{') {
                    evaluate(ops, vals);
                }
                ops.push(',');
            } else if (c == '}') {
                while (!ops.isEmpty() && ops.peek() != '{') {
                    evaluate(ops, vals);
                }
                ops.pop(); // Pop '{'
            }
        }

        while (!ops.isEmpty()) {
            evaluate(ops, vals);
        }

        List<String> result = new ArrayList<>(vals.pop());
        Collections.sort(result);
        return result;
    }

    private void evaluate(Stack<Character> ops, Stack<Set<String>> vals) {
        char op = ops.pop();
        Set<String> set2 = vals.pop();
        Set<String> set1 = vals.pop();
        Set<String> res = new HashSet<>();

        if (op == '*') {
            // Cartesian product / concatenation
            for (String s1 : set1) {
                for (String s2 : set2) {
                    res.add(s1 + s2);
                }
            }
        } else if (op == ',') {
            // Union
            res.addAll(set1);
            res.addAll(set2);
        }

        vals.push(res);
    }
}