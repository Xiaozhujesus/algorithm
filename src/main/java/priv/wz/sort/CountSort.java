package priv.wz.sort;

import java.util.Arrays;

/**
 * For simplicity, consider the data in the range 0 to 9.
 * Input data: 1, 4, 1, 2, 7, 5, 2
 * 1) Take a count array to store the count of each unique object.
 * Index:     0  1  2  3  4  5  6  7  8  9
 * Count:     0  2  2  0  1  1  0  1  0  0
 * <p>
 * 2) Modify the count array such that each element at each index
 * stores the sum of previous counts.
 * Index:     0  1  2  3  4  5  6  7  8  9
 * Count:     0  2  4  4  5  6  6  7  7  7
 */
public class CountSort {

    void sort(char arr[]) {
        int count[] = new int[256];
        for (int i = 0; i < arr.length; ++i)
            ++count[arr[i]];
        int cur = 0;
        for (int i = 0; i < 256; i++) {
            while (count[i] != 0) {
                arr[cur++] = (char) i;
                count[i]--;
            }
        }
        /**
         * --------------或者下面这样
         */
        // Change count[i] so that count[i] now contains actual
        // position of this character in output array
//        for (int i = 1; i <= 255; ++i)
//            count[i] += count[i - 1];
//
//        // Build the output character array
//        // To make it stable we are operating in reverse order.
//        for (int i = n - 1; i >= 0; i--) {
//            output[count[arr[i]] - 1] = arr[i];
//            --count[arr[i]];
//        }
//
//        // Copy the output array to arr, so that arr now
//        // contains sorted characters
//        for (int i = 0; i < n; ++i)
//            arr[i] = output[i];
    }

    public static void main(String[] args) {

        char[] arr = {'g', 'e', 'e', 'k', 's', 'f', 'o', 'r', 'g', 'e', 'e', 'k', 's'};
        new CountSort().sort(arr);
        System.out.print("Sorted character array is " + Arrays.toString(arr));

    }
}
