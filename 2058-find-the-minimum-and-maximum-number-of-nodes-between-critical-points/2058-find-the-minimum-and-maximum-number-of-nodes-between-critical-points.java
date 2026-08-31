/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // Base case: A critical point needs a previous and a next node.
        // Therefore, we need at least 3 nodes.
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int minDistance = Integer.MAX_VALUE;
        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 2; // Head is 1, next is 2

        while (curr.next != null) {
            ListNode nextNode = curr.next;
            
            // Check for local maxima or local minima
            boolean isMaxima = curr.val > prev.val && curr.val > nextNode.val;
            boolean isMinima = curr.val < prev.val && curr.val < nextNode.val;

            if (isMaxima || isMinima) {
                // If this is the first critical point we've encountered
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    // Update the minimum distance between adjacent critical points
                    minDistance = Math.min(minDistance, currentIndex - prevCriticalIndex);
                }
                // Update the last seen critical point index
                prevCriticalIndex = currentIndex;
            }

            // Move pointers forward
            prev = curr;
            curr = nextNode;
            currentIndex++;
        }

        // If we found fewer than 2 critical points, return [-1, -1]
        if (firstCriticalIndex == prevCriticalIndex) {
            return new int[]{-1, -1};
        }

        // Maximum distance is always between the first and the absolute last critical point
        int maxDistance = prevCriticalIndex - firstCriticalIndex;

        return new int[]{minDistance, maxDistance};
    }
}
