package priv.wz.array;

/**
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 *
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 */
public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        int[] ans = new int[nums.length];
        int end = 0;
        for (int cur : nums) {
            int i = 0;
            for (; i < end; i++) {
                if (cur <= ans[i]) {
                    ans[i] = cur;
                    break;
                }
            }
            if (i == end) {
                ans[end] = cur;
                end++;
                if (end == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    // 最长递增子序列的特例
    public boolean increasingTriplet2(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }
}
