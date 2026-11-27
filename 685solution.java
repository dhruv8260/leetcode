
class Solution {

    int[] parent;

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int u, int v) {
        int pu = find(u);
        int pv = find(v);

        if (pu == pv) {
            return false;
        }

        parent[pu] = pv;
        return true;
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {

        int n = edges.length;

        int[] parentNode = new int[n + 1];

        int[] candidate1 = null;
        int[] candidate2 = null;

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];

            if (parentNode[v] == 0) {
                parentNode[v] = u;
            } else {
                candidate1 = new int[]{parentNode[v], v};
                candidate2 = edge;
                edge[1] = 0;
            }
        }

        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];

            if (v == 0) {
                continue;
            }

            if (!union(u, v)) {

                if (candidate1 == null) {
                    return edge;
                }

                return candidate1;
            }
        }

        return candidate2;
    }
}
