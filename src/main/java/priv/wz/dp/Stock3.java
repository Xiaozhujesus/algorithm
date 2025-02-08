package priv.wz.dp;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class Stock3 {

    // 利用 Stock1 问题的思路
    public int maxProfit(int[] prices) {
        int[] preMaxProfit = new int[prices.length];
        int preMin = prices[0], targetMax = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < preMin) {
                preMin = prices[i];
            } else {
                int tmp = prices[i] - preMin;
                if (tmp > targetMax) {
                    targetMax = tmp;
                }
            }
            preMaxProfit[i] = targetMax;
        }
        int[] afterMaxProfit = new int[prices.length];
        int afterMax = prices[prices.length - 1];
        targetMax = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            if (prices[i] > afterMax) {
                afterMax = prices[i];
            } else {
                int tmp = afterMax - prices[i];
                if (tmp > targetMax) {
                    targetMax = tmp;
                }
            }
            afterMaxProfit[i] = targetMax;
        }
        int ans = afterMaxProfit[0];
        for (int i = 0, end = prices.length - 1; i < end; i++) {
            int tmp = preMaxProfit[i] + afterMaxProfit[i + 1];
            if (tmp > ans) {
                ans = tmp;
            }
        }
        return ans;
    }

    /**
     * dp
     * 由于我们最多可以完成两笔交易，因此在任意一天结束之后，我们会处于以下五个状态中的一种：
     *
     * 1、未进行过任何操作；
     * 2、只进行过一次买操作；
     * 3、进行了一次买操作和一次卖操作，即完成了一笔交易；
     * 4、在完成了一笔交易的前提下，进行了第二次买操作；
     * 5、完成了全部两笔交易。
     * 由于第一个状态的利润显然为 0，因此我们可以不用将其记录。
     *
     * buy1[i] 表示第i天结束只进行过一次买操作的最大利润
     * sell1[i] 表示第i天结束完成了一笔交易的最大利润
     * buy2[i] 表示第i天结束在完成了一笔交易的前提下，进行了第二次买操作的最大利润
     * sell2[i] 表示第i天结束完成了全部两笔交易的最大利润
     *
     * 状态转移方程如下代码所示，由于 i 的状态只与 i-1 有关，因此优化成使用变量存储
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}
