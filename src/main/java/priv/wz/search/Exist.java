package priv.wz.search;

/**
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class Exist {
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0 || board.length == 0 || board[0].length == 0) {
            return false;
        }
        int row = board.length;
        int column = board[0].length;
        boolean[][] walked = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                walked[i][j] = false;
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (search(board, walked, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean search(char[][] board, boolean[][] walked, String word, int index, int i, int j) {
        if (board[i][j] == word.charAt(index)) {
            walked[i][j] = true;
            if (index == word.length() - 1) {
                return true;
            }
            if (i - 1 >= 0 && !walked[i - 1][j] && search(board, walked, word, index + 1, i - 1, j)) {
                return true;
            } else if (i + 1 < board.length && !walked[i + 1][j] && search(board, walked, word, index + 1, i + 1, j)) {
                return true;
            } else if (j - 1 >= 0 && !walked[i][j - 1] && search(board, walked, word, index + 1, i, j - 1)) {
                return true;
            } else if (j + 1 < board[0].length && !walked[i][j + 1] && search(board, walked, word, index + 1, i, j + 1)) {
                return true;
            }
            walked[i][j] = false;
            return false;
        } else {
            return false;
        }
    }
}
