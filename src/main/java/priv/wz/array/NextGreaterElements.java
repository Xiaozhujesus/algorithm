package priv.wz.array;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个循环数组nums（nums[nums.length - 1]的下一个元素是nums[0]），返回nums中每个元素的 下一个更大元素 。
 * 数字 x的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
 * 如果不存在，则输出 -1
 */
public class NextGreaterElements {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null) {
            return nums;
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int j = (i + 1) % nums.length;
            while (j != i) {
                if (nums[j] > nums[i]) {
                    ans[i] = nums[j];
                    break;
                }
                j = (j + 1) % nums.length;
            }
            if (j == i) {
                ans[i] = -1;
            }
        }
        return ans;
    }

    // 栈中存索引，栈中索引对应的元素不增（递减或相等）
    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }

}
