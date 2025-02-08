package org.practice.array;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 */
public class MinNumInRotateArray {
    public int min(int[] arr, int l, int r) {
        while (l != r) {
            int m = (l + r) / 2;
            if (arr[l] == arr[m] && arr[m] == arr[r]) {
                return minNum(arr, l, r);
            } else if (arr[l] < arr[m]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return arr[l];
    }

    private int minNum(int[] arr, int l, int r) {
        for (int i = l; i < r; i++) {
            if (arr[i] > arr[i + 1]) {
                return arr[i + 1];
            }
        }
        return arr[l];
    }
}
