package priv.wz.recursive;

import java.util.Stack;

/**
 * 从尾到头反过来打印出每个结点的值
 */
public class ReversePrintList {

    public void printList(Node head) {
        Stack stack = new Stack<Integer>();
        while (head != null) {
            stack.push(head.value);
            head = head.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public void printRecursive(Node head) {
        if (head == null) {
            return;
        }
        printRecursive(head.next);
        System.out.println(head.value);
    }

    class Node {
        Integer value;
        Node next;

        Node(Integer val) {
            this.value = val;
        }
    }
}

