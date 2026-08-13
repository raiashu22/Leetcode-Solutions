class Solution {
    // Segment Tree Node Structure
    class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        char leftChar;
        char rightChar;
        int size;

        Node(char c) {
            this.maxLen = 1;
            this.prefLen = 1;
            this.suffLen = 1;
            this.leftChar = c;
            this.rightChar = c;
            this.size = 1;
        }

        Node() {
            this.maxLen = 0;
            this.prefLen = 0;
            this.suffLen = 0;
            this.size = 0;
        }
    }

    private Node[] tree;
    private char[] chars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        this.chars = s.toCharArray();
        this.tree = new Node[4 * n];

        buildTree(0, 0, n - 1);

        int k = queryIndices.length;
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            chars[idx] = c;
            updateTree(0, 0, n - 1, idx, c);
            result[i] = tree[0].maxLen;
        }

        return result;
    }

    private void buildTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        buildTree(leftChild, start, mid);
        buildTree(rightChild, mid + 1, end);

        tree[node] = merge(tree[leftChild], tree[rightChild]);
    }

    private void updateTree(int node, int start, int end, int idx, char c) {
        if (start == end) {
            tree[node] = new Node(c);
            return;
        }
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        if (idx <= mid) {
            updateTree(leftChild, start, mid, idx, c);
        } else {
            updateTree(rightChild, mid + 1, end, idx, c);
        }

        tree[node] = merge(tree[leftChild], tree[rightChild]);
    }

    private Node merge(Node left, Node right) {
        Node parent = new Node();
        parent.size = left.size + right.size;
        parent.leftChar = left.leftChar;
        parent.rightChar = right.rightChar;

        // Base max length from children
        parent.maxLen = Math.max(left.maxLen, right.maxLen);
        parent.prefLen = left.prefLen;
        parent.suffLen = right.suffLen;

        // If the boundary characters match, we can merge across the center
        if (left.rightChar == right.leftChar) {
            int combinedMid = left.suffLen + right.prefLen;
            parent.maxLen = Math.max(parent.maxLen, combinedMid);

            // If the entire left child is uniform, prefix extends into the right child
            if (left.prefLen == left.size) {
                parent.prefLen = left.size + right.prefLen;
            }
            // If the entire right child is uniform, suffix extends into the left child
            if (right.suffLen == right.size) {
                parent.suffLen = right.size + left.suffLen;
            }
        }

        return parent;
    }
}
