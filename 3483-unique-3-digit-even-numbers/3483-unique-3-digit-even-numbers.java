public class Solution {
    public int totalNumbers(int[] digits) {
        // Count the frequency of each available digit
        int[] available = new int[10];
        for (int digit : digits) {
            available[digit]++;
        }
        
        int distinctEvenCount = 0;
        
        // Iterate through all possible 3-digit even numbers
        for (int num = 100; num < 1000; num += 2) {
            int[] needed = new int[10];
            int temp = num;
            
            // Extract digits of the current number
            while (temp > 0) {
                needed[temp % 10]++;
                temp /= 10;
            }
            
            // Check if we have enough available digits
            boolean isValid = true;
            for (int i = 0; i < 10; i++) {
                if (needed[i] > available[i]) {
                    isValid = false;
                    break;
                }
            }
            
            if (isValid) {
                distinctEvenCount++;
            }
        }
        
        return distinctEvenCount;
    }
}
