package priv.wz.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合
 * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 输入: candidates = [2], target = 1
 * 输出: []
 * <p>
 * 在 backtrace 目录下也有一道题，与本题相似
 */
public class CombinationSum {

    private int[] count;
    private int[] candidates;
    private int target;
    private List ans = new ArrayList();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return ans;
        }
        this.target = target;
        this.candidates = candidates;
        this.count = new int[candidates.length];
        while (!over()) {
        }
        return ans;
    }


    /**
     * 将每个 candidate[i] 视为一个数的各个位，每次加一进行搜索，超过 target 就进位
     */
    private boolean over() {
        int compare = compare();
        if (compare == 0) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < candidates.length; i++) {
                for (int j = 0; j < count[i]; j++) {
                    tmp.add(candidates[i]);
                }
            }
            ans.add(tmp);
            count[0]++;
        } else if (compare > 0) {
            // 直接返回 false 也可以，但是可以剪枝一下
            int i = 0;
            while (compare() > 0) {
                count[i++] = 0;
                if (i == candidates.length) {
                    return true;
                }
                count[i]++;
            }
        } else {
            count[0]++;
        }
        return false;
    }

    private int compare() {
        int sum = 0;
        for (int i = 0; i < candidates.length; i++) {
            sum += candidates[i] * count[i];
        }
        return sum - target;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new CombinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(lists);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.candidates = candidates;
        List<Integer> combine = new ArrayList<>();
        dfs(target, combine, 0);
        return ans;
    }

    public void dfs(int target, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        // 直接跳过
        dfs(target, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(target - candidates[idx], combine, idx);
            combine.remove(combine.size() - 1);
        }
    }
}
