class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Calculate the total sum of all stones (prefix sum up to index n-1)
        int totalPrefixSum = 0;
        for (int stone : stones) {
            totalPrefixSum += stone;
        }
        
        // Base case: If forced to take up to the last index,
        // the player must take all remaining stones.
        int maxDifference = totalPrefixSum;
        
        // Traverse backwards from index n-2 down to 1
        // Maintain the running prefix sum on the fly
        for (int i = n - 2; i > 0; i--) {
            totalPrefixSum -= stones[i + 1];
            maxDifference = Math.max(maxDifference, totalPrefixSum - maxDifference);
        }
        
        return maxDifference;
    }
}
