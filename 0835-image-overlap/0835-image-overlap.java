import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();
        
        // 1. Filter and store the coordinates of all 1s for both images
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) {
                    list1.add(new int[]{r, c});
                }
                if (img2[r][c] == 1) {
                    list2.add(new int[]{r, c});
                }
            }
        }
        
        // 2. Count frequencies of transformation vectors
        // Scale is 2*n to account for negative offsets (range from -n to n)
        int[][] counts = new int[2 * n][2 * n];
        int maxOverlap = 0;
        
        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                // Calculate the translation vector and add the 'n' offset to handle negative indices
                int dr = p2[0] - p1[0] + n;
                int dc = p2[1] - p1[1] + n;
                
                counts[dr][dc]++;
                maxOverlap = Math.max(maxOverlap, counts[dr][dc]);
            }
        }
        
        return maxOverlap;
    }
}
