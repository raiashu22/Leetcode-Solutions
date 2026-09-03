class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;
        
        // Single pass to find the smallest odd and even elements
        for (int num : nums1) {
            if (num % 2 == 0) {
                if (num < minEven) {
                    minEven = num;
                }
            } else {
                if (num < minOdd) {
                    minOdd = num;
                }
            }
        }
        
        // If there are no odd numbers or no even numbers, 
        // the array already satisfies the condition.
        if (minOdd == Integer.MAX_VALUE || minEven == Integer.MAX_VALUE) {
            return true;
        }
        
        // Even numbers can only change to odd if a smaller odd number exists
        return minOdd < minEven;
    }
}
