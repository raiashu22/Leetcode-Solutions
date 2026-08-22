public class Solution {
    public boolean checkDivisibility(int n) {
        int temp = n;
        int digitSum = 0;
        int digitProduct = 1;

        // Extract each digit one by one
        while (temp > 0) {
            int digit = temp % 10;
            digitSum += digit;
            digitProduct *= digit;
            temp /= 10;
        }

        int totalSum = digitSum + digitProduct;

        // Avoid division by zero
        if (totalSum == 0) {
            return false;
        }

        // Check if n is divisible by the total sum
        return n % totalSum == 0;
    }
}
