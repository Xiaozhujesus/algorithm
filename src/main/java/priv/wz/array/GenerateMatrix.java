package priv.wz.array;

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针
 * 顺序螺旋排列的 n x n 正方形矩阵 matrix 1 <= n <= 20
 */
public class GenerateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int i = 0, j = 0, cur = 1;
        while (i < n / 2) {
            int begin = i, end = n - 1 - i;
            while (j < end) {
                ans[i][j++] = cur++;
            }
            while (i < end) {
                ans[i++][j] = cur++;
            }
            while (j > begin) {
                ans[i][j--] = cur++;
            }
            while (i > begin) {
                ans[i--][j] = cur++;
            }
            i++;
            j++;
        }
        if (1 == n % 2) {
            ans[i][j] = cur;
        }
        return ans;
    }
}
