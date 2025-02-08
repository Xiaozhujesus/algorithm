package priv.wz.search;

/**
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 *
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 */
public class SearchMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int[] firstColumn = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            firstColumn[i] = matrix[i][0];
        }
        BinarySearch bs = new BinarySearch();
        int row = bs.searchFirstGreatEqu(firstColumn, target);
        if (row == 0) {
            if (matrix[0][0] != target) {
                return false;
            } else {
                return true;
            }
        }
        if (row != matrix.length) {
            if (matrix[row][0] == target) {
                return true;
            }
            if (row == 0) {
                return false;
            }
        }
        row--;
        int col = bs.searchFirstGreatEqu(matrix[row], target);
        if (col == matrix[row].length) {
            return false;
        }
        if (matrix[row][col] == target) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        System.out.println(new SearchMatrix2().searchMatrix(matrix, 3));
    }
}
