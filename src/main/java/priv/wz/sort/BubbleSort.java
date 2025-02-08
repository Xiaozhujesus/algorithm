package priv.wz.sort;

import java.util.Arrays;

public class BubbleSort {

    public void sort(int[] arr) {
        for (int end = arr.length - 1; end > 0 ; end--) {
            // 确定 end 位置的值
            for (int j = 0; j < end; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] ints = {3, 6, 1, 5, 2};
        new BubbleSort().sort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
