class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int lesser = 0;
        int same = 0;
        
        for (int x : nums) {
            if (x < target) {
                lesser++;
            } else if (x == target) {
                same++;
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i = lesser; i < lesser + same; i++) {
            ans.add(i);
        }
        
        return ans;
    }
}