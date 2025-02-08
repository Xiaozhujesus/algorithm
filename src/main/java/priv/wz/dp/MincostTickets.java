package priv.wz.dp;

/**
 * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行
 * 在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出，每一项是一个从 1 到 365 的整数
 * 火车票有 三种不同的销售方式 ：
 * 一张 为期一天 的通行证售价为 costs[0] 美元；
 * 一张 为期七天 的通行证售价为 costs[1] 美元；
 * 一张 为期三十天 的通行证售价为 costs[2] 美元
 * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证，那么我们可以连着旅行 7 天：
 * 第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天
 * 返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费
 * <p>
 * 示例 1：
 * <p>
 * 输入：days = [1,4,6,7,8,20], costs = [2,7,15]
 * 输出：11
 * 解释：
 * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
 * 在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效
 * 在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效
 * 在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效
 * 你总共花了 $11，并完成了你计划的每一天旅行
 */
public class MincostTickets {

    private int[] days;
    private int[] costs;
    private int[] cross;

    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        this.cross = new int[]{1, 7, 30};

        int[] dp = new int[days.length];
        dp[0] = Math.min(costs[0], costs[1]);
        dp[0] = Math.min(dp[0], costs[2]);
        for (int i = 1; i < days.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < costs.length; j++) {
                int begin = begin(cross[j], i);
                if (begin == 0) {
                    min = Math.min(min, costs[j]);
                } else {
                    min = Math.min(min, dp[begin - 1] + costs[j]);
                }
            }
            dp[i] = min;
        }
        return dp[days.length - 1];
    }

    /**
     * 从 days[end] 开始往前，cross 能到达的最远 days 的索引
     */
    private int begin(int cross, int end) {
        int begin = days[end] + 1 - cross;
        for (int index = end; index >= 0; index--) {
            if (days[index] < begin) {
                return index + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new MincostTickets().mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{7, 2, 15}));
    }
}
