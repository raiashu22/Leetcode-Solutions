import java.util.Arrays;

class Solution {
    private int getDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[][] arr = new Integer[n][2];
        
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i]; // value
            arr[i][1] = i;       // original index
        }
        
        // Sort based on digit sum, then by value
        Arrays.sort(arr, (a, b) -> {
            int sumA = getDigitSum(a[0]);
            int sumB = getDigitSum(b[0]);
            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }
            return Integer.compare(a[0], b[0]);
        });
        
        boolean[] visited = new boolean[n];
        int cycles = 0;
        
        for (int i = 0; i < n; i++) {
            if (visited[i] || arr[i][1] == i) {
                continue;
            }
            
            // Traverse the cycle
            int j = i;
            int cycleSize = 0;
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j][1];
                cycleSize++;
            }
            
            if (cycleSize > 1) {
                cycles += (cycleSize - 1);
            }
        }
        
        return cycles;
    }
}