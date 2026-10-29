
import java.util.*;

class Solution {

    public List<Boolean> palindromePath(int n, int[][] edges,
                                        String s, String[] queries) {

        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] size = new int[n];
        int[] heavy = new int[n];
        int[] order = new int[n];

        Arrays.fill(parent, -2);
        Arrays.fill(heavy, -1);

        // Step 1: Build parent, depth and traversal order.
        int[] stack = new int[n];
        int top = 0;
        int count = 0;

        stack[top++] = 0;
        parent[0] = -1;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int v : graph[u]) {
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        // Step 2: Calculate subtree sizes and heavy children.
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;

            for (int v : graph[u]) {
                if (parent[v] == u) {
                    size[u] += size[v];

                    if (heavy[u] == -1 ||
                        size[v] > size[heavy[u]]) {
                        heavy[u] = v;
                    }
                }
            }
        }

        // Step 3: Heavy-Light Decomposition.
        int[] head = new int[n];
        int[] pos = new int[n];

        int[] chainStack = new int[n];
        int chainTop = 0;
        int timer = 0;

        chainStack[chainTop++] = 0;

        while (chainTop > 0) {
            int start = chainStack[--chainTop];
            int chainHead = start;

            for (int u = start; u != -1; u = heavy[u]) {
                head[u] = chainHead;
                pos[u] = timer++;

                for (int v : graph[u]) {
                    if (parent[v] == u && v != heavy[u]) {
                        chainStack[chainTop++] = v;
                    }
                }
            }
        }

        // Step 4: Build segment tree.
        int[] base = new int[n];

        for (int i = 0; i < n; i++) {
            base[pos[i]] = 1 << (s.charAt(i) - 'a');
        }

        SegmentTree seg = new SegmentTree(base);

        // Step 5: Process queries.
        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                seg.update(pos[u], 1 << (c - 'a'));

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = 0;

                // XOR the segments until both nodes share a chain.
                while (head[u] != head[v]) {
                    if (depth[head[u]] < depth[head[v]]) {
                        int temp = u;
                        u = v;
                        v = temp;
                    }

                    mask ^= seg.query(pos[head[u]], pos[u]);
                    u = parent[head[u]];
                }

                // Query the remaining segment, including both endpoints.
                int left = Math.min(pos[u], pos[v]);
                int right = Math.max(pos[u], pos[v]);

                mask ^= seg.query(left, right);

                // A palindrome permutation allows at most one odd count.
                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    static class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[4 * n];
            build(1, 0, n - 1, arr);
        }

        void build(int node, int start, int end, int[] arr) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }

            int mid = (start + end) / 2;

            build(node * 2, start, mid, arr);
            build(node * 2 + 1, mid + 1, end, arr);

            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        void update(int index, int value) {
            update(1, 0, n - 1, index, value);
        }

        void update(int node, int start, int end,
                    int index, int value) {

            if (start == end) {
                tree[node] = value;
                return;
            }

            int mid = (start + end) / 2;

            if (index <= mid) {
                update(node * 2, start, mid, index, value);
            } else {
                update(node * 2 + 1, mid + 1, end, index, value);
            }

            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        int query(int left, int right) {
            return query(1, 0, n - 1, left, right);
        }

        int query(int node, int start, int end,
                  int left, int right) {

            if (left <= start && end <= right) {
                return tree[node];
            }

            if (end < left || start > right) {
                return 0;
            }

            int mid = (start + end) / 2;

            return query(node * 2, start, mid, left, right)
                 ^ query(node * 2 + 1, mid + 1, end, left, right);
        }
    }
}
