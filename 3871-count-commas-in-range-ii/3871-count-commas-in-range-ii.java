public class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long threshold = 1000;
        
        while (n >= threshold) {
            totalCommas += (n - threshold + 1);
            
            // Prevent long overflow since max n is 10^15
            if (threshold >= 1000000000000000L) {
                break;
            }
            threshold *= 1000;
        }
        
        return totalCommas;
    }
}
