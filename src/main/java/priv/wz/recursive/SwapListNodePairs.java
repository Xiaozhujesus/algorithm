package priv.wz.recursive;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class SwapListNodePairs {
    public ListNode f(ListNode head) {
        return swap(head);
    }
    private ListNode swap(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode second = head.next;
        ListNode third = second.next;
        second.next = head;
        head.next = swap(third);
        return second;
    }
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
