package priv.wz.array;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3。 不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 3) {
            return nums.length;
        }
        int end = 0, cur_value, cur_pos = 0;
        while (cur_pos < nums.length) {
            cur_value = nums[cur_pos++];
            nums[end++] = cur_value;
            if (cur_pos < nums.length && nums[cur_pos] == cur_value) {
                nums[end++] = cur_value;
                cur_pos++;
            }
            while (cur_pos < nums.length && nums[cur_pos] == cur_value) {
                cur_pos++;
            }
        }
        return end;
    }

    /**
     * 只出现一次
     */
    public int removeDuplicates1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int end = 0, curValue = nums[0], curPos = 0;
        while (curPos < nums.length) {
            curValue = nums[curPos];
            nums[end++] = curValue;
            while (curPos < nums.length && nums[curPos] == curValue) {
                curPos++;
            }
        }
        return end;
    }
}
