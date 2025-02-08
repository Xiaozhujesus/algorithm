package priv.wz.permute.combine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序返回所有不重复的全排列。
 */
public class PermuteUnique {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        return util(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    private List<List<Integer>> util(List<Integer> arr) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr.size() == 1) {
            ans.add(arr);
            return ans;
        }
        for (int i = 0; i < arr.size(); i++) {
            if (i == 0 || !arr.get(i).equals(arr.get(i - 1))) {
                int first = arr.get(i);
                List<Integer> tmp = new ArrayList<>(arr);
                tmp.remove(i);
                List<List<Integer>> sub = util(tmp);
                for (List<Integer> item : sub) {
                    item.add(0, first);
                }
                ans.addAll(sub);
            }
        }
        return ans;
    }

    /**
     * 该题是正常排练的变形，与正常排列不同的是：每一轮确定 cur 位置值的 for 循环选的值需要保证不能与
     * 之前选的重复而由于回溯搜索的特点，每次 for 循环都会恢复之前选的值的 visit 标志位，因此
     * i > 0 && nums[i] == nums[i - 1] && !visit[i - 1]) 这种情况一定是当前循环上次选过的
     * 如下算法适用于从 nums 中选出个数小于 nums.length 个数的排列
     */
    private int[] nums;
    private boolean[] visit;
    private int[] perm;
    private List<List<Integer>> ans;

    public List<List<Integer>> permuteUnique2(int[] nums) {
        this.nums = nums;
        ans = new ArrayList<>();
        perm = new int[nums.length];
        visit = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(0);
        return ans;
    }

    private void backtrack(int cur) {
        if (cur == nums.length) {
            ans.add(Arrays.stream(perm).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (visit[i] || (i > 0 && nums[i] == nums[i - 1] && !visit[i - 1])) {
                continue;
            }
            perm[cur] = nums[i];
            visit[i] = true;
            backtrack(cur + 1);
            visit[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new PermuteUnique().permuteUnique2(new int[]{1, 2, 2, 2}));
    }
}