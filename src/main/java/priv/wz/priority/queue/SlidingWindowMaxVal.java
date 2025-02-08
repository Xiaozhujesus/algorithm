package priv.wz.priority.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums，有一个大小 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。返回滑动窗口中的最大值 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 */
public class SlidingWindowMaxVal {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length * k == 0) return new int[0];
        if (k == 1) return nums;
        if (nums.length <= k) {
            int max = nums[0];
            for (int i : nums) {
                if (i > max) {
                    max = i;
                }
            }
            return new int[]{max};
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
        return ret;
    }

    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        // 队列中存储的是索引，索引对应的元素在队列中递减
        Deque<Integer> deque = new LinkedList<Integer>();
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
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}
