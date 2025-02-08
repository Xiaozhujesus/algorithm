package priv.wz.priority.queue;

import java.util.PriorityQueue;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。例如，
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 */
public class MedianFinder {
    private PriorityQueue<Integer> min;
    private PriorityQueue<Integer> max;

    public MedianFinder() {
        min = new PriorityQueue<>((a, b) -> b - a);
        max = new PriorityQueue<>((a, b) -> a - b);
    }

    public void addNum(int num) {
        if (min.isEmpty() || num <= min.peek()) {
            min.offer(num);
            if (min.size() > max.size() + 1) {
                max.offer(min.poll());
            }
        } else {
            max.offer(num);
            if (max.size() == min.size() + 1) {
                min.offer(max.poll());
            }
        }

    }

    public double findMedian() {
        if (min.size() > max.size()) {
            return min.peek();
        }
        return (min.peek() + max.peek()) / 2.0;
    }
}
