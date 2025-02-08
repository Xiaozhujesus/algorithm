package priv.wz.dp;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择某一天买入这只股票，并选择在未来的某一个不同的日子卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 这题与最大子段和一样，但最大子段和很难看出来问题与：以某个数结尾的最大子段和是等价的，而以某个数结尾的最大子段和
 * 这个问题比较好解
 */
public class MaxProfit {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int preMin = prices[0];
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > preMin) {
                if (prices[i] - preMin > ans) {
                    ans = prices[i] - preMin;
                }
            } else {
                preMin = prices[i];
            }
        }
        return ans;
    }
}
