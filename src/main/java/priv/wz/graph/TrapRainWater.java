package priv.wz.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * 输出: 4
 * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
 * 输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * 输出: 10
 * 参考https://leetcode-cn.com/problems/trapping-rain-water-ii/
 */
public class TrapRainWater {
    private int[][] map;
    private boolean[][] visited;
    private int m;
    private int n;
    private int maxHeight;

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length < 3) {
            return 0;
        }
        if (heightMap[0] == null || heightMap[0].length < 3) {
            return 0;
        }
        map = heightMap;
        m = heightMap.length;
        n = heightMap[0].length;
        visited = new boolean[m][n];
        int max = heightMap[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (heightMap[i][j] > max) {
                    max = heightMap[i][j];
                }
            }
        }
        maxHeight = max;
        int ans = 0;
        List<int[]> curTopLevel;
        while (maxHeight > 0) {
            curTopLevel = visitGraph();
            ans += curTopLevel.size();
            maxHeight--;
            reset();
        }
        return ans;
    }

    /**
     * 类似找被包围的小岛，等于 maxHeight 的位置为1，其他位置为0，相当于找被 1 包围的所有点的位置
     * 图的遍历
     */
    private List<int[]> visitGraph() {
        List<int[]> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<int[]> tmp = visit(i, j);
                if (tmp != null) {
                    ans.addAll(tmp);
                }
            }
        }
        return ans;
    }

    /**
     * 以(x,y)为中心向周围探测，返回 null 表示以(x,y)为中心无法形成小岛，否则返回组成小岛的点的坐标
     */
    private List<int[]> visit(int x, int y) {
        if (x == -1 || x == m || y == -1 || y == n) {
            return null;
        }

        List<int[]> ans = new ArrayList<>();
        if (visited[x][y] || map[x][y] >= maxHeight) {
            return ans;
        }

        visited[x][y] = true;
        List<int[]> right = visit(x + 1, y);
        List<int[]> left = visit(x - 1, y);
        List<int[]> down = visit(x, y - 1);
        List<int[]> top = visit(x, y + 1);
        // 注意这里一定是周围都访问一遍，再进行判断是否为 null，否则有可能导致将边界无法形成小岛的点堵住，遍历其他点时候
        // 由于 visited 为 true 导致不会访问边界点而得出错误结果
        if (right == null || left == null || down == null || top == null) {
            return null;
        }
        ans.add(new int[]{x, y});
        ans.addAll(right);
        ans.addAll(left);
        ans.addAll(down);
        ans.addAll(top);
        return ans;
    }

    private void reset() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[][] ints = {{5, 8, 7, 7}, {5, 2, 1, 5}, {7, 1, 7, 1}, {8, 9, 6, 9}, {9, 8, 9, 9}};
        System.out.println(new TrapRainWater().trapRainWater(ints));
        Map<String, Integer> mp = new HashMap<>();
    }
}
