package priv.wz.greedy;

/**
 * 给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列
 * 如果存在这样的 i, j, k, 且满足 0 ≤ i < j < k ≤ n-1，
 * 使得 arr[i] < arr[j] < arr[k] ，返回 true ;否则返回 false
 * 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)
 */
public class IncreasingTriplet {
    // 与 DP 中的 LongestIncrementSubSeq 问题解法二一样
    public boolean f(int[] arr) {
        if (arr == null || arr.length < 3) {
            return false;
        }
        /**
         * 核心在这，diffLengthTailSmallest[i] 表示遍历到目前为止，出现的长度为 i 的递增子序列的末尾元素最小值
         * 反证法很容易证明，diffLengthTailSmallest 递增
         * 不要记录目前的最长递增子序列是什么，而是记录目前每个长度下，最长递增子序列末尾是什么
         */

        int[] diffLengthTailSmallest = new int[3];
        diffLengthTailSmallest[1] = arr[0];
        // 已经找到的最长递增子序列的长度
        int boundary = 1;

        // 由于这里只有两个，直接比较即可；正常应该二分查找 diffLengthTailSmallest[0..boundary]中第一个大于 arr[i] 的，并将其替换为 arr[i]
        // 如果比 diffLengthTailSmallest[boundary] 还大，直接放在 diffLengthTailSmallest[boundary+1]
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= diffLengthTailSmallest[1]) {
                diffLengthTailSmallest[1] = arr[i];
            } else {
                if (boundary < 2) {
                    diffLengthTailSmallest[2] = arr[i];
                    boundary = 2;
                } else {
                    if (arr[i] > diffLengthTailSmallest[2]) {
                        return true;
                    } else {
                        diffLengthTailSmallest[2] = arr[i];
                    }
                }
            }
        }
        return false;
    }
}
