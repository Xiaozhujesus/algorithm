package priv.wz.array;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class NumIslands {

    private char[][] grid;
    private boolean[][] visit;
    private int length;
    private int width;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        length = grid.length;
        width = grid[0].length;
        this.visit = new boolean[length][width];
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (!visit[i][j] && grid[i][j] == '1') {
                    ans++;
                    visit(i, j);
                }
            }
        }
        return ans;
    }

    private void visit(int x, int y) {
        if (x < 0 || x == length || y < 0 || y == width) {
            return;
        }
        if (grid[x][y] == '0' || visit[x][y]) {
            return;
        }
        visit[x][y] = true;
        visit(x - 1, y);
        visit(x + 1, y);
        visit(x, y - 1);
        visit(x, y + 1);
    }
}
