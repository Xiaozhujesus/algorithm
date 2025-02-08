package priv.wz.search;

import java.util.PriorityQueue;

/**
 * 给定两个有序数组arr1和arr2，已知两个数组的长度都为N，求两个数组中所有数的上中位数。
 * 上中位数：假设递增序列长度为n，若n为奇数，则上中位数为第n/2+1个数；否则为第n/2个数
 * 时间复杂度为O(logN)，额外空间复杂度为O(1)
 */
public class FindMedianinTwoSortedAray {
    public int findMedianinTwoSortedAray(int[] arr1, int[] arr2) {
        if (arr1.length == 1) {
            return Math.min(arr1[0], arr2[0]);
        }
        int len = arr1.length;
        int low = 0, high = len - 1;
        int mid, other;
        while (low <= high) {
            mid = (low + high) / 2;
            other = len - (mid + 1) - 1;
            if (arr1[mid] > arr2[other]) {
                // 不主动跳出循环，外层循环永远死循环
                if (high != mid) {
                    high = mid;
                } else {
                    break;
                }
            } else if (arr1[mid] < arr2[other]) {
                // 不主动跳出循环，外层循环永远死循环
                if (low != mid) {
                    low = mid;
                } else {
                    break;
                }
            } else {
                return arr1[mid];
            }
        }
        /**
         * 跳出循环的情况：
         * 1. low+1 == high && arr1[mid] < arr2[other]
         * 2. low == high (由low+1 == high && arr1[mid] > arr2[other]的情况转移过来的)
         * 此时mid == low
         * 跳出循环时候low永远不可能为len-1
         */
        other = len - (low + 1) - 1;
        if (low < high) {
            // 在arr2
            if (arr2[other] < arr1[low + 1]) {
                return arr2[other];
            } else {
                // 在arr1
                return arr1[low + 1];
            }
        } else {
            // arr1[0] > arr2[len-2]，但是arr1[0]与arr[len-1]大小未知
            if (low == 0) {
                return Math.min(arr1[0], arr2[len - 1]);
            }
            // 0<low<len-1 && arr1[low] > arr2[other]
            return arr1[low];
        }
    }

    /**
     * 这题的通用形式是：n 个有序数组，每个长度为 Lengthi，找数组的第 k 小的数
     */
    public int getKthElement(int[][] nums, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */


        int n = nums.length;
        int activen = n;
        boolean[] finish = new boolean[n];
        int[] index = new int[n];
        int[] nextIndex = new int[n];

        while (true) {
            for (int i = 0; i < n; i++) {
                if (index[i] == nums[i].length) {
                    finish[i] = true;
                    activen--;
                }
            }
            if (activen == 1) {
                for (int i = 0; i < n; i++) {
                    if (!finish[i]) {
                        return nums[i][index[i] + k - 1];
                    }
                }
            }
            if (k < activen) {
                PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
                for (int i = 0; i < n; i++) {
                    if (!finish[i]) {
                        pq.offer(new int[]{nums[i][index[i]], i});
                    }
                }
                int[] min;
                while (k != 0) {
                    min = pq.poll();
                    k--;
                    if (k == 0) {
                        return min[0];
                    }
                    index[min[1]]++;
                    if (index[min[1]] != nums[min[1]].length) {
                        min[0] = nums[min[1]][index[min[1]]];
                        pq.offer(min);
                    }
                }
            }

            // 正常情况
            int average = k / activen;
            int mini = -1, min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    nextIndex[i] = Math.min(index[i] + average, nums[i].length) - 1;
                    if (nums[i][nextIndex[i]] < min) {
                        min = nums[i][nextIndex[i]];
                        mini = i;
                    }
                }
            }

            k -= (nextIndex[mini] - index[mini] + 1);
            index[mini] = nextIndex[mini] + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new FindMedianinTwoSortedAray().getKthElement(new int[][]{{3, 6, 7}, {1, 2, 4, 5}}, 7));
    }
}
