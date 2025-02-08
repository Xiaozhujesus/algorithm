package priv.wz.dp;

/**
 * 假设你正在爬楼梯，需要 n 阶你才能到达楼顶，每次你可以爬 1 或 2 个台阶。
 * 你有多少种不同的方法可以爬到楼顶呢？注意：给定 n 是一个正整数
 */
public class ClimbStairs {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 上面不就是斐波那契数列么
    public int climb(int n) {
        int a = 1, b = 1, c = 2;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
