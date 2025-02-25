package priv.wz.list;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 */
public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(head);
        ListNode preTail = dummy, a = head, b = a.next, nextHead = b.next;
        while (a != null && b != null) {
            preTail.next = b;
            b.next = a;
            a.next = nextHead;
            preTail = a;
            a = nextHead;
            b = a != null ? a.next : null;
            nextHead = b != null ? b.next : null;
        }
        return dummy.next;
    }
}
