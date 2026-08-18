import java.util.HashMap;
import java.util.Map;

class Solution {
    // Renamed from largestAlmostMissingInteger to largestInteger
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[51]; // Based on constraints: 0 <= nums[i] <= 50
        
        for (int num : nums) {
            count[num]++;
        }

        // Case 1: Subarray size is 1
        if (k == 1) {
            int maxAns = -1;
            for (int i = 0; i <= 50; i++) {
                if (count[i] == 1) {
                    maxAns = Math.max(maxAns, i);
                }
            }
            return maxAns;
        }

        // Case 2: Subarray size is equal to array length
        if (k == n) {
            int maxAns = -1;
            for (int num : nums) {
                maxAns = Math.max(maxAns, num);
            }
            return maxAns;
        }

        // Case 3: 1 < k < n
        int maxAns = -1;
        if (count[nums[0]] == 1) {
            maxAns = Math.max(maxAns, nums[0]);
        }
        if (count[nums[n - 1]] == 1) {
            maxAns = Math.max(maxAns, nums[n - 1]);
        }

        return maxAns;
    }
}
