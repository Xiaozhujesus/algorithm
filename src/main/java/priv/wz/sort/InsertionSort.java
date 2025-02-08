package priv.wz.sort;

/**
 * 插入排序和选择排序其实本质一样的，插入排序是从前半段有序数组中找位置插入
 * 选择排序是从后半段找最小
 */
public class InsertionSort {

    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
