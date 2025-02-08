package priv.wz.sort;

import java.util.Arrays;

public class QuickSort {

    /**
     * 左闭右开
     */
    public void sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int key = partition(arr, l, r);
//        int mid = partition2(arr, l, r);
        sort(arr, l, key);
        // 这里左边界要+1，否则可能陷入无限循环，比如 [0,2)，key 为 0，又会调用 [0,2)
        sort(arr, key + 1, r);
    }

    /**
     * 核心是分区函数，该函数返回值是索引，数组中索引小于该索引的元素值，
     * 小于该索引对应的值；右侧则大于等于
     * 左闭右开，用户保证 l < r，否则结果未定义
     */
    private int partition(int[] arr, int l, int r) {
        int key = arr[r - 1];
        // arr[i] < key，不变式初始化
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (arr[j] < key) {
                i++;
                swapEqual(arr, i, j);
            }
        }
        /**
         * 这里一定要将 key 放到左右分界的中间，否则，如果中间位置的值大于 key，上层
         * 分治时不会操作中间位置的值，而是排序除中间位置值左右两侧的数组，就会导致错误
         */
        i++;
        swapEqual(arr, i, r - 1);
        return i;
    }

    /**
     * 该函数返回值是索引，数组中索引小于该索引的元素值，
     * 小于该索引对应的值；右侧则大于等于
     * 左闭右开，用户保证 l < r，否则结果未定义
     */
    public int partition2(int[] arr, int l, int r) {
        r--;
        l--;
        int key = arr[r];
        int keyIndex = r;
        // 循环不变式，每次循环开始都有：arr[l] < key，arr[r] >= key
        while (l < r) {
            while (l < r && arr[r] >= key) {
                r--;
            }
            while (l < r && arr[l] < key) {
                l++;
            }
            // 所有元素都大于等于最后一个元素时，l == r 且在数组范围外
            if (l != r) {
                swapEqual(arr, l, r);
            }
        }
        // 此时 arr[l] 一定小于 key
        l++;
        swapEqual(arr, l, keyIndex);
        return l;
    }

    /**
     * 3路快排，分区函数将数组分为3部分，第一部分小于选定值，第二部分等于选定值，第三部分大于选定值，用来处理数组中重复元素很多的情况
     *
     * @param arr
     * @param l
     * @param r
     */
    public void quicksort3way(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        Pair pair = partition3way(arr, l, r);
        quicksort3way(arr, l, pair.i);
        quicksort3way(arr, pair.j + 1, r);
    }

    /**
     * l ~ r 闭区间都排序
     * 返回值 pari.i 和 pair.j 说明：
     * index <= pair.i 的元素都小于 key
     * pair.i < index <= pair.j 等于 key
     */
    private Pair partition3way(int[] arr, int l, int r) {
        int key = arr[r];
        int i = l - 1, j = i;
        for (int p = l; p < r; p++) {
            if (arr[p] < key) {
                i++;
                j++;
                swapLess(arr, i, j, p);
            } else if (arr[p] == key) {
                j++;
                swapEqual(arr, j, p);
            }
        }
        arr[r] = arr[j + 1];
        arr[j + 1] = key;
        return new Pair(i, j + 1);
    }

    private void swapLess(int[] arr, int i, int j, int p) {
        int tmp = arr[p];
        arr[p] = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    private void swapEqual(int[] arr, int j, int p) {
        int tmp = arr[p];
        arr[p] = arr[j];
        arr[j] = tmp;
    }

    class Pair {
        int i;
        int j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{1, 4, 2, 4, 2, 4, 1, 2, 4, 1, 2, 2, 2, 2, 4, 1, 4, 4, 4};
        int[] arr = {2, 1};
//        new QuickSort().quicksort3way(arr, 0, arr.length - 1);
        new QuickSort().sort(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
