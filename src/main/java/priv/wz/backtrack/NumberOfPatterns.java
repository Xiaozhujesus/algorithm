package priv.wz.backtrack;

/**
 * 合法的手机解锁方式个数，合法的定义为：
 * 屏幕为 3*3 的网格，第一行 1、2、3，第二行 4、5、6，第三行 7、8、9
 * 给定两个整数 m 和 n，1 <= m <= n <= 9
 * 1、最少经过 m 个点，最多 n 个
 * 2、手势中不能有重复的点
 * 3、相邻两个点直接不能经过未经过的点
 * 4、点的顺序不同则为不同解锁方式
 */
public class NumberOfPatterns {

    private boolean[] visit = new boolean[10];
    private int[][] middle = new int[10][10];
    private int ans = 0;
    private int m;
    private int n;

    public NumberOfPatterns() {
        middle[1][3] = middle[3][1] = 2;
        middle[1][9] = middle[9][1] = 5;
        middle[1][7] = middle[7][1] = 4;
        middle[2][8] = middle[8][2] = 5;
        middle[3][7] = middle[7][3] = 5;
        middle[3][9] = middle[9][3] = 6;
        middle[4][6] = middle[6][4] = 5;
        middle[7][9] = middle[9][7] = 8;
    }

    public int numberOfPatterns(int m, int n) {
        this.m = m;
        this.n = n;
        walk(0, 0);
        return ans;
    }

    void walk(int dot, int cur) {
        if (dot > n) {
            return;
        }
        if (dot >= m && dot <= n) {
            ans++;
        }
        visit[cur] = true;
        for (int i = 1; i < 10; i++) {
            if (!visit[i] && valid(cur, i)) {
                walk(dot + 1, i);
            }
        }
        visit[cur] = false;
    }

    private boolean valid(int i, int j) {
        if (middle[i][j] == 0) {
            return true;
        }
        return visit[middle[i][j]];
    }
}
