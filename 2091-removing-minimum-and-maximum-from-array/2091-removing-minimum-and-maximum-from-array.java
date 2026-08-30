class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }

        int minIdx = 0;
        int maxIdx = 0;

        // Find the indices of the minimum and maximum elements
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        // Determine left and right boundaries among the two target indices
        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        // Strategy 1: Deleting both from the front
        int op1 = right + 1;

        // Strategy 2: Deleting both from the back
        int op2 = n - left;

        // Strategy 3: Deleting left from front and right from back
        int op3 = (left + 1) + (n - right);

        // Return the minimum of the three strategies
        return Math.min(op1, Math.min(op2, op3));
    }
}
