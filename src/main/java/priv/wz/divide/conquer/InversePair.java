package priv.wz.divide.conquer;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
 * 并将P对1000000007取模的结果输出。 即输出P%1000000007。
 * 输入描述：
 * 题目保证输入的数组中没有的相同的数字
 * 数据范围：
 * 对于%50的数据,size<=10^4
 * 对于%75的数据,size<=10^5
 * 对于%100的数据,size<=2*10^5
 */
public class InversePair {
    private int res;

    public int InversePairs(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] tmp = new int[array.length];
        mergesort(array, 0, array.length - 1, tmp);
        return res;
    }

    private void mergesort(int[] arr, int l, int h, int[] tmp) {
        if (l < h) {
            int mid = (l + h) / 2;
            mergesort(arr, l, mid, tmp);
            mergesort(arr, mid + 1, h, tmp);
            merge(arr, l, mid, h, tmp);
        }
    }

    /**
     * l~h的逆序对 = 左半部分的逆序对 + 右半部分的逆序对 + 左半部分相对右半部分的逆序对
     *
     * @param arr
     * @param l
     * @param mid
     * @param h
     * @param tmp
     */
    private void merge(int[] arr, int l, int mid, int h, int[] tmp) {
        int p = 0;
        int i = l;
        int j = mid + 1;
        while (i <= mid && j <= h) {
            if (arr[i] > arr[j]) {
                // 存在逆序对
                res += (mid - i + 1);
                res %= 1000000007;
                tmp[p++] = arr[j++];
            } else {
                tmp[p++] = arr[i++];
            }
        }
        while (i <= mid) {
            tmp[p++] = arr[i++];
        }
        while (j <= h) {
            tmp[p++] = arr[j++];
        }
        for (i = 0; i < p; i++) {
            arr[l + i] = tmp[i];
        }
    }
}
