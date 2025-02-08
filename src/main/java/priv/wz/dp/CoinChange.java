package priv.wz.dp;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * 跟爬楼梯一样，就是爬一层或两层变成了不同面额的硬币
 */
public class CoinChange {
    private int[] coins;
    private int[] dp;

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        this.dp = new int[amount + 1];
        this.coins = coins;
        return dp(amount);
    }

    private int dp(int amount) {
        if (dp[amount] != 0) {
            return dp[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin < amount) {
                int tmp = dp(amount - coin);
                if (tmp != -1 && tmp < min) {
                    min = tmp;
                }
            } else if (coin == amount) {
                dp[amount] = 1;
                return 1;
            }
        }
        dp[amount] = min == Integer.MAX_VALUE ? -1 : min + 1;
        return dp[amount];
    }

    /**
     * 斐波那契数列
     */
    private int dp2(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];

        for (int curAmount = 1; curAmount <= amount; curAmount++) {
            int min = -1;
            for (int coin : coins) {
                if (coin < curAmount && dp[curAmount - coin] != 0) {
                    if (min == -1) {
                        min = dp[curAmount - coin];
                    } else {
                        min = Math.min(dp[curAmount - coin], min);
                    }
                } else if (coin == curAmount) {
                    min = 0;
                    break;
                }
            }
            dp[curAmount] = min + 1;
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new CoinChange().dp2(new int[]{2}, 11));
    }
}
