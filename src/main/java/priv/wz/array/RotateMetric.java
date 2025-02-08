package org.practice.array;

import java.util.ArrayList;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素
 */
public class RotateMetric {

    public ArrayList<Integer> g(int[][] matrix) {
        ArrayList<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        // 包括只有一条的情况
        while (left <= right && top <= bottom) {
            for (int column = left; column < right; column++) {
                order.add(matrix[top][column]);
            }
            // 注意这里边界条件有=
            for (int row = top; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            // 横竖至少都是两条
            if (left < right && top < bottom) {
                // 这里的起始点，由于上面已经输出了右侧列的最后一个，因此从right-1开始
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
