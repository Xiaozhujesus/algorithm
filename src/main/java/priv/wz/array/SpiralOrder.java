package priv.wz.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 */
public class SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return Collections.EMPTY_LIST;
        }
        int m = matrix.length, n = matrix[0].length;
        int[] ans = new int[m * n];
        // 类似二分查找，从外向内收缩
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        int cur = 0;
        while (cur < ans.length) {
            // 循环处理每一圈
            int i = top, j = left;
            if (top == bottom) {
                // 一条横线
                while (j <= right) {
                    ans[cur++] = matrix[i][j];
                    j++;
                }
            } else if (left == right) {
                // 一条竖线
                while (i <= bottom) {
                    ans[cur++] = matrix[i][j];
                    i++;
                }
            } else {
                // 两条线
                while (j < right) {
                    ans[cur++] = matrix[i][j];
                    j++;
                }
                while (i < bottom) {
                    ans[cur++] = matrix[i][j];
                    i++;
                }
                while (j > left) {
                    ans[cur++] = matrix[i][j];
                    j--;
                }
                while (i > top) {
                    ans[cur++] = matrix[i][j];
                    i--;
                }
            }
            top++;
            bottom--;
            left++;
            right--;
        }
        return Arrays.stream(ans).boxed().collect(Collectors.toList());
//        return ans;
    }

    public List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        // 包括只有一条的情况
        while (left <= right && top <= bottom) {
            // 这两 for 循环要处理最后只有一条的情况
            for (int column = left; column < right; column++) {
                ans.add(matrix[top][column]);
            }
            // 注意这里边界条件有=，这里没有上面就需要有
            for (int row = top; row <= bottom; row++) {
                ans.add(matrix[row][right]);
            }
            // 横竖至少都是两条
            if (left < right && top < bottom) {
                // 这里的起始点，由于上面已经输出了右侧列的最后一个，因此从right-1开始
                for (int column = right - 1; column > left; column--) {
                    ans.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    ans.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return ans;
    }
}
