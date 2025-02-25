package priv.wz.array;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0, j = nums.length - 1;
        while (i != j) {
            while (i != j && nums[i] != val) {
                i++;
            }
            while (i != j && nums[j] == val) {
                j--;
            }
            if (i == j) {
                break;
            } else {
                swap(nums, i, j);
            }
        }
        if (nums[i] == val) {
            return i;
        } else {
            return i + 1;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
