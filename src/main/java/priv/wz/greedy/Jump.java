package priv.wz.greedy;

/**
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则返回 false 。
 */
public class Jump {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }
        int maxPos = -1, start = 0, end = 0;
        while (end < nums.length - 1) {
            for (int i = start; i <= end; i++) {
                maxPos = Math.max(maxPos, i + nums[i]);
            }
            if (maxPos <= end) {
                return false;
            }
            start = end + 1;
            end = maxPos;
        }
        return true;
    }
}
