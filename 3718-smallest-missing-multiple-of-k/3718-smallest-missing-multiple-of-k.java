import java.util.HashSet;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        // Store all numbers in a hash set for O(1) lookups
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        // Start checking multiples of k: k, 2k, 3k...
        int multiple = k;
        while (set.contains(multiple)) {
            multiple += k;
        }
        
        return multiple;
    }
}
