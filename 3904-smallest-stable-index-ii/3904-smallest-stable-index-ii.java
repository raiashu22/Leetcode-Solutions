public class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return -1;

        // Step 1: Precompute the suffix minimums
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // Step 2: Traverse from left to right to find the first stable index
        int prefixMax = nums[0];
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            
            // Calculate the instability score
            int instabilityScore = prefixMax - suffixMin[i];
            
            // Return the first index that satisfies the condition
            if (instabilityScore <= k) {
                return i;
            }
        }

        // Return -1 if no stable index is found
        return -1;
    }
}
