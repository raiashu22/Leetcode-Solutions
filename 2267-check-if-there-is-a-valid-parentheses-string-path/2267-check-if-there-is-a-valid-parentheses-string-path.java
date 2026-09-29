class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Total path length is m + n - 1. Must be even.
        if ((m + n - 1) % 2 != 0) return false;
        // Path must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        int maxBalance = (m + n - 1) / 2;
        memo = new Boolean[m][n][maxBalance + 1];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance) {
        // Adjust balance based on current cell
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Invalid path state
        if (balance < 0 || balance > (m + n - 1) / 2) return false;

        // Reached destination
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        // Return cached result
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }

        boolean found = false;
        // Move Right
        if (c + 1 < n) {
            found = dfs(grid, r, c + 1, balance);
        }
        // Move Down
        if (!found && r + 1 < m) {
            found = dfs(grid, r + 1, c, balance);
        }

        return memo[r][c][balance] = found;
    }
}