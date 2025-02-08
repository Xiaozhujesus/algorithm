package priv.wz.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在四个元素 a，b，c，d，使得 a + b + c + d = 0 ？
 * 请你找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：答案中不可以包含重复的四元组。
 */
public class FourNumSum {

    public List<List<Integer>> solution(int[] nums, int target) {
        List list = new LinkedList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return list;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                int low = j + 1;
                int high = nums.length - 1;
                while (low < high) {
                    int tmp = nums[i] + nums[j] + nums[low] + nums[high];
                    if (tmp < target) {
                        low++;
                    } else if (tmp > target) {
                        high--;
                    } else {
                        List<Integer> cur = Arrays.asList(nums[i], nums[j], nums[low], nums[high]);
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
                while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                    j++;
                }
            }
        }
        return list;
    }
}
