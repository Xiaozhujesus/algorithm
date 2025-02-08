package org.practice.array;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 */
public class SearchRange {
    public int[] solution(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return new int[]{-1, -1};
        }
        if (arr[0] > target || arr[arr.length - 1] < target) {
            return new int[]{-1, -1};
        }
        int len = arr.length;
        int res[] = new int[2];
        res[0] = -1;
        res[1] = -1;
        int low1 = 0, high1 = len - 1;
        //查找右边界
        while (low1 <= high1) {
            int middle1 = (low1 + high1) / 2;
            if (arr[middle1] < target)
                low1 = middle1 + 1;
            else if (arr[middle1] > target) {
                high1 = middle1 - 1;
            } else {
                low1 = middle1;
                if (low1 == high1) {
                    break;
                }
            }
        }
        if (low1 > high1) {
            return res;
        } else {
            res[0] = low1;
        }
        int low2 = 0, high2 = len - 1;
        //查找左边界
        while (low2 <= high2) {
            int middle2 = (low2 + high2) / 2;
            if (arr[middle2] > target) {
                high2 = middle2 - 1;
            } else if (arr[middle2] < target) {
                low2 = middle2 + 1;
            } else {
                high2 = middle2;
                if (low2 == high2) {
                    break;
                }
            }
        }
        res[1] = low2;
        return res;
    }
}
