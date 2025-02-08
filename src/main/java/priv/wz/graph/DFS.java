package priv.wz.graph;

public class DFS {

    //连通图的深度优先搜索
    public void dfs(Graph graph, int start) {
        boolean[] visited = new boolean[graph.v];
        walk(graph, start, visited);
    }
    //非联通图的深度优先搜索
    public void dfs2(Graph graph) {
        boolean[] visited = new boolean[graph.v];
        for (int i = 0; i < graph.v; i++) {
            if (!visited[i]) {
                walk(graph, i, visited);
            }
        }
    }

    /**
     * 跟树的 DFS 一样只不过左右子树的递归，变成使用循环递归每个相邻节点
     */
    public void walk(Graph graph, int start, boolean[] visited) {
        visited[start] = true;
        System.out.println(start);
        for (Graph.VetexWithWeight i : graph.adj[start]) {
            if (!visited[i.v]) {
                walk(graph, i.v, visited);
            }
        }
    }
}


