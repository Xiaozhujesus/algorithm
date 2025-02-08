package priv.wz.dp;

/**
 * 给定一个由0和1组成的2维矩阵，返回该矩阵中最大的由1组成的正方形的面积
 */
public class MaxMatrix {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        /**
         * dp[i][j]是以matrix[i][j]为右下角的正方形的最长边长
         * 0 1 1 1 0
         * 1 1 1 1 0
         * 0 1 1 1 1
         * 0 1 1 1 1
         * 0 0 1 1 1
         * [i,j]为右下角时，最大就是 [i-1,j-1] + 1，但还要看 i 行和 j 列，
         * 这是通过 dp[i-1][j] 和 dp[i][j-1] 来判断的，如果都比 dp[i-1][j-1] 大说明 i 行和 j 列 也都是 1
         * 最大就是 [i-1,j-1] + 1，否则取最小
         */
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] > '0') {
                dp[i][0] = 1;
                max = 1;
            } else {
                dp[i][0] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] > '0') {
                dp[0][i] = 1;
                max = 1;
            } else {
                dp[0][i] = 0;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    int min = dp[i - 1][j - 1];
                    min = Math.min(dp[i][j - 1], min);
                    min = Math.min(dp[i - 1][j], min);
                    dp[i][j] = min + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max * max;
    }
}
