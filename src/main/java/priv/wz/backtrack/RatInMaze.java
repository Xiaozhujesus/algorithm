package priv.wz.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 老鼠走正方形迷宫，从左上到右下，只能往下或往右侧走，是否可以达到终点，输出路径
 */
public class RatInMaze {
    private int n;
    private int[][] board;
    private int[] stepx = {1, 0};
    private int[] stepy = {0, 1};
    private List<List<Pair>> allPath = new LinkedList<>();


    public List start(int[][] board) {
        if (board == null || board[0].length == 0) {
            return allPath;
        }
        this.board = board;
        n = board[0].length;
        List<Pair> path = new LinkedList<>();
        walk(0, 0, path);
        return allPath;
    }

    //如果有结果就返回，不需要输出所有结果，可以给walk函数加一个返回值，表示是否找到路径，然后判断需不需要继续循环
    private void walk(int x, int y, List<Pair> path) {
        if (x == n - 1 && y == n - 1) {
            path.add(new Pair(x, y));
            List<Pair> tmp = new ArrayList<>();
            tmp.addAll(path);
            allPath.add(tmp);
            //backtrack
            path.remove(path.size() - 1);
            return;
        }
        if (valid(x, y)) {
            path.add(new Pair(x, y));
            for (int i = 0; i < stepx.length; i++) {
                int nextx = x + stepx[i];
                int nexty = y + stepy[i];
                walk(nextx, nexty, path);
            }
            path.remove(path.size() - 1);
        }
    }

    private boolean valid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n && board[x][y] > 0;
    }

    class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
