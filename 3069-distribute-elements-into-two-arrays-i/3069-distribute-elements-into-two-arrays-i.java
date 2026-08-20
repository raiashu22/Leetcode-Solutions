import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        
        // Use Lists since the final size of each array dynamically changes
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        
        // First operation: append nums[0] to arr1
        arr1.add(nums[0]);
        // Second operation: append nums[1] to arr2
        arr2.add(nums[1]);
        
        // Process the remaining elements
        for (int i = 2; i < n; i++) {
            int lastArr1 = arr1.get(arr1.size() - 1);
            int lastArr2 = arr2.get(arr2.size() - 1);
            
            if (lastArr1 > lastArr2) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }
        
        // Concatenate arr1 and arr2 into the final result array
        int[] result = new int[n];
        int index = 0;
        
        for (int num : arr1) {
            result[index++] = num;
        }
        for (int num : arr2) {
            result[index++] = num;
        }
        
        return result;
    }
}
