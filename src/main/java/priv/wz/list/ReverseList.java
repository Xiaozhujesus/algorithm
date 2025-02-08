package priv.wz.list;

/**
 * 反转list
 */
public class ReverseList {
    //迭代
    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head.next;
        while (next != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;
        }
        cur.next = pre;
        return cur;
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
