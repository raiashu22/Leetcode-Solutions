import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        
        // Step 1: Find the first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (first[idx] == -1) {
                first[idx] = i;
            }
            last[idx] = i;
        }
        
        List<int[]> intervals = new ArrayList<>();
        
        // Step 2: Expand intervals to meet conditions
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;
            
            int left = first[i];
            int right = last[i];
            boolean isValid = true;
            
            for (int j = left; j <= right; j++) {
                int currChar = s.charAt(j) - 'a';
                if (first[currChar] < left) {
                    isValid = false;
                    break;
                }
                right = Math.max(right, last[currChar]);
            }
            
            if (isValid) {
                intervals.add(new int[]{left, right});
            }
        }
        
        // Step 3: Sort intervals by their END positions
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));
        
        List<String> result = new ArrayList<>();
        int lastEnd = -1;
        
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            
            // If the current interval starts after the last chosen one ends
            if (left > lastEnd) {
                result.add(s.substring(left, right + 1));
                lastEnd = right;
            }
        }
        
        return result;
    }
}
