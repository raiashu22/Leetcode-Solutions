class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            // Converts 0-indexed i to 1-indexed (i + 1)
            if (n % (i + 1) == 0) {
                sum += nums[i] * nums[i];
            }
        }

        return sum;
    }
}