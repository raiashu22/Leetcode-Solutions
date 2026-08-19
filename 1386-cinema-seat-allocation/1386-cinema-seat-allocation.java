import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map to store row number and its bitmask representation for seats 2 to 9
        Map<Integer, Integer> rowReservations = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            // We only care about seats 2 through 9
            if (col >= 2 && col <= 9) {
                // Shift based on index 0 (seat 2 corresponds to bit 0)
                int currentMask = rowReservations.getOrDefault(row, 0);
                rowReservations.put(row, currentMask | (1 << (col - 2)));
            }
        }
        
        // Bitmask patterns for the three possible blocks (shifted by 2)
        // Left block:  seats 2,3,4,5 -> bits 0,1,2,3 -> binary 00001111 (15)
        // Right block: seats 6,7,8,9 -> bits 4,5,6,7 -> binary 11110000 (240)
        // Mid block:   seats 4,5,6,7 -> bits 2,3,4,5 -> binary 00111100 (60)
        int leftMask = 15;
        int rightMask = 240;
        int midMask = 60;
        
        // Start assuming all rows are completely empty (each gets 2 groups)
        int maxGroups = n * 2;
        
        // Process only the rows that have at least one reservation
        for (int mask : rowReservations.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;
            boolean midFree = (mask & midMask) == 0;
            
            if (leftFree && rightFree) {
                // Both left and right sides are open; row fits 2 families.
                // No deduction needed.
                continue;
            } else if (leftFree || rightFree || midFree) {
                // At least one of the three blocks is completely open.
                // Row fits 1 family instead of 2. Deduct 1.
                maxGroups -= 1;
            } else {
                // No blocks are fully open. Row fits 0 families. Deduct 2.
                maxGroups -= 2;
            }
        }
        
        return maxGroups;
    }
}
