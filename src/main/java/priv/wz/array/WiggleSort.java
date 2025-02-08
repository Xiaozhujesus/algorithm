package priv.wz.array;

import java.util.Arrays;

/**
 * 一个没有排序的数组，请将原数组就地重新排列满足如下性质
 * <p>
 * nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * <p>
 * 进阶：
 * <p>
 * nums[0] < nums[1] > nums[2] < nums[3]....
 * O(n)时间复杂度和O(1)空间复杂度
 */
public class WiggleSort {
    public void f(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        Arrays.sort(arr);
        for (int i = 1; i+1 < arr.length; i += 2) {
            int tmp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = tmp;
        }
    }

    /**
     * 总体思路是先排序，然后从中间分成两部分，为了避免等于，然后穿插
     *
     * @param arr
     */
    public void f2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int[] tmp = Arrays.copyOfRange(arr, 0, arr.length);
        Arrays.sort(tmp);
        // 奇数位置上的元素个数要大于等于偶数位置上的元素个数，因此j从中间往后一个开始
        int i = 0;
        int j = (arr.length + 1) / 2;
        int p = 0;
        // 表示奇数位置
        boolean flag = true;
        while (p != arr.length) {
            if (flag) {
                arr[p] = tmp[i];
                i++;
            } else {
                arr[p] = tmp[j];
                j++;
            }
            p++;
            flag = !flag;
        }
    }
}
