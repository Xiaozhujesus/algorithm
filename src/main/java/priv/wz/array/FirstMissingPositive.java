package priv.wz.array;

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * 输入：nums = [1,2,0]
 * 输出：3
 * 解释：范围 [1,2] 中的数字都在数组中。
 * 与 minMissNum 题目一样
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        // 归位，比如 1 应该放在 nums[0]，不能放在 nums[1]，原因见下面的解法
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && /*当前位置值与对应位置值相同，防止无需循环*/ nums[i] != nums[nums[i] - 1]) {
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    // 归位 1 在 nums[1] 的对应方式有问题，对于输入 [2,1]，会返回 2，实际应该返回 3
    public int firstMissingPositive1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 0 && nums[i] < nums.length && /*当前位置值与对应位置值相同，防止无需循环*/ nums[i] != nums[nums[i]]) {
                int tmp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = tmp;
            }
        }
        if (nums.length == 1) {
            return nums[0] == 1 ? 2 : 1;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }
}
