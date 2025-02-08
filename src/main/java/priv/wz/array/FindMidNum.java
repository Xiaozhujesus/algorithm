package org.practice.array;

import java.util.PriorityQueue;

/**
 * 寻找无序数组中位数
 */
public class FindMidNum {

    public int f(int[] arr) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int mid = (arr.length + 1) / 2;
        for (int i = 0; i < arr.length; i++) {
            if (q.size() < mid) {
                q.add(arr[i]);
            } else if (arr[i] > q.peek()) {
                q.poll();
                q.add(arr[i]);
            }
        }
        return q.peek();
    }


    /**
     * 快排序改进，与寻找第k大数一样，只不过k为mid
     * @param arr
     * @return
     */
    public int g(int[] arr) {
        int mid = (arr.length + 1) / 2;
        int low = 0;
        int high = arr.length - 1;
        int mark = -1;
        while (mark != mid) {
            mark = partition(arr, low, high);
            if (mark > mid) {
                high = mark - 1;
            } else if (mark < mid) {
                low = mark + 1;
            } else {
                break;
            }
        }
        return arr[mid];
    }

    private int partition(int[] arr, int low, int high) {
        int first = low - 1;
        int second = low;
        while (second != high) {
            if (arr[second] <= arr[high]) {
                first++;
                int tmp = arr[second];
                arr[second] = arr[first];
                arr[first] = tmp;
            }
            second++;
        }
        first++;
        int tmp = arr[second];
        arr[second] = arr[first];
        arr[first] = tmp;
        return first;
    }
}
