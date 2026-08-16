class Solution {
    public boolean stoneGameIX(int[] stones) {
        // Track count of stones with remainders 0, 1, and 2
        int[] cnt = new int[3];
        for (int s : stones) {
            cnt[s % 3]++;
        }
        
        // Case 1: Evens out the turn flip of 0-stones
        if (cnt[0] % 2 == 0) {
            return cnt[1] > 0 && cnt[2] > 0;
        }
        
        // Case 2: One 0-stone flips the turn advantage to Bob
        return Math.abs(cnt[1] - cnt[2]) > 2;
    }
}
