class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;
        
        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        
        // Case 1: Total XOR is already non-zero
        if (totalXor != 0) {
            return nums.length;
        }
        
        // Case 2: Total XOR is 0, but we can drop one non-zero element
        if (hasNonZero) {
            return nums.length - 1;
        }
        
        // Case 3: All elements are 0
        return 0;
    }
}
