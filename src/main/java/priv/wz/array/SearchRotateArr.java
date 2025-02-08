package org.practice.array;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 */
public class SearchRotateArr {
    public int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            //先判断中间点是啥情况，mid左侧还是右侧连续递增
            if (nums[0] <= nums[middle]) {
                // 不是在low-mid，就是在mid-high，而根据mid与0的比较结果，得知low-high递增，所以判断target是不是在这个范围内比较简单
                if (target < nums[middle] && target >= nums[low]) {
                    high = middle - 1;
                } else {
                    low = middle + 1;
                }
            } else {
                if (target > nums[middle] && target <= nums[high]) {
                    low = middle + 1;
                } else {
                    high = middle - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 有序数组，数组有可能旋转了，也可能没有旋转，而且有可能有重复数字，找到第一个出现的target的index
     */
//    public int f(int[] arr, int target) {
//        if (arr == null || arr.length == 0) {
//            return -1;
//        }
//        int l = 0, h = arr.length - 1, mid;
//        while (l < h) {
//            mid = (l + h) / 2;
//            if (arr[mid] == target) {
//                return mid;
//            } else if (arr[mid] < target) {
//
//            } else {
//
//            }
//        }
//    }
}