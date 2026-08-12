import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        int left = 0;
        int maxLen = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Add the current element to the frequency map
            counts.put(nums[right], counts.getOrDefault(nums[right], 0) + 1);
            
            // Shrink window from the left if the frequency exceeds k
            while (counts.get(nums[right]) > k) {
                counts.put(nums[left], counts.get(nums[left]) - 1);
                left++;
            }
            
            // Update the maximum length found
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}
