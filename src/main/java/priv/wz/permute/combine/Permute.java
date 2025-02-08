package priv.wz.permute.combine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个不含重复数字的数组 nums ，返回其所有可能的全排列 。你可以按任意顺序返回答案。
 */
public class Permute {

    private boolean[] visit;
    private int[] nums;
    private int[] cur;
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        if (nums == null || nums.length == 0) {
            return ans;
        }
        this.nums = nums;
        this.cur = new int[nums.length];
        this.visit = new boolean[nums.length];
        backtrace(0);
        return ans;
    }

    private void backtrace(int i) {
        if (i == nums.length) {
            ans.add(Arrays.stream(cur).boxed().collect(Collectors.toList()));
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            if (!visit[j]) {
                cur[i] = nums[j];
                visit[j] = true;
                backtrace(i + 1);
                visit[j] = false;
            }
        }
    }

    /**
     *
     */
    public List<List<Integer>> permute2(int[] nums) {
        util(nums, 0);
        return ans;
    }

    private void util(int[] nums, int index) {
        if (index == nums.length) {
            ans.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            util(nums, index + 1);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
