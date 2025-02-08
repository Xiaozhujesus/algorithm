package priv.wz.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 有向图和无向图的环是不同的，比如对于有向图 1 -> 2 -> 4,1 ->3 -> 4，这是不存在环的
 * 但若是无向图，就是存在环的；有向图的检测会难一些
 * 从这个例子也可以看出有向图无法用 BFS 检测环
 */
public class CycleDetect {

    /**
     * 有向图环检测
     */
    public boolean isCycle(Graph graph) {
        boolean[] visited = new boolean[graph.v];
        boolean[] stack = new boolean[graph.v];

        for (int i = 0; i < graph.v; i++) {
            if (cycle(graph, visited, stack, i)) {
                return true;
            }
        }
        return false;
    }

    // dfs只是visited过了不一定有环，比如1->2->4,1->3->4，这样就无环，但是节点4第二次会visited为true
    private boolean cycle(Graph graph, boolean[] visited, boolean[] stack, int start) {
        if (stack[start]) {
            return true;
        }
        if (visited[start]) {
            return false;
        }
        visited[start] = true;
        stack[start] = true;
        for (Graph.VetexWithWeight i : graph.adj[start]) {
            if (cycle(graph, visited, stack, i.v)) {
                return true;
            }
        }
        stack[start] = false;
        return false;
    }

    /**
     * 无向图环检测，并查集方法
     */
    public boolean isCycle1(Graph graph) {
        int[] parent = new int[graph.v];
        for (int i = 0; i < graph.v; i++) {
            parent[i] = -1;
        }
        for (int i = 0; i < graph.v; i++) {
            int pi = find(parent, i);
            for (Graph.VetexWithWeight j : graph.adj[i]) {
                // 避免重复检测，由于 最上层 for 循环从小到大，这里只检测比当前大的
                if (j.v > i) {
                    int pj = find(parent, j.v);
                    if (pi == pj) {
                        return true;
                    }
                    union(parent, pj, pi);
                }
            }
        }
        return false;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == -1) {
            return i;
        }
        return find(parent, parent[i]);
    }

    private void union(int[] parent, int i, int j) {
        parent[i] = j;
    }


    /**
     * 无向、非连通图环检测，DFS
     */
    public boolean isCycle2(Graph graph) {
        boolean[] visited = new boolean[graph.v];
        for (int i = 0; i < graph.v; i++) {
            if (!visited[i]) {
                if (walk(graph, visited, i, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 无向、连通图的环检测，DFS
     */
    private boolean walk(Graph graph, boolean[] visited, int cur, int parent) {
        visited[cur] = true;
        for (Graph.VetexWithWeight j : graph.adj[cur]) {
            if (!visited[j.v]) {
                if (walk(graph, visited, j.v, cur)) {
                    return true;
                }
            } else if (j.v != parent) {
                return true;
            }
        }
        return false;
    }

    /**
     * 无向、连通图检测环，BFS
     */
    public boolean isCycle3(Graph graph) {
        boolean[] visited = new boolean[graph.v];
        Queue<int[]> queue = new LinkedList<>();
        // 第一个是节点，第二个是 parent
        queue.add(new int[]{0, -1});
        visited[0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            // 我们假设图是无环的，这一步将 cur 所有邻接节点中去掉 cur 节点，转换为有向图了，否则 BFS 会重复探测，影响环的判断
            for (Graph.VetexWithWeight v : graph.adj[cur[0]]) {
                // 存在环
                if (visited[v.v] && v.v != cur[1]) {
                    return true;
                }
                visited[v.v] = true;
                queue.add(new int[]{v.v, cur[0]});
            }
        }
        return false;
    }
}
