package org.practice.array;

/**
 * 有一个NxN整数矩阵，请编写一个算法，将矩阵顺时针旋转90度。
 * <p>
 * 给定一个NxN的矩阵，和矩阵的阶数N,请返回旋转后的NxN矩阵,保证N小于等于300。
 * <p>
 * 测试样例：
 * [[1,2,3],[4,5,6],[7,8,9]],3
 * 返回：[[7,4,1],[8,5,2],[9,6,3]]
 */
public class RotateMetric2 {
    public int[][] rotateMatrix(int[][] mat, int n) {
        // 找规律：mat[i][j]被旋转到了mat[j][n-i-1]的位置
        int[][] temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[j][n - 1 - i] = mat[i][j];
            }
        }
        return temp;
    }
}
