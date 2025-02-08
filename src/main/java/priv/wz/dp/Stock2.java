package priv.wz.dp;

/**
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 可以当日买入或卖出股票，但你在任何时候最多只能持有一股股票。返回你能获得的最大利润。
 */
public class Stock2 {

    /**
     * dp
     */
    public int maxProfit(int[] prices) {
        // 第i天结束，持有股票所能获得的最大利润
        int[] hold = new int[prices.length];
        hold[0] = -prices[0];
        // 第i天结束，不持有股票所能获得的最大利润
        int[] sold = new int[prices.length];
        sold[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            hold[i] = Math.max(hold[i - 1], sold[i - 1] - prices[i]);
            sold[i] = Math.max(hold[i - 1] + prices[i], sold[i - 1]);
        }
        return sold[prices.length];
    }

    /**
     * 空间优化
     */
    public int maxProfit2(int[] prices) {
        int preHold = -prices[0];
        int preSold = 0;
        for (int i = 1; i < prices.length; i++) {
            int curHold = Math.max(preHold, preSold - prices[i]);
            int curSold = Math.max(preHold + prices[i], preSold);
            preHold = curHold;
            preSold = curSold;
        }
        return preSold;
    }

    /**
     * 贪心，吃掉每一笔能吃的利润
     */
    public int maxProfit4(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

    /**
     * 整数 fee 代表了交易股票的手续费用，买入再卖出算一笔费用
     */
    public int maxProfit(int[] prices, int fee) {
        int ans = 0;
        // 第i天获得的最大利润，dp[0][i] 表示未持有股票，dp[1][i] 表示持有股票
        int[][] dp = new int[2][prices.length];
        dp[0][0] = 0;
        dp[1][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + prices[i] - fee);
            dp[1][i] = Math.max(dp[0][i - 1] - prices[i], dp[1][i - 1]);
        }
        return dp[0][prices.length - 1];
    }
    // 空间可以优化
}
