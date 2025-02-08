package priv.wz.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个无重复元素的正整数数组 candidates 和一个目标整数 target，找出 candidates 中可以使数字和
 * 为目标数 target 的所有不同组合并以列表形式返回。你可以按任意顺序返回这些组合。
 * candidates 中的 同一个数字可以无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
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
        walk(0);
        return ans;
    }

    void walk(int index) {
        if (target == 0) {
            addAns();
            return;
        }
        if (index == candidates.length) {
            return;
        }
        for (int i = 0, end = target / candidates[index]; i <= end; i++) {
            count[index] += i;
            target -= candidates[index] * i;
            walk(index + 1);
            target+= candidates[index] * i;
            count[index] -= i;
        }
    }

    private void addAns() {
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < candidates.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                tmp.add(candidates[i]);
            }
        }
        ans.add(tmp);
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
        if (target >= candidates[idx]) {
            combine.add(candidates[idx]);
            dfs(target - candidates[idx], combine, idx);
            combine.remove(combine.size() - 1);
        }
    }
}
