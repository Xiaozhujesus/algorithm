package priv.wz.stack;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作，如下图两个栈底连起来就可以了
 *  -------------------------------------
 * |up       bottom | bottom           up|
 *  -------------------------------------
 */
public class StackImplQueue {

    private Stack<Integer> first = new Stack();
    private Stack<Integer> second = new Stack();

    public Integer pop() throws Exception {
        if (!second.isEmpty()) {
            return second.pop();
        }
        while (!first.isEmpty()) {
            second.push(first.pop());
        }
        if (second.isEmpty()) {
            throw new Exception("queue is empty");
        }
        return second.pop();
    }

    public void push(Integer i) {
        first.push(i);
    }
}

