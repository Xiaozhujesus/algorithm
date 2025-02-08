package priv.wz.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断nums中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 */
public class ThreeNumSum {

    public List<List<Integer>> threeNumSum(int[] nums, int target) {
        List<List<Integer>> list = new LinkedList<>();
        if (nums == null || nums.length < 3) {
            return list;
        }
        Arrays.sort(nums);
        for (int i = 0, end = nums.length - 2; i < end; i++) {
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                int tmp = nums[i] + nums[low] + nums[high];
                if (tmp < target) {
                    low++;
                } else if (tmp > target) {
                    high--;
                } else {
                    List<Integer> cur = Arrays.asList(nums[i], nums[low], nums[high]);
                    list.add(cur);
                    while (low + 1 < high && nums[low] == nums[low + 1]) {
                        low++;
                    }
                    while (high - 1 > low && nums[high] == nums[high - 1]) {
                        high--;
                    }
                    low++;
                    high--;
                }
            }
            while (i + 1 < end && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return list;
    }
}
