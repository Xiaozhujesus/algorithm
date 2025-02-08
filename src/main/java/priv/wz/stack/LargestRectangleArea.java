package priv.wz.stack;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class LargestRectangleArea {

    /**
     * 从中间向两边扩展，左右侧第一个小于当前高度的就是边界
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            int low = i - 1, high = i + 1;
            while (low >= 0 && heights[low] >= heights[i]) {
                low--;
            }
            while (high < heights.length && heights[high] >= heights[i]) {
                high++;
            }
            ans = Math.max(ans, (high - low - 1) * heights[i]);
        }
        return ans;
    }

    /**
     * 上面方法的优化，单调栈方法，与本目录下 SlidingWindowMaxValue 和 DailyTemperatures 一样
     */
    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        /**
         * 栈中存储元素索引，索引本身递增，索引对应的height也递增
         */
        Stack<Integer> mono_stack = new Stack<>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            // -1 位置可以看做比任何 height 都小
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        mono_stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}
