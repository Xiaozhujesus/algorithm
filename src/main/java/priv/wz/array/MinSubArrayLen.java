package priv.wz.array;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的子数组 [numsl, numsl+1, ..., numsr-1, numsr]
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 */
public class MinSubArrayLen {
    public int minSubArrayLen(int target, int[] nums) {
        int low = 0, high = 0, sum = 0;
        int begin = 0, len = Integer.MAX_VALUE;
        while (high < nums.length) {
            sum += nums[high];
            while (low <= high && sum >= target) {
                len = Math.min(len, high - low + 1);
                begin = low;
                sum -= nums[low];
                low++;
            }
            high++;
        }
        if (len == Integer.MAX_VALUE) {
            return 0;
        }
        return len;
    }
}
