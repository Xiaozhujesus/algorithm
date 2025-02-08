package priv.wz.graph;

import java.util.Stack;

/**
 * 棋盘上岛屿个数，岛屿由上下左右相连的 1 构成
 */
public class NumIslands {

    private boolean[][] visit;
    private char[][] grid;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        this.grid = grid;
        int row = grid.length;
        int column = grid[0].length;
        visit = new boolean[row][column];

        int ret = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1' && !visit[i][j]) {
                    ret++;
                    dfs2(i, j);
                }
            }
        }
        return ret;
    }

    /**
     * 递归
     */
    public void dfs(int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length) {
            return;
        }
        if (grid[i][j] == '0' || visit[i][j]) {
            return;
        }
        visit[i][j] = true;

        /**
         * 这里如果自己用栈迭代实现，栈中每个点有 4 个状态，对应栈顶当前节点应该访问上下左右那个相邻节点
         */
        dfs(i - 1, j);
        dfs(i + 1, j);
        dfs(i, j - 1);
        dfs(i, j + 1);
    }

    /**
     * 迭代
     */
    private void dfs2(int i, int j) {
        visit[i][j] = true;
        Stack<int[]> stack = new Stack<>();
        // 栈中元素是个三元组，分别表示 i,j,以及当前状态：0 表示应该访问当前位置上面，1 表示右面，以此类推顺时针旋转访问
        stack.push(new int[]{i, j, 0});
        while (!stack.empty()) {
            if (stack.peek()[2] == 4) {
                stack.pop();
            } else {
                int[] cur = stack.peek();
                switch (cur[2]) {
                    case 0:
                        if (valid(cur[0] - 1, cur[1])) {
                            visit[cur[0] - 1][cur[1]] = true;
                            stack.push(new int[]{cur[0] - 1, cur[1], 0});
                        }
                        break;
                    case 1:
                        if (valid(cur[0], cur[1] + 1)) {
                            visit[cur[0]][cur[1] + 1] = true;
                            stack.push(new int[]{cur[0], cur[1] + 1, 0});
                        }
                        break;
                    case 2:
                        if (valid(cur[0] + 1, cur[1])) {
                            visit[cur[0] + 1][cur[1]] = true;
                            stack.push(new int[]{cur[0] + 1, cur[1], 0});
                        }
                        break;
                    case 3:
                        if (valid(cur[0], cur[1] - 1)) {
                            visit[cur[0]][cur[1] - 1] = true;
                            stack.push(new int[]{cur[0], cur[1] - 1, 0});
                        }
                        break;
                    default:
                }
                cur[2]++;
            }
        }
    }

    private boolean valid(int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1' && !visit[i][j];
    }

    public static void main(String[] args) {
        char[][] tmp = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(new NumIslands().numIslands(tmp));
    }
}
