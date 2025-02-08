package priv.wz.permute.combine;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 通用的排列问题：从 n 个元素中选 m 个排列，n 个元素可以重复
 */
public class GeneralPermute {
    private List<List<Integer>> permutes = new ArrayList<>();
    // 每个元素是什么
    private int[] nums;
    // 记录每个元素剩余多少
    private int[] status;
    // 记录当前每个元素被选择了多少
    private int[] cur;

    public List<List<Integer>> permute(int[] num, int m) {
        // 输入预处理
        Map<Integer, Integer> statistic = new HashMap<>();
        for (int i : num) {
            statistic.put(i, statistic.getOrDefault(i, 0) + 1);
        }
        nums = new int[statistic.size()];
        status = new int[statistic.size()];
        cur = new int[m];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : statistic.entrySet()) {
            nums[index] = entry.getKey();
            status[index] = entry.getValue();
            index++;
        }
        search(0);
        return permutes;
    }

    /**
     * 回溯法搜索，index 表示当前走到 cur 的那个位置了
     * @param index
     */
    private void search(int index) {
        // 回溯结束条件
        if (index == cur.length) {
            permutes.add(Arrays.stream(cur).boxed().collect(Collectors.toList()));
            return;
        }
        // 由于是排列，因此当前 index 位置只要是不同元素，结果一定不同，循环可用元素将其放置在cur[index]
        for (int i = 0; i < status.length; i++) {
            if (status[i] > 0) {
                status[i]--;
                cur[index] = nums[i];
                search(index + 1);
                // 恢复状态
                status[i]++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new GeneralPermute().permute(new int[]{1, 1, 2, 2}, 1));
    }
}
