class Solution {
    private static final int MOD = 1000000007;
    long[] sumSq;
    long[] sumVal;
    long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumSq = new long[4 * n];
        sumVal = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> lastOccurrence = new HashMap<>();
        long totalSumSq = 0;
        long currentSumSq = 0;

        for (int i = 0; i < n; i++) {
            int val = nums[i];
            int last = lastOccurrence.getOrDefault(val, -1);
            
            // Range update [last + 1, i] increment by 1
            if (last + 1 <= i) {
                update(1, 0, n - 1, last + 1, i, 1);
            }
            lastOccurrence.put(val, i);

            // Query total sum of squares of all subarrays ending at i
            currentSumSq = querySq(1, 0, n - 1, 0, i);
            totalSumSq = (totalSumSq + currentSumSq) % MOD;
        }

        return (int) totalSumSq;
    }

    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val) {
        int len = r - l + 1;
        sumSq[node] = (sumSq[node] + 2 * val * sumVal[node] % MOD + val * val % MOD * len) % MOD;
        sumVal[node] = (sumVal[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        if (ql <= mid) update(2 * node, l, mid, ql, qr, val);
        if (qr > mid) update(2 * node + 1, mid + 1, r, ql, qr, val);
        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        sumVal[node] = (sumVal[2 * node] + sumVal[2 * node + 1]) % MOD;
    }

    private long querySq(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return sumSq[node];
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        long res = 0;
        if (ql <= mid) res = (res + querySq(2 * node, l, mid, ql, qr)) % MOD;
        if (qr > mid) res = (res + querySq(2 * node + 1, mid + 1, r, ql, qr)) % MOD;
        return res;
    }
}