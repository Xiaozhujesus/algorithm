package priv.wz.array;

/**
 * 给你一个数组，将数组中的元素向右轮转 k个位置，其中k是非负数。
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 */
public class RotateN {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k == 0) {
            return;
        }
        int r = k % nums.length;
        int[] tmp = new int[r];
        System.arraycopy(nums, nums.length - r, tmp, 0, r);
        int cur = nums.length - r - 1;
        while (cur >= 0) {
            nums[cur + r] = nums[cur];
            cur--;
        }
        System.arraycopy(tmp, 0, nums, 0, r);
    }
}
