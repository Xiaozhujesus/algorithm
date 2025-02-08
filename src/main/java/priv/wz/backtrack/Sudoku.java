package priv.wz.backtrack;

/**
 * 数独问题
 * 数独盘面是个九宫，每一宫又分为九个小格，在其他的空格上填入1-9的数字。使1-9每个数字在每一行、每一列和每一宫中都只出现一次，不重复
 */
public class Sudoku {
    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int x, int y) {
        if (x == 9) {
            return true;
        }
        if (y == 9) {
            return dfs(board, x + 1, 0);
        }
        if (board[x][y] != '.') {
            return dfs(board, x, y + 1);
        }
        for (char c = '1'; c <= '9'; c++) {
            if (valid(board, x, y, c)) {
                board[x][y] = c;
                if (dfs(board, x, y + 1)) {
                    return true;
                }
                board[x][y] = '.';
            }
        }
        return false;
    }

    private boolean valid(char[][] board, int x, int y, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == c) {
                return false;
            }
            if (board[i][y] == c) {
                return false;
            }
            if (board[x / 3 * 3 + i / 3][y / 3 * 3 + i % 3] == c) {
                return false;
            }
        }
        return true;
    }
}
