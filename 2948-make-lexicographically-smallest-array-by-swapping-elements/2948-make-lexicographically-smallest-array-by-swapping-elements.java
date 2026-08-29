import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Step 1: Pair each element with its original index
        int[][] sortedPairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedPairs[i][0] = nums[i]; // value
            sortedPairs[i][1] = i;       // original index
        }
        
        // Sort pairs primarily by their values
        Arrays.sort(sortedPairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        
        // Step 2: Group connected components
        int i = 0;
        while (i < n) {
            int j = i + 1;
            
            // Find all elements belonging to the same group
            while (j < n && sortedPairs[j][0] - sortedPairs[j - 1][0] <= limit) {
                j++;
            }
            
            // Step 3: Extract and sort the original indices for this group
            List<Integer> groupIndices = new ArrayList<>();
            for (int k = i; k < j; k++) {
                groupIndices.add(sortedPairs[k][1]);
            }
            Collections.sort(groupIndices);
            
            // Step 4: Map sorted values to sorted indices in the final result
            for (int idx = 0; idx < groupIndices.size(); idx++) {
                result[groupIndices.get(idx)] = sortedPairs[i + idx][0];
            }
            
            // Move to the next group
            i = j;
        }
        
        return result;
    }
}
