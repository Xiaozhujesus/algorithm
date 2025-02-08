package priv.wz.list;

import java.util.Stack;

/**
 * 回文链表
 */
public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
        }
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }
        slow = head;
        while (!stack.isEmpty()) {
            if (slow.val != stack.pop().val) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }
}
