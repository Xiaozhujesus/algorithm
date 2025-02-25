package priv.wz.permute.combine;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 通用的排列问题：从 n 个元素中选 m 个排列，n 个元素可以重复
 */
public class GeneralPermute {
    private List<int[]> permutes = new ArrayList<>();
    // 每个元素是什么
    private int[] item;
    // 记录每个元素剩余多少
    private int[] status;
    // 记录当前每个元素被选择了多少
    private int[] cur;

    public List<int[]> permute(int[] num, int m) {
        // 输入预处理
        Map<Integer, Integer> statistic = new HashMap<>();
        for (int i : num) {
            statistic.put(i, statistic.getOrDefault(i, 0) + 1);
        }
        item = new int[statistic.size()];
        status = new int[statistic.size()];
        cur = new int[m];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : statistic.entrySet()) {
            item[index] = entry.getKey();
            status[index] = entry.getValue();
            index++;
        }
        search(0);
        return permutes;
    }

    /**
     * 回溯法搜索，index 表示当前走到 cur 的那个位置了
     *
     * @param index
     */
    private void search(int index) {
        // 回溯结束条件
        if (index == cur.length) {
            permutes.add(Arrays.copyOf(cur, cur.length));
            return;
        }
        // 由于是排列，因此当前 index 位置只要是不同元素，结果一定不同，循环可用元素将其放置在cur[index]
        for (int i = 0; i < item.length; i++) {
            if (status[i] > 0) {
                status[i]--;
                cur[index] = item[i];
                search(index + 1);
                // 恢复状态
                status[i]++;
            }
        }
    }

    private int[] str2Arr(String str) {
        int[] ans = new int[str.length()];
        char[] ca = str.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            ans[i] = ca[i];
        }
        return ans;
    }

    private String arr2Str(int[] arr) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            ans.append((char)arr[i]);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
//        System.out.println(new GeneralPermute().permute(new int[]{1, 1, 2, 2}, 1));
        GeneralPermute generalPermute = new GeneralPermute();
        int[] hellos = generalPermute.str2Arr("hello");
        List<int[]> ans = generalPermute.permute(hellos, hellos.length);
        for (int[] item : ans) {
            System.out.println(generalPermute.arr2Str(item));
        }
    }
}
