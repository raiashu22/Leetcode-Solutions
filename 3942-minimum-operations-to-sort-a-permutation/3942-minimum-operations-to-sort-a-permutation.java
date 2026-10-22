class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int[] want = new int[n];
        for (int i = 0; i < n; i++) {
            want[i] = i;
        }

        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx = i;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        // Check if rotation of original array matches
        boolean matchOriginal = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx + i) % n] != want[i]) {
                matchOriginal = false;
                break;
            }
        }
        if (matchOriginal) {
            ans = Math.min(ans, Math.min(idx, n - idx + 2));
        }

        // Check if rotation of reversed array matches
        boolean matchReverse = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx - i + n) % n] != want[i]) {
                matchReverse = false;
                break;
            }
        }
        if (matchReverse) {
            ans = Math.min(ans, Math.min(idx + 2, n - idx));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}