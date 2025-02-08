package priv.wz.greedy;

/**
 * 无向图的所有节点上 m 种色，要求相邻节点颜色不同，可以的话返回上色方案，否则返回 false
 * 感觉这题没有回溯，因为某一点上色1不可以，那么上其他色也不行
 */
public class MColor {
    private int[] colors;
    private int m;
    private int[][] graph;

    public int[] mColor(int[][] graph, int m) {
        if (graph == null || graph.length == 0) {
            return colors;
        }
        this.colors = new int[graph.length];
        this.m = m;
        this.graph = graph;
        walk(0);
        return colors;
    }

    /**
     * 这里以顶点的视角，即按顶点编号的顺序一个一个处理，图只是约束
     * 这里的回溯其实没什么用，结构是回溯的结构，由于使用了贪心策略，
     * 当第 n 个顶点用 m 中颜色都不行的时候，说明该图着色需要的最少
     * 颜色大于 m，回溯也没用，参考下面更基础的问题
     */
    private boolean walk(int v) {
        if (v == colors.length) {
            return true;
        }
        for (int c = 1; c <= m; c++) {
            // 优先从最小的开始，贪心
            if (valid(v, c)) {
                colors[v] = c;
                if (walk(v + 1)) {
                    return true;
                }
                colors[v] = 0;
            }
        }
        return false;
    }

    private boolean valid(int v, int color) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] == 1 && color == colors[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 新问题：给图顶点着色，相邻的顶点不同色，最少需要多少种颜色
     * 贪心
     */
    public int minColor(int[][] graph) {
        this.graph = graph;
        this.colors = new int[graph.length];
        int min = 1;
        // 遍历顶点
        for (int i = 0; i < graph.length; i++) {
            // 最多顶点个数种颜色,颜色从 1 开始，未着色的顶点默认为 0
            for (int j = 1; j <= graph.length; j++) {
                if (valid(i, j)) {
                    colors[i] = j;
                    if (j > min) {
                        min = j;
                    }
                    break;
                }
            }
        }
        return min;
    }

    /**
     * 上面问题比原始问题更基础，因此可以直接使用贪心而不需要回溯
     */
    public boolean mColor2(int[][] graph, int m) {
        // 遍历顶点
        for (int i = 0; i < graph.length; i++) {
            // 最多顶点个数种颜色
            for (int j = 1; j <= graph.length; j++) {
                if (valid(i, j)) {
                    colors[i] = j;
                    if (j > m) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * bfs 和 dfs 也可以，每个顶点的着色策略也是用贪心，顶点是否访问过可以直接
     * 用 answer 记录，默认为 0 表示没访问过，着色从 1 开始
     */
}
