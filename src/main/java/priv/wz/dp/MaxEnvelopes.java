package priv.wz.dp;

/**
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 注意：不允许旋转信封。
 *
 * 示例 1：
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *
 * 示例 2：
 * 输入：envelopes = [[1,1],[1,1],[1,1]]
 * 输出：1
 */
public class MaxEnvelopes {
    // 一种方法是将 信封按宽度排个序，这样就变成了找 高度最长的递增子序列问题
    // 第二种就是dp
    public int maxEnvelopes(int[][] envelopes) {
        // dp[i][0]表示套第i个信封可以得到的最大值
        // dp[i][1]表示不套第i个信封可以得到的最大值
        int[][] dp = new int[envelopes.length][2];
        return 0;
    }
}
