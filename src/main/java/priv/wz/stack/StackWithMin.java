package priv.wz.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 实现一个特殊功能的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作
 * 有三种操作种类，op1表示push，op2表示pop，op3表示getMin。你需要返回和op3出现次数一样多的数组，表示每次getMin的答案
 * in: [[1,3],[1,2],[1,1],[3],[2],[3]]
 * out: [1,2]
 * <p>
 * 提示：举个例子，栈里这样：【3，2，1，3，2，1】，不断pop，每次pop看下最小是多少
 */
public class StackWithMin {
    public int[] getMinStack(int[][] op) {
        if (op == null || op.length == 0) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        MyStack stack = new MyStack();
        for (int i = 0, len = op.length; i < len; i++) {
            if (op[i][0] == 1) {
                stack.push(op[i][1]);
            } else if (op[i][0] == 2) {
                stack.pop();
            } else {
                list.add(stack.min());
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    // 只要栈确定了，当前栈对应的最小就是确定的，不会改变，只需用另一个栈记录就可以了
    class MyStack {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> min = new Stack<>();

        void push(int i) {
            stack.push(i);
            if (min.isEmpty() || i < min.peek()) {
                min.push(i);
            } else {
                min.push(min.peek());
            }
        }

        int pop() {
            min.pop();
            return stack.pop();
        }

        int min() {
            return min.peek();
        }
    }

    // 上面你会发现，min 栈一定是递减的，因此可以压缩
    class MyStack2 {
        Stack<Integer> stack = new Stack<>();
        Stack<MinWithFreq> min = new Stack<>();

        void push(int i) {
            stack.push(i);
            if (min.isEmpty() || i < min.peek().min) {
                min.push(new MinWithFreq(i));
            } else {
                min.peek().freq++;
            }
        }

        int pop() {
            if (--min.peek().freq == 0) {
                min.pop();
            }
            return stack.pop();
        }
    }

    class MinWithFreq {
        int min;
        int freq;

        public MinWithFreq(int min) {
            this.min = min;
            this.freq = 1;
        }
    }

    public static void main(String[] args) {
        new StackWithMin().getMinStack(new int[][]{{1, -185224}, {1, -515268}, {3}, {1, -57301}, {2}, {2}, {1, 539954}, {1,
                886856}, {1, 333965}, {3}});
    }
}
