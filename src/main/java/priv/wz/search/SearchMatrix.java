package priv.wz.search;

/**
 * 编写一个高效的算法来搜索mxn矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = 0, column = matrix[0].length - 1, mid;
        boolean horizon = true;
        while (true) {
            if (horizon) {
                mid = bSearchRow(matrix, row, 0, column, target);
                if (mid == -1) {
                    return false;
                }
                if (matrix[row][mid] == target) {
                    return true;
                }
                column = mid;
                horizon = false;
            } else {
                mid = bSearchColumn(matrix, column, row, matrix.length - 1, target);
                if (mid == -1) {
                    return false;
                }
                if (matrix[mid][column] == target) {
                    return true;
                }
                row = mid;
                horizon = true;
            }
        }
    }

    // 返回row内最后一个小于等于 target 的column索引，否则返回-1
    public int bSearchRow(int[][] matric, int row, int left, int right, int target) {
        int low = left, high = right;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (matric[row][mid] > target) {
                high = mid - 1;
            } else {
                if (mid == right || matric[row][mid + 1] > target) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    // 返回column内第一个大于等于 target 的row索引，否则返回-1
    public int bSearchColumn(int[][] matric, int column, int top, int down, int target) {
        int low = top, high = down;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (matric[mid][column] < target) {
                low = mid + 1;
            } else {
                if (mid == top || matric[mid - 1][column] < target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     * <p>
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int low = 0, high = matrix.length - 1, mid, row = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (matrix[mid][0] < target) {
                if (mid + 1 < matrix.length && matrix[mid + 1][0] <= target) {
                    low = mid + 1;
                } else {
                    row = mid;
                    break;
                }
            } else if (matrix[mid][0] > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        if (row == -1) {
            return false;
        }
        low = 0;
        high = matrix[row].length - 1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (matrix[row][mid] < target) {
                if (mid + 1 < matrix[row].length && matrix[row][mid + 1] <= target) {
                    low = mid + 1;
                } else {
                    return false;
                }
            } else if (matrix[row][mid] > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
