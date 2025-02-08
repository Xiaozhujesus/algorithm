package priv.wz.list;

/**
 * 删除倒数第 n 个节点
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        ListNode pre = dummy, cur = head, tail = head;
        while (n > 1 && tail != null) {
            tail = tail.next;
            n--;
        }
        if (tail == null) {
            return head;
        }
        while (tail.next != null) {
            pre = cur;
            cur = cur.next;
            tail = tail.next;
        }
        pre.next = cur.next;
        return dummy.next;
    }
}
