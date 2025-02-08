package priv.wz.graph;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为'X'。
 * 任何不在边界上，或不与边界上的'O'相连的'O'最终都会被填充为'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class RoundArea {
    public void f(char[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return;
        }
        int m = arr.length;
        int n = arr[0].length;
        // 从边界以 'O' 为 root 深度搜素（或广度优先），将 'O' 标记为 '#'
        for (int i = 0; i < m; i++) {
            if (arr[i][0] == 'O') {
                dfs(arr, i, 0);
            }
            if (arr[i][n - 1] == 'O') {
                dfs(arr, i, n - 1);
            }
        }
        for (int i = 1; i < n - 1; i++) {
            if (arr[0][i] == 'O') {
                dfs(arr, 0, i);
            }
            if (arr[m - 1][i] == 'O') {
                dfs(arr, m - 1, i);
            }
        }

        // 将'#'还原为'O'，将'O'改为'X'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == '#') {
                    arr[i][j] = 'O';
                } else if (arr[i][j] == 'O') {
                    arr[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] arr, int i, int j) {
        if (i < 0 || j < 0 || i == arr.length || j == arr[0].length || arr[i][j] == 'X' || arr[i][j] == '#') {
            return;
        }
        arr[i][j] = '#';
        dfs(arr, i - 1, j);
        dfs(arr, i + 1, j);
        dfs(arr, i, j - 1);
        dfs(arr, i, j + 1);
    }
}
