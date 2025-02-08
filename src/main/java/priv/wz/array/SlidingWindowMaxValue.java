package org.practice.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。
 * 滑动窗口每次只向右移动一位，返回滑动窗口中的最大值，你能在线性时间复杂度内解决此题吗
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 */
public class SlidingWindowMaxValue {
    ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
    int[] nums;

    /**
     * 假设队列中第一个比nums[i]大的数的索引为first，那么在nums中，first和i之间不会有比nums[i]大的数，因为如果有，那么在处理该索引时候
     * first就会被清理掉而不存在了
     *
     * @param i
     * @param k
     */
    public void clean_deque(int i, int k) {
        // remove indexes of elements not from sliding window
        if (!deq.isEmpty() && deq.getFirst() == i - k)
            deq.removeFirst();

        // remove from deq indexes of all elements
        // which are smaller than current element nums[i]
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) {
            deq.removeLast();
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        // init deque and output
        this.nums = nums;
        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            // compute max in nums[:k]
            if (nums[i] > nums[max_idx]) max_idx = i;
        }
        int[] output = new int[n - k + 1];
        output[0] = nums[max_idx];

        // build output
        for (int i = k; i < n; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }


    /**
     * 与上面的等价，如果window中最大值是第一个元素，滑动窗口的时候被移动出窗口了，需要在新的window中重新找到最大元素，需要遍历window中
     * 所有元素
     *
     * @param nums
     * @param k
     * @return
     */
//    public int[] maxWindow(int[] nums, int k) {
//        int n = nums.length;
//        if (n * k == 0 || n < k) {
//            return new int[0];
//        }
//        if (k == 1) {
//            return nums;
//        }
//        int beforeMaxValIndex = 0;
//        for (int i = 0; i < k; i++) {
//            if (nums[i] > nums[beforeMaxValIndex]) {
//                beforeMaxValIndex = i;
//            }
//        }
//        int[] ret = new int[nums.length - k + 1];
//        ret[0] = nums[beforeMaxValIndex];
//        for (int i = k; i < nums.length; i++) {
//            //最大元素的索引不在window第一个元素，只需要比较最大元素和当前元素
//            if (beforeMaxValIndex != i - k) {
//                if (nums[beforeMaxValIndex] > nums[i]) {
//                    ret[i - k + 1] = nums[beforeMaxValIndex];
//                } else {
//                    ret[i - k + 1] = nums[i];
//                    beforeMaxValIndex = i;
//                }
//            } else {
//                //最大元素索引在window第一个元素，移动到下一个window重新找最大
//                beforeMaxValIndex = i - k + 1;
//                for (int j = beforeMaxValIndex; j <= i; j++) {
//                    if (nums[j] > nums[beforeMaxValIndex]) {
//                        beforeMaxValIndex = j;
//                    }
//                }
//                ret[i - k + 1] = nums[beforeMaxValIndex];
//            }
//        }
//        return ret;
//    }


    public ArrayList<Integer> maxWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0 || n < k) {
            return new ArrayList<>();
        }
        if (k == 1) {
            ArrayList<Integer> res = new ArrayList<>(nums.length);
            for (int i : nums) {
                res.add(i);
            }
            return res;
        }
        int beforeMaxValIndex = 0;
        for (int i = 0; i < k; i++) {
            if (nums[i] > nums[beforeMaxValIndex]) {
                beforeMaxValIndex = i;
            }
        }
        int[] ret = new int[nums.length - k + 1];
        ret[0] = nums[beforeMaxValIndex];
        for (int i = k; i < nums.length; i++) {
            //最大元素的索引不在window第一个元素，只需要比较最大元素和当前元素
            if (beforeMaxValIndex != i - k) {
                if (nums[beforeMaxValIndex] > nums[i]) {
                    ret[i - k + 1] = nums[beforeMaxValIndex];
                } else {
                    ret[i - k + 1] = nums[i];
                    beforeMaxValIndex = i;
                }
            } else {
                //最大元素索引在window第一个元素，移动到下一个window重新找最大
                beforeMaxValIndex = i - k + 1;
                for (int j = beforeMaxValIndex; j <= i; j++) {
                    if (nums[j] > nums[beforeMaxValIndex]) {
                        beforeMaxValIndex = j;
                    }
                }
                ret[i - k + 1] = nums[beforeMaxValIndex];
            }
        }
        ArrayList<Integer> res = new ArrayList<>(ret.length);
        for (int i : ret) {
            res.add(i);
        }
        return res;
    }
}

