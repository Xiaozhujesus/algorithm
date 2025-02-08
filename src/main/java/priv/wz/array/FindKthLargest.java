package priv.wz.array;

import java.util.PriorityQueue;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length;i++) {
            if (q.size() < k) {
                q.offer(nums[i]);
            } else if (nums[i] > q.peek()) {
                q.offer(nums[i]);
                q.poll();
            }
        }
        return q.peek();
    }
}
