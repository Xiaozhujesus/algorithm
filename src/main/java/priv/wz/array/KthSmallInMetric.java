package org.practice.array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素
 */
class KthSmallInMetric {
    public int f(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        // 二分查找是索引，而这里是值，按照这种逼近方法，left和right最终一定相等；
        // 而第k小的数字又一定存在，因此left==right时候，必定是结果，只不过有可能left==right时候
        // 此时矩阵中小于等于left值的元素个数大于k，但是left在矩阵中有多个相同值，因此第k小的元素也是left
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 矩阵中以mid为分割值，小于等于mid值的元素个数是不是大于等于k
     * 若是，因为这里有等于k的情况，所以right = mid
     * 若不是，说明mid值还要加大，因此left = mid+1，这里需要加1，因为要加大
     *
     * @param matrix
     * @param mid
     * @param k
     * @param n
     * @return
     */
    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                /**
                 * 当前这列有多少元素，然后处理下一列
                 */
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }


    //通过上面方法的启发，类似一维数组找第k大的数
    public int g(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b-a;
            }
        });
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (pq.size() < k) {
                    pq.offer(matrix[i][j]);
                } else {
                    if (pq.peek() <= matrix[i][j]) {
                        break;
                    } else {
                        pq.poll();
                        pq.offer(matrix[i][j]);
                    }
                }
            }
        }
        return pq.peek();
    }
}

/**
 * 这中方法类似与在一个给定升序排列的数组中找第k小的数，根据目前left和right的值，mid包含的小于mid的元素个数不断调整left和right
 * 最终计算的值一定在数组中
 * <p>
 * right = mid
 * left = mid + 1
 * 上面这种方法不会丢数据，结果一定在数组中
 */