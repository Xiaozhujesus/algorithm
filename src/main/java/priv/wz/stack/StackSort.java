package priv.wz.stack;

import java.util.Stack;

/**
 * 给定两个栈，一个空一个有乱序数据，使用这两个栈对数据进行排序
 */
public class StackSort {
    public Stack<Integer> stackSort(Stack<Integer> input) {
        Stack<Integer> ans = new Stack<>();
        ans.push(input.pop());
        while (!input.isEmpty()) {
            int tmp = input.pop();
            while (!ans.isEmpty() && ans.peek() > tmp) {
                input.push(ans.pop());
            }
            ans.push(tmp);
        }
        return ans;
    }
}
