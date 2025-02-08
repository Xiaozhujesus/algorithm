package priv.wz.array;

/**
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * 请你找出并返回只出现一次的那个数。
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * <p>
 * 示例 1:
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 *
 * 这题更一般的描述是数组只有一个出现一次的元素，其余元素都出现两次，而且出现两次的元素相邻排列
 */
public class SingleNonDuplicate {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if ((high - mid + 1) % 2 == 0) {
                if (nums[mid] == nums[mid + 1]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] == nums[mid + 1]) {
                    low = mid;
                } else {
                    high = mid;
                }
            }
        }
        return nums[low];
    }
}
