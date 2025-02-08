package org.practice.pointer;

/**
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定数组 nums = [1,1,2],
 *
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 *
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class DeleteArrRepeatItem {
    public int f(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int i = 0;
        int j = 1;
        while (j < arr.length) {
            while (j < arr.length && arr[i] == arr[j]) {
                j++;
            }
            if (j < arr.length) {
                if (j - i == 1) {
                    j++;
                    i++;
                } else {
                    i++;
                    arr[i] = arr[j];
                    j++;
                }
            }
        }
        if (j-i==1) {
            return arr.length;
        } else {
            return i+1;
        }
    }
}
