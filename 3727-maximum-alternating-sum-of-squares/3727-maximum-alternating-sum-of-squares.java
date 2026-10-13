import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squared = new long[n];
        for (int i = 0; i < n; i++) {
            squared[i] = (long) nums[i] * nums[i];
        }
        
        Arrays.sort(squared);
        
        long score = 0;
        int mid = n / 2;
        
        // Subtract the smaller half [0, mid)
        for (int i = 0; i < mid; i++) {
            score -= squared[i];
        }
        
        // Add the larger half [mid, n)
        for (int i = mid; i < n; i++) {
            score += squared[i];
        }
        
        return score;
    }
}