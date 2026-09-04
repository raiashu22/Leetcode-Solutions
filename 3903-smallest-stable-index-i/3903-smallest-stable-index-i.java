class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return -1;

        // Step 1: Precompute the suffix minimums
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // Step 2: Iterate through the array keeping track of the prefix maximum
        int currentMax = nums[0];
        for (int i = 0; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            
            // Calculate instability score
            int instabilityScore = currentMax - suffixMin[i];
            
            // Return the first index that satisfies the stability condition
            if (instabilityScore <= k) {
                return i;
            }
        }

        return -1;
    }
}
