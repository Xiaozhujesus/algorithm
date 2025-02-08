package priv.wz.dp;

/**
 * 有 N 件物品和一个最多能背重量为 W 的背包。第i件物品的重量是 weight[i]，得到的价值是 value[i]
 * 每件物品只能用一次，求解将哪些物品装入背包里物品价值总和最大。
 */
public class Bag {
    public int max(int bagSize, int[] weight, int[] value) {
        int wlen = weight.length;
        //定义dp数组：dp[i][j]表示背包容量为j时，前i个物品能获得的最大价值
        int[][] dp = new int[wlen][bagSize + 1];
        //初始化：背包容量为0时，能获得的价值都为0
        for (int i = 0; i < wlen; i++) {
            dp[i][0] = 0;
        }
        if (weight[0] <= bagSize) {
            for (int i = weight[0]; i <= bagSize; i++) {
                dp[0][i] = value[0];
            }
        }

        for (int i = 1; i < wlen; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (weight[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        return dp[wlen - 1][bagSize];
    }

    // 优化，第i行只与第i-1行有关，但要从后往前填充
    public int max2(int bagSize, int[] weight, int[] value) {
        int wlen = weight.length;
        int[] dp = new int[bagSize + 1];
        if (weight[0] <= bagSize) {
            for (int i = weight[0]; i <= bagSize; i++) {
                dp[i] = value[0];
            }
        }
        for (int i = 1; i < wlen; i++) {
            for (int j = bagSize; j >= 0; j--) {
                if (weight[i] <= j) {
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            }
        }
        return dp[bagSize];
    }
}
