package priv.wz.dp;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
 */
public class Stock1 {
    public int oneDeal(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int target = 0;
        int beforeLowestPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < beforeLowestPrice) {
                beforeLowestPrice = prices[i];
            } else {
                int diff = prices[i] - beforeLowestPrice;
                if (diff > target) {
                    target = diff;
                }
            }
        }
        return target;
    }
}
