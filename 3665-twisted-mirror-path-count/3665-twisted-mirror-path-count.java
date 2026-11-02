class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long MOD = 1000000007;

        // Helper function to resolve the final landing position for a move
        // dir: 0 for trying to move Right, 1 for trying to move Down
        int[][][] jump = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Try moving Right (dir = 0)
                jump[i][j][0] = simulate(grid, i, j, 0, m, n);
                // Try moving Down (dir = 1)
                jump[i][j][1] = simulate(grid, i, j, 1, m, n);
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                // For both Right (0) and Down (1) moves
                for (int dir = 0; dir < 2; dir++) {
                    int nextPos = jump[i][j][dir];
                    if (nextPos != -1) {
                        int ni = nextPos / n;
                        int nj = nextPos % n;
                        dp[ni][nj] = (dp[ni][nj] + dp[i][j]) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }

    private int simulate(int[][] grid, int r, int c, int dir, int m, int n) {
        // dir: 0 = trying to move right, 1 = trying to move down
        while (true) {
            int nr = r, nc = c;
            if (dir == 0) {
                nc++;
            } else {
                nr++;
            }

            // Check boundaries
            if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                return -1; // Invalid/Out of bounds
            }

            // If it hits an empty cell, this is where it lands
            if (grid[nr][nc] == 0) {
                return nr * n + nc;
            }

            // If it hits a mirror, it reflects immediately:
            // Entering while moving right (dir=0) -> turns down (dir=1)
            // Entering while moving down (dir=1) -> turns right (dir=0)
            r = nr;
            c = nc;
            dir = 1 - dir;
        }
    }
}