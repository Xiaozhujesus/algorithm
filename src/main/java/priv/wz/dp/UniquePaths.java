package priv.wz.dp;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] path = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    path[i][j] = 1;
                } else {
                    path[i][j] = path[i - 1][j] + path[i][j - 1];
                }
            }
        }
        return path[m - 1][n - 1];
    }


    /**
     * 障碍物
     */
    public int uniquePaths(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int[][] path2 = new int[m][n];
        if (arr[0][0] == 1 || arr[m - 1][n - 1] == 1) {
            return 0;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (arr[i][j] == 1) {
                    path2[i][j] = 0;
                } else if (i == m - 1 && j == n - 1) {
                    path2[i][j] = 1;
                } else if (i == m - 1) {
                    path2[i][j] = path2[i][j + 1];
                } else if (j == n - 1) {
                    path2[i][j] = path2[i + 1][j];
                } else {
                    path2[i][j] = path2[i + 1][j] + path2[i][j + 1];
                }
            }
        }
        return path2[0][0];
    }
}
