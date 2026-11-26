class Solution {
    int[] parent;
    int[] rank;
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];
        rank = new int[n + 1];
        // Initially, every node is its own parent
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int pu = find(u);
            int pv = find(v);
            // If both nodes have same parent, cycle exists
            if (pu == pv) {
                return edge;
            }
            union(pu, pv);
        }
        return new int[0];
    }
    // Find with Path Compression
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    // Union by Rank
    private void union(int u, int v) {
        if (rank[u] < rank[v]) {
            parent[u] = v;
        } 
        else if (rank[u] > rank[v]) {
            parent[v] = u;
        } 
        else {
            parent[v] = u;
            rank[u]++;
        }
    }
} 