package priv.wz.backtrack;

/**
 * 给定一个m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class Exist {

    private char[][] board;
    private boolean[][] visit;
    private String word;

    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0 || board.length == 0 || board[0].length == 0) {
            return false;
        }
        this.board = board;
        this.word = word;
        int row = board.length;
        int column = board[0].length;
        this.visit = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (search(0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param index word 的第几个字母
     * @param i     棋盘位置
     * @param j     棋盘位置
     * @return
     */
    public boolean search(int index, int i, int j) {
        if (board[i][j] == word.charAt(index)) {
            visit[i][j] = true;
            if (index == word.length() - 1) {
                return true;
            }
            if (i - 1 >= 0 && !visit[i - 1][j] && search(index + 1, i - 1, j)) {
                return true;
            } else if (i + 1 < board.length && !visit[i + 1][j] && search(index + 1, i + 1, j)) {
                return true;
            } else if (j - 1 >= 0 && !visit[i][j - 1] && search(index + 1, i, j - 1)) {
                return true;
            } else if (j + 1 < board[0].length && !visit[i][j + 1] && search(index + 1, i, j + 1)) {
                return true;
            }
            visit[i][j] = false;
            return false;
        } else {
            return false;
        }
    }
}
