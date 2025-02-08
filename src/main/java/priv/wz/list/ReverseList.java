package priv.wz.list;

/**
 * 反转list
 */
public class ReverseList {
    //迭代
    public ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //递归
    public ListNode g(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tmp = g(head.next);
        head.next.next = head;
        head.next = null;
        return tmp;
    }
}
