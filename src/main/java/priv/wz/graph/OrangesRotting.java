package priv.wz.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * <p>
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻的新鲜橘子都会腐烂。
 * <p>
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 */
public class OrangesRotting {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(i * grid[0].length + j);
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- != 0) {
                int cur = queue.remove();
                int i = cur / grid[0].length;
                int j = cur % grid[0].length;
                if (valid(grid, i - 1, j)) {
                    grid[i - 1][j] = 2;
                    queue.add((i - 1) * grid[0].length + j);
                }
                if (valid(grid, i + 1, j)) {
                    grid[i + 1][j] = 2;
                    queue.add((i + 1) * grid[0].length + j);
                }
                if (valid(grid, i, j - 1)) {
                    grid[i][j - 1] = 2;
                    queue.add(i * grid[0].length + j - 1);
                }
                if (valid(grid, i, j + 1)) {
                    grid[i][j + 1] = 2;
                    queue.add(i * grid[0].length + j + 1);
                }
            }
            ans++;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        // 只有一个 0 的情况
        if (ans == 0) {
            return 0;
        }
        return ans - 1;
    }

    private boolean valid(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1;
    }
}
