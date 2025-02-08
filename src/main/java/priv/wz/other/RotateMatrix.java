package priv.wz.other;

/**
 * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class RotateMatrix {
    public void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        diagonal(matrix);
        vertical(matrix);
    }

    // 对角线翻转
    public void diagonal(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    // 垂直翻转
    public void vertical(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            int j = 0, k = matrix[0].length - 1;
            while (j < k) {
                swap(matrix, i, j, i, k);
                j++;
                k--;
            }
        }
    }

    public void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        int tmp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = tmp;
    }
}
