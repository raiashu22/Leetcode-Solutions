import java.util.Arrays;

public class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        // minLeft[i] stores the minimum length of a valid subarray in arr[0...i]
        int[] minLeft = new int[n];
        // Initialize with a large value representing infinity
        Arrays.fill(minLeft, Integer.MAX_VALUE / 2); 
        
        int minTotalLength = Integer.MAX_VALUE;
        int currentSum = 0;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            // Shrink the window from the left if the sum exceeds target
            while (currentSum > target && left <= right) {
                currentSum -= arr[left];
                left++;
            }
            
            // If we found a valid subarray matching the target
            if (currentSum == target) {
                int currentLength = right - left + 1;
                
                // Check if a valid non-overlapping subarray exists to the left
                if (left > 0 && minLeft[left - 1] != Integer.MAX_VALUE / 2) {
                    minTotalLength = Math.min(minTotalLength, currentLength + minLeft[left - 1]);
                }
                
                // Update the minLeft for the current end index
                if (right > 0) {
                    minLeft[right] = Math.min(minLeft[right - 1], currentLength);
                } else {
                    minLeft[right] = currentLength;
                }
            } else {
                // If no valid subarray ends at 'right', carry forward the best from the left
                if (right > 0) {
                    minLeft[right] = minLeft[right - 1];
                }
            }
        }
        
        return minTotalLength == Integer.MAX_VALUE ? -1 : minTotalLength;
    }
}
