/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int matchingNodesCount = 0;

    public int averageOfSubtree(TreeNode root) {
        matchingNodesCount = 0;
        calculateSumAndCount(root);
        return matchingNodesCount;
    }

    // Helper method that returns an array: {subtree_sum, subtree_node_count}
    private int[] calculateSumAndCount(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0}; // {Sum, Count}
        }

        // 1. Post-order traversal: Get data from left and right subtrees
        int[] leftData = calculateSumAndCount(node.left);
        int[] rightData = calculateSumAndCount(node.right);

        // 2. Accumulate current node's total sum and count
        int currentSum = leftData[0] + rightData[0] + node.val;
        int currentCount = leftData[1] + rightData[1] + 1;

        // 3. Check if current node's value matches the integer-divided average
        if (node.val == (currentSum / currentCount)) {
            matchingNodesCount++;
        }

        // 4. Return data back up to the parent node
        return new int[]{currentSum, currentCount};
    }
}
