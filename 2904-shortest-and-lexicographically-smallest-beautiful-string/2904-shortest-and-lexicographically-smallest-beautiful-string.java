import java.util.ArrayList;
import java.util.List;

public class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        List<Integer> onesIndices = new ArrayList<>();
        
        // Step 1: Collect all indices where character is '1'
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                onesIndices.add(i);
            }
        }
        
        // If there aren't enough 1's, no beautiful substring exists
        if (onesIndices.size() < k) {
            return "";
        }
        
        int minLen = Integer.MAX_VALUE;
        String result = "";
        
        // Step 2 & 3: Iterate through all valid windows of size k
        for (int i = 0; i <= onesIndices.size() - k; i++) {
            int start = onesIndices.get(i);
            int end = onesIndices.get(i + k - 1);
            int currentLen = end - start + 1;
            
            // Extract the substring for this window
            String currentSub = s.substring(start, end + 1);
            
            // Step 4: Update result based on length and lexicographical order
            if (currentLen < minLen) {
                minLen = currentLen;
                result = currentSub;
            } else if (currentLen == minLen) {
                // If lengths are equal, pick the lexicographically smaller one
                if (currentSub.compareTo(result) < 0) {
                    result = currentSub;
                }
            }
        }
        
        return result;
    }
}
