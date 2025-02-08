package priv.wz.stack;

import java.util.Stack;

/**
 * 给定一个乱序的栈，设计算法将其升序排列。
 * <p>
 * ps: 允许额外使用一个栈来辅助操作
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
