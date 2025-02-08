package priv.wz.stack;

import java.util.*;

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


    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        /**
         * 队列中存储的是索引，索引递增，索引对应的元素值在队列中递减，如果 nums[i] 比队列尾的元素值大，那么队列尾
         * 的元素不可能是包含 nums[i] 的滑动窗口的最大值，nums[i] 更有可能；deque 就是维护滑动窗口最大值的结构
         * 这里没用优先队列
         */
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 由于滑动窗口往前移动，窗口最大值可能被移出去
            if (deque.peekFirst() == i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
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

