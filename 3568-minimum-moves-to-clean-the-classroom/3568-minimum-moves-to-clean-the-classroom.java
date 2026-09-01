import java.util.*;

public class Solution {
    // Helper class to store the state of the BFS
    static class State {
        int r, c, energy, mask, moves;

        State(int r, int c, int energy, int mask, int moves) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
            this.moves = moves;
        }
    }

    // Renamed method to match your online judge platform driver call signature
    public int minMoves(String[] classroom, int maxEnergy) {
        int m = classroom.length;
        int n = classroom[0].length(); // Fixed string indexing bug from previous snippet
        
        int startR = -1, startC = -1;
        List<int[]> litterList = new ArrayList<>();
        
        // Locate 'S' and all 'L' cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litterList.add(new int[]{i, j});
                }
            }
        }
        
        int totalLitter = litterList.size();
        int targetMask = (1 << totalLitter) - 1;
        
        // If there is no litter to collect, 0 moves are needed
        if (targetMask == 0) return 0;

        // visited[r][c][mask] stores the maximum remaining energy seen for this state
        int[][][] visited = new int[m][n][1 << totalLitter];
        for (int[][] arr2D : visited) {
            for (int[] arr1D : arr2D) {
                Arrays.fill(arr1D, -1);
            }
        }

        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(startR, startC, maxEnergy, 0, 0));
        visited[startR][startC][0] = maxEnergy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            // If all litter items are collected, return the total moves
            if (curr.mask == targetMask) {
                return curr.moves;
            }

            // If out of energy, student cannot move further unless they are standing on 'R'
            if (curr.energy == 0) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];

                // Boundary check and obstacle check
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                int nextEnergy = curr.energy - 1;
                int nextMask = curr.mask;
                char nextCell = classroom[nr].charAt(nc);

                // If next cell is litter, find its index and update bitmask
                if (nextCell == 'L') {
                    for (int k = 0; k < totalLitter; k++) {
                        if (litterList.get(k)[0] == nr && litterList.get(k)[1] == nc) {
                            nextMask |= (1 << k);
                            break;
                        }
                    }
                } 
                // If next cell is reset station, restore full energy capacity
                else if (nextCell == 'R') {
                    nextEnergy = maxEnergy;
                }

                // Pruning: Only proceed if this state provides strictly more energy than before
                if (nextEnergy > visited[nr][nc][nextMask]) {
                    visited[nr][nc][nextMask] = nextEnergy;
                    queue.offer(new State(nr, nc, nextEnergy, nextMask, curr.moves + 1));
                }
            }
        }

        return -1; // Return -1 if it's impossible to collect all litter
    }
}
