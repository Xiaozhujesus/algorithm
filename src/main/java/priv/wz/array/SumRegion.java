package priv.wz.array;

/**
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 */
public class SumRegion {

    private int[][] sum;

    public SumRegion(int[][] nums) {
        int m = nums.length;
        int n = nums[0].length;
        sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + nums[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row1][col2] + sum[row2 - 1][col1 - 1] - sum[row1][col1 - 1] - sum[row2 - 1][col2];
    }
}
