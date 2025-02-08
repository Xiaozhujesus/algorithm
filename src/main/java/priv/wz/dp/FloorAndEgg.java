package priv.wz.dp;

/**
 * 你面前有一栋从 1 到 N 共 N 层的楼，然后给你 K 个鸡蛋（K 至少为 1）。现在确定这栋楼存在楼层 0 <= F <= N，在这层楼将鸡蛋扔下去，
 * 鸡蛋恰好没摔碎（高于 F 的楼层都会碎，低于 F 的楼层都不会碎）。现在问你，最坏情况下，你至少要扔几次鸡蛋，才能确定这个楼层 F 呢？
 */
public class FloorAndEgg {
    private int[][] dp;

    public int f(int k, int n) {
        dp = new int[k + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[1][i] = i;
        }
        return util(k, n);
    }

    public int util(int k, int n) {
        if (k == 1) {
            return n;
        }
        if (n == 0) {
            return 0;
        }
        if (dp[k][n] != 0) {
            return dp[k][n];
        } else {
            int res = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                res = Math.min(res, Math.max(util(k - 1, i - 1), util(k, n - i)) + 1);
            }
            return dp[k][n] = res;
        }
    }

    public static void main(String[] args) {
        System.out.println(new FloorAndEgg().f(2, 105));
    }
}
