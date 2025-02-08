package priv.wz.sort;

import java.util.Arrays;

public class MergeSort {

    /**
     * 左闭右开
     */
    public void sort(int[] arr, int l, int r) {
        if (l + 1 == r) {
            return;
        }
        int m = (l + r) / 2;
        sort(arr, l, m);
        sort(arr, m, r);
        merge(arr, l, m, r);
    }

    /**
     * l ~ m - 1, m ~ r
     */
    private void merge(int[] arr, int l, int m, int r) {

        if (arr[m - 1] <= arr[m]) {
            return;
        }
        /**
         * 这里其实只用 m+1...r 复制到新数组，然后 l...m 使用原来数组，然后从最大值开始往原来的数组里填数字就可以了
         * 如果 m+1...r 先结束，则整个复制结束；如果 l...m 先结束，那么只需继续将 m+1...r 剩余的部分都复制到原来数组到低位就可以了
         */
        int[] tmp = new int[r - m];
        System.arraycopy(arr, m, tmp, 0, tmp.length);

        int p1 = m - 1;
        int p2 = tmp.length - 1;
        int i = r - 1;
        while (p1 >= l && p2 >= 0) {
            if (arr[p1] < tmp[p2]) {
                arr[i--] = tmp[p2--];
            } else {
                arr[i--] = arr[p1--];
            }
        }
        while (p2 >= 0) {
            arr[i--] = tmp[p2--];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,5};
        new MergeSort().sort(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
