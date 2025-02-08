package priv.wz.permute.combine;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class GeneralCombine {

    /**
     * 更一般形式的组合问题：有 n1 个 a，n2 个 b，n3 个 c，从 n1+n2+n3 中选出 m 个
     * 下面 DP 解法是错的，想法是在上层循环中取第一个元素保证不重复来保证结果的不重复，比如
     * n1,n2,n3|m =
     * a*(n1-1,n2,n3|m-1) +
     * b*(n1,n2-1,n3|m-1) +
     * c*(n1,n2,n3-1|m-1)
     * 但是这种方式实际上每层的子问题之间是重复的，比如在集合 {a,b} 中取 2 个元素的组合
     * 按上述算法执行得到：
     * 1,1|2 =
     * a*(0,1|1) +
     * b*(1,0|1)
     * =
     * {a,b} +
     * {b,a}
     * <p>
     * 这种思路可以用来求解一般形式的排列题
     * <p>
     * 组合问题的关键是每个元素的个数在结果中不同才能保证结果不重复
     */

    // 每个元素是什么
    private int[] nums;
    // 记录每个元素剩余多少
    private int[] status;
    // 记录当前每个元素被选择了多少
    private int[] cur;
    private int m;
    private List<List<Integer>> combines = new ArrayList<>();

    public List<List<Integer>> combine(int[] num, int m) {
        // 输入预处理
        Map<Integer, Integer> statistic = new HashMap<>();
        for (int i : num) {
            statistic.put(i, statistic.getOrDefault(i, 0) + 1);
        }
        nums = new int[statistic.size()];
        status = new int[statistic.size()];
        cur = new int[statistic.size()];
        this.m = m;
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : statistic.entrySet()) {
            nums[i] = entry.getKey();
            status[i] = entry.getValue();
            i++;
        }
        search(0, m);
        return combines;
    }

    /**
     * 搜索成功，添加结果，当 statusIndex 为 status.length 时只添加 cur，否则
     * 分为两部分添加：cur[0 - statusIndex) 和 status[statusIndex - status.length)
     *
     * @param statusIndex
     */
    private void addResult(int statusIndex) {
        List<Integer> ans = new ArrayList<>(m);
        for (int i = 0; i < statusIndex; i++) {
            int c = cur[i];
            while (c > 0) {
                ans.add(nums[i]);
                c--;
            }
        }
        for (int i = statusIndex; i < status.length; i++) {
            int c = status[i];
            while (c > 0) {
                ans.add(nums[i]);
                c--;
            }
        }
        combines.add(ans);
    }

    /**
     * 回溯法搜索
     *
     * @param cur_pos
     * @param wanted
     * @return
     */
    private void search(int cur_pos, int wanted) {
        // 回溯结束条件
        if (cur_pos == status.length) {
            if (wanted == 0) {
                addResult(status.length);
            }
            return;
        }
        int sum = Arrays.stream(IntStream.range(cur_pos, status.length).map(i -> status[i]).toArray()).sum();
        if (sum < wanted) {
            return;
        } else if (sum == wanted) {
            addResult(cur_pos);
            return;
        }
        if (wanted == 0) {
            addResult(status.length);
            return;
        }
        int choose = Math.min(status[cur_pos], wanted);
        /**
         * 要想选择的结果不重复，需要保证最终结果中每个元素的个数不同，比如 1 个 a 的结果和 2 个 a 的结果对于组合来说一定不同
         */
        while (choose >= 0) {
            cur[cur_pos] = choose;
            status[cur_pos] -= choose;
            // 有重复，可以 DP
            search(cur_pos + 1, wanted - choose);
            status[cur_pos] += choose;
            // 由于 choose 最终为 0，因此 cur[cur_pos] 会被恢复为 0，不需要像 status[cur_pos] 被显示恢复
            choose--;
        }
    }

    public static void main(String[] args) {
        System.out.println(new GeneralCombine().combine(new int[]{1, 1, 2, 2, 3, 3}, 3));
        System.out.println(new GeneralCombine().combine(new int[]{1, 2, 3, 4, 5, 6}, 2));
    }
}



