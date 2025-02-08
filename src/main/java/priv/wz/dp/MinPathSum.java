package priv.wz.dp;

/**
 * 给定一个由非负整数填充的m x n的二维数组，现在要从二维数组的左上角走到右下角，请找出路径上的所有数字之和最小的路径。
 * 注意：你每次只能向下或向右移动。
 */
public class MinPathSum {
    private int m;
    private int n;
    private int[][] dp;
    private int[][] grid;

    // 自顶向下
    public int minPathSum(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        dp = new int[m][n];
        this.grid = grid;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        dp[m - 1][n - 1] = grid[m - 1][n - 1];
        util(0, 0);
        return dp[0][0];
    }

    private void util(int i, int j) {
        if (dp[i][j] != -1) {
            return;
        }
        if (i == m - 1) {
            util(i, j + 1);
            dp[i][j] = grid[i][j] + dp[i][j + 1];
            return;
        }
        if (j == n - 1) {
            util(i + 1, j);
            dp[i][j] = grid[i][j] + dp[i + 1][j];
            return;
        }
        util(i + 1, j);
        util(i, j + 1);
        dp[i][j] = grid[i][j] + (dp[i + 1][j] < dp[i][j + 1] ? dp[i + 1][j] : dp[i][j + 1]);
    }

    // 自底向上
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j != n - 1) {
                    dp[i][j] = dp[i][j + 1] + grid[i][j];
                } else if (j == n - 1 && i != m - 1) {
                    dp[i][j] = dp[i + 1][j] + grid[i][j];
                } else if (i != m - 1 && j != n - 1) {
                    dp[i][j] = (dp[i + 1][j] > dp[i][j + 1] ? dp[i][j + 1] : dp[i + 1][j]) + grid[i][j];
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }
        return dp[0][0];
    }

    // 原地，空间复杂度为1
    public int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }
}
