package org.practice.dp;

/**
 * 给定一个由0和1组成的2维矩阵，返回该矩阵中最大的由1组成的正方形的面积
 */
public class MaxMatrix {
    public int solve(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        /**
         * dp[i][j]是以matrix[i][j]为右下角的正方形的最长边长
         */
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] > '0' ? 1 : 0;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i] > '0' ? 1 : 0;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    int min = dp[i-1][j-1];
                    min = dp[i][j-1] < min ? dp[i][j-1] : min;
                    min = dp[i-1][j] < min ? dp[i-1][j] : min;
                    dp[i][j] = min+1;
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                    }
                }
            }
        }
        return max*max;
    }
}
