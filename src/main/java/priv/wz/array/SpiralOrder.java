package priv.wz.array;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 */
public class SpiralOrder {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[0];
        }
        int m = matrix.length, n = matrix[0].length;
        int[] ans = new int[m * n];
        int r1 = 0, r2 = m - r1 - 1, c1 = 0, c2 = n - c1 - 1;
        int cur = 0;
        while (cur < ans.length) {
            int i = r1, j = c1;
            if (r1 == r2) {
                while (j <= c2) {
                    ans[cur++] = matrix[i][j];
                    j++;
                }
            } else if (c1 == c2) {
                while (i <= r2) {
                    ans[cur++] = matrix[i][j];
                    i++;
                }
            } else {
                while (j < c2) {
                    ans[cur++] = matrix[i][j];
                    j++;
                }
                while (i < r2) {
                    ans[cur++] = matrix[i][j];
                    i++;
                }
                while (j > c1) {
                    ans[cur++] = matrix[i][j];
                    j--;
                }
                while (i > r1) {
                    ans[cur++] = matrix[i][j];
                    i--;
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }
}
