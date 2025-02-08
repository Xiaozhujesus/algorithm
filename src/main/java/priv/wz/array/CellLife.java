package org.practice.array;

/**
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），
 * 或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * <p>
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个
 * 细胞所形成的，其中细胞的出生和死亡是同时发生的
 *
 * 与棋盘设置0那题一样
 */
public class CellLife {
    private int[] x = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    private int[] y = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

    public void f(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
            return;
        }
        int m = arr.length;
        int n = arr[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                checkLife(arr, m, n, i, j);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] > 1) {
                    arr[i][j] -= 2;
                }
            }
        }
    }

    /**
     * 如果原来活的，之后死了，设置2；如果原来死的，之后活了，设置3；其他情况不变
     *
     * @param arr
     * @param m
     * @param n
     * @param i
     * @param j
     */
    private void checkLife(int[][] arr, int m, int n, int i, int j) {
        int sum = 0;
        for (int p = 0; p < x.length; p++) {
            int nexti = i + x[p];
            int nextj = j + y[p];
            if (nexti >= 0 && nexti < m && nextj >= 0 && nextj < n) {
                if (arr[nexti][nextj] == 1 || arr[nexti][nextj] == 2) {
                    sum++;
                }
            }
        }
        if (arr[i][j] == 0 && sum == 3) {
            arr[i][j] = 3;
        } else if (arr[i][j] == 1 && sum != 2 && sum != 3) {
            arr[i][j] = 2;
        }
    }
}
