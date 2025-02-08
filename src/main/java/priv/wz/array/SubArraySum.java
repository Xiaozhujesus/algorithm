package priv.wz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 */
public class SubArraySum {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 前i个数的和 -> 个数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0, curSum = 0;
        // 子数组和 sum[i..j] = sum[j] - sum[i-1]，转换为这种方式后可以避免 n2 复杂度的遍历
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            // 子数组长度至少为 1，因此先查然后将当前放入
            ans += map.getOrDefault(curSum - k, 0);
            map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        }
        return ans;
    }
}
