package priv.wz.sort;

/**
 * 类似选择排序和冒泡排序，使用堆化，找到堆中最大的元素，将最大的元素与最后一个互换，然后堆尺寸减1，重复这个过程
 */

public class HeapSort {

    /**
     * 堆排序核心是堆化，大顶堆的定义就是递归的，即第一个节点是最大的，其左右子堆也满足这个性质，因此该过程也是递归的
     *
     * @param arr
     * @param n   堆大小
     * @param i   以arr[i]为根进行子树的heapify，数组里面存了个树，树的这种形式在 Union-Find 算法中也存在
     */
    private void heapify(int arr[], int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    public void sort(int arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
}

/**
 * 这种方法每次对所有元素进行堆化操作，重复了，只需要操作受影响的堆即可
 *
 * @param arr
 * @param len 对arr从0开始多少个元素进行堆化
 * @param arr
 * @param len 对arr从0开始多少个元素进行堆化
 *//*

public class HeapSort {
    */
/**
 * @param arr
 * @param len 对arr从0开始多少个元素进行堆化
 *//*

    public void heapify(int[] arr, int len) {
        int m = len/2 - 1;
        while (m >= 0) {
            int left = m*2;
            int right = left+1;
            if (right < len) {
                int max = m;
                if (arr[left] > arr[max]) {
                    max = left;
                }
                if (arr[right] > arr[max]) {
                    max = right;
                }
                if (max != m) {
                    swap(arr, m, max);
                }
            } else {
                if (arr[m] < arr[left]) {
                    swap(arr, m, left);
                }
            }
            m--;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void sort(int[] arr) {
        int len = arr.length;
        while (len != 1) {
            heapify(arr, len);
            swap(arr,0, len-1);
            len--;
        }
    }
}
*/
