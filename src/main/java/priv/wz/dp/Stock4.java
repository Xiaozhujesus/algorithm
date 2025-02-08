package priv.wz.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数数组prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * <p>
 * 示例 2：
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 */
public class Stock4 {
    public int maxProfit(int k, int[] prices) {
        if (k == 0) {
            return 0;
        }
        int sum = 0;
        List<Integer> compress = new ArrayList<>();
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prices[i - 1];
            if ((profit < 0 && sum > 0) || (profit > 0 && sum < 0)) {
                compress.add(sum);
                sum = profit;
            } else {
                sum += profit;
            }
        }
        int[] dp = new int[compress.size()];
        for (int i = 0; i < dp.length; i++) {
            if (compress.get(i) < 0) {
                if (i == 0) {
                    dp[i] = 0;
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        return 1;
    }
}
