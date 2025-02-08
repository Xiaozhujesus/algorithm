package priv.wz.array;

import java.util.PriorityQueue;

/**
 * 给定一个整数数组num，同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。如输入数组[1,3,5,2,2],5,3，返回：2
 */
public class KSmallestNum {

    public int pq(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (pq.size() < k) {
                pq.offer(arr[i]);
            } else if (arr[i] > pq.peek()) {
                pq.offer(arr[i]);
                pq.poll();
            }
        }
        return pq.peek();
    }

    public int quickSort(int[] arr, int k) {
        int l = 0, h = arr.length - 1;
        int tmp;
        while (true) {
            tmp = partition(arr, l, h);
            if (tmp == k - 1) {
                return arr[tmp];
            } else if (tmp > k - 1) {
                h = tmp - 1;
            } else {
                l = tmp + 1;
            }
        }
    }

    private int partition(int[] arr, int l, int h) {
        int i = l - 1;
        int j = l;
        while (j < h) {
            if (arr[j] <= arr[h]) {
                i++;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
            j++;
        }
        i++;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return i;
    }
}
