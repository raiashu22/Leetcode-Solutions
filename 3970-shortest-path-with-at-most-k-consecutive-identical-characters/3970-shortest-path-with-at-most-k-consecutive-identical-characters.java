import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of [v, w]
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj[u].add(new int[]{v, w});
        }
        
        // Min-heap storing {weight, node, count}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        // dist[node][count] stores the minimum weight to reach (node, count)
        // Since count can range from 1 to k, we use size k + 1.
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        // Starting at node 0. 
        // Note: Depending on whether the path includes node 0's label initially or starts counting from the first step.
        // Let's trace carefully: a path starting at node 0 has its label included.
        // Since we start at node 0, the initial run length of labels[0] is 1.
        if (1 > k) return -1; // If k is less than 1, though constraints say 1 <= k <= 50.
        
        pq.offer(new int[]{0, 0, 1}); // {weight, node, count}
        dist[0][1] = 0;
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];
            int count = curr[2];
            
            // If we reached the destination node n - 1
            if (u == n - 1) {
                return d;
            }
            
            if (d > dist[u][count]) {
                continue;
            }
            
            for (int[] neighbor : adj[u]) {
                int v = neighbor[0];
                int w = neighbor[1];
                
                int nextCount;
                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }
                
                // Check constraint
                if (nextCount <= k) {
                    if (d + w < dist[v][nextCount]) {
                        dist[v][nextCount] = d + w;
                        pq.offer(new int[]{d + w, v, nextCount});
                    }
                }
            }
        }
        
        // Find the minimum distance to reach node n - 1 across all valid counts
        int ans = Integer.MAX_VALUE;
        for (int c = 1; c <= k; c++) {
            ans = Math.min(ans, dist[n - 1][c]);
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}