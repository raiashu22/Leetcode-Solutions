class Solution {
    public int countCommas(int n) {
        int totalCommas = 0;
        long start = 1000;
        int commasPerNumber = 1;

        while (start <= n) {
            // Determine the upper bound for the current comma range
            long end = start * 1000 - 1;
            long currentEnd = Math.min(n, end);

            // Count how many numbers fall into this specific range
            long numbersInRange = currentEnd - start + 1;
            totalCommas += (int) (numbersInRange * commasPerNumber);

            // Move to the next thousand interval
            start *= 1000;
            commasPerNumber++;
        }

        return totalCommas;
    }
}
