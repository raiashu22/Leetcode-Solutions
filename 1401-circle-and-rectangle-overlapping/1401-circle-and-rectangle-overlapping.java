class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the closest X coordinate on the rectangle to the circle's center
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        
        // Find the closest Y coordinate on the rectangle to the circle's center
        int closestY = Math.max(y1, Math.min(yCenter, y2));
        
        // Calculate the distance between the closest point and the circle's center
        int distX = xCenter - closestX;
        int distY = yCenter - closestY;
        
        // Check if the squared distance is less than or equal to the squared radius
        return (distX * distX + distY * distY) <= (radius * radius);
    }
}
