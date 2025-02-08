package priv.wz.backtrack;

/**
 * 将马随机放在国际象棋的8×8棋盘的某个方格中，马按走棋规则进行移动。要求每个方格只进入一次，走遍棋盘上全部64个方格
 */
public class Knight {
    private final int SIZE = 8;
    private final int END = SIZE * SIZE;
    private int[] stepx = {-1, -1, 1, 1, -2, -2, 2, 2};
    private int[] stepy = {2, -2, 2, -2, 1, -1, 1, -1};
    private int[][] board;

    public void init() {
        board = new int[SIZE][SIZE];
        walk(0, 0, 1);
    }

    private void walk(int i, int j, int cur) {
        //判断是否合法
        if (!valid(i, j)) {
            return;
        }
        board[i][j] = cur;
        //判断递归是否结束
        if (cur == END) {
            printResult();
            board[i][j] = 0;
            return;
        }
        //迭代当前轮次所有情况
        for (int p = 0; p < stepx.length; p++) {
            int nextx = i + stepx[p];
            int nexty = j + stepy[p];
            walk(nextx, nexty, cur + 1);
        }
        //回溯
        board[i][j] = 0;
    }

    private void printResult() {
        System.out.println("result :");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                System.out.print(",");
            }
            System.out.println();
        }
        System.exit(0);
    }

    private boolean valid(int i, int j) {
        return i >= 0 && i < SIZE && j >= 0 && j < SIZE && board[i][j] == 0;
    }

    public static void main(String[] args) {
        Knight k = new Knight();
        k.init();
    }
}
