package priv.wz.dp;

/**
 * 给定一个非负整数数组 nums ，你最初位于数组的第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 示例1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * <p>
 * 示例2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 */
public class CanJump {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        int closedCan = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= closedCan) {
                closedCan = 1;
            } else {
                closedCan++;
            }
        }
        return closedCan == 1;
    }

    public boolean canJump2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        boolean flag[] = new boolean[nums.length];
        flag[0] = true;
        for (int i = 0; i < nums.length; i++) {
            if (flag[i]) {
                for (int j = 1; j <= nums[i]; j++) {
                    if (i + j < flag.length) {
                        flag[i + j] = true;
                    }
                }
            }
        }
        return flag[nums.length - 1];
    }
}
