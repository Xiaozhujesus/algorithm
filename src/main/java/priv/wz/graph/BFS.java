package priv.wz.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 树的 BFS 和 DFS 是图的特例，而图的 BFS 和 DFS 是搜索的特例，其他问题的搜索可能
 * 需要按照问题要求构建搜索规则，而图的搜索规则就是每次只能搜索相邻的点，也就是需要按边走
 */
public class BFS {

    // 连通图
    public void bfs(Graph graph, int start) {
        boolean[] visited = new boolean[graph.v];
        walk(graph, start, visited);
    }

    /**
     * 每个点有且仅有进入队列一次，进去就算访问过了，否则可能导致重复入队，比如菱形的情况
     */
    public void walk(Graph graph, int start, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int s = queue.poll();
            // 入队时输出或出队时输出都可以，入队输出需要在初始化队列时也输出初始点一次，出队只需这一次
            System.out.println(s);
            for (Graph.VetexWithWeight i : graph.adj[s]) {
                if (!visited[i.v]) {
                    visited[i.v] = true;
                    queue.add(i.v);
                }
            }
        }
    }

    // 非连通图
    public void bfsAll(Graph graph) {
        boolean[] visited = new boolean[graph.v];
        for (int i = 0; i < graph.v; i++) {
            if (!visited[i]) {
                walk(graph, i, visited);
            }
        }
    }
}
