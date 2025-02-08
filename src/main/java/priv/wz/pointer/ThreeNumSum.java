package priv.wz.pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 */
public class ThreeNumSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] <= 0) {
                int p = i + 1, q = nums.length - 1;
                while (p < q) {
                    int sum = nums[p] + nums[q];
                    if (sum == -nums[i]) {
                        ans.add(Arrays.asList(nums[i], nums[p], nums[q]));
                        while (p < q && nums[p] == nums[p + 1]) {
                            p++;
                        }
                        p++;
                        while (p < q && nums[q] == nums[q - 1]) {
                            q--;
                        }
                        q--;
                    } else if (sum > -nums[i]) {

                        while (p < q && nums[q] == nums[q - 1]) {
                            q--;
                        }
                        q--;
                    } else {
                        while (p < q && nums[p] == nums[p + 1]) {
                            p++;
                        }
                        p++;
                    }
                }
            }
        }
        return ans;
    }
}
