class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Extract coordinates for readability
        int x1_1 = rec1[0], y1_1 = rec1[1], x2_1 = rec1[2], y2_1 = rec1[3];
        int x1_2 = rec2[0], y1_2 = rec2[1], x2_2 = rec2[2], y2_2 = rec2[3];

        // Check if rec2 is completely to the left, right, above, or below rec1
        if (x2_2 <= x1_1 || // rec2 is completely to the left of rec1
            x1_2 >= x2_1 || // rec2 is completely to the right of rec1
            y2_2 <= y1_1 || // rec2 is completely below rec1
            y1_2 >= y2_1) { // rec2 is completely above rec1
            return false;
        }

        // If none of the above are true, they must overlap
        return true;
    }
}
