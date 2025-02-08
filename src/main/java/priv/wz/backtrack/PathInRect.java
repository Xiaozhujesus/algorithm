package priv.wz.backtrack;

/**
 * 判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向上下左右移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子
 */
public class PathInRect {
    int[][] next = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int rows, cols;
    int[][] rect;
    boolean[][] mark;
    /**
     *
     * @param arr 以一维数组方式给出矩阵
     * @param row 矩阵的行数
     * @param col 矩阵的列数
     * @param str 搜索的字符串
     * @return
     */
    public boolean hasPath(char[] arr, int row, int col, char[] str) {
        rows = row;
        cols = col;
        buildRect(arr, row, col);
        mark = new boolean[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (search(r, c, str,0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search(int row, int col, char[] str, int pathLen) {
        if (pathLen == str.length) {
            return true;
        }
        if (row < 0 || row >= rows || col < 0 || col >= cols || mark[row][col] || rect[row][col] != str[pathLen]) {
            return false;
        }
        mark[row][col] = true;
        for (int[] n : next) {
            if (search(row+n[0], col+n[1], str, pathLen+1)) {
                return true;
            }
        }
        mark[row][col] = false;
        return false;
    }

    private void buildRect(char[] arr, int row, int col) {
        int index = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                rect[r][c] = arr[index];
                index++;
            }
        }
    }
}
