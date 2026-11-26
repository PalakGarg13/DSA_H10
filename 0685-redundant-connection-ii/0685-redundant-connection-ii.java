class Solution {
    int[] parent;
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parentNode = new int[n + 1];
        int[] firstEdge = null;
        int[] secondEdge = null;
        // Step 1: Check if any node has two parents
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (parentNode[v] == 0) {
                parentNode[v] = u;
            } 
            else {
                firstEdge = new int[]{parentNode[v], v};
                secondEdge = edge;
                break;
            }
        }
        // Step 2: Initialize DSU
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        // Step 3: Detect cycle using Union Find
        for (int[] edge : edges) {
            // Skip the second edge if a node has two parents
            if (edge == secondEdge) {
                continue;
            }
            int u = edge[0];
            int v = edge[1];
            int pu = find(u);
            int pv = find(v);
            // Cycle detected
            if (pu == pv) {
                // If two parents exist, remove first edge
                if (firstEdge != null) {
                    return firstEdge;
                }
                // Otherwise remove current edge
                return edge;
            }
            union(u, v);
        }
        // No cycle, but two parents exist
        return secondEdge;
    }
    // Find with Path Compression
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    // Union
    private void union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        parent[pu] = pv;
    }
} 