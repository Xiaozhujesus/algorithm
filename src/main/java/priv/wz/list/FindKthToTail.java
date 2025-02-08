package priv.wz.list;

/**
 * 倒数第k个节点
 */
public class FindKthToTail {
    public ListNode FindKthToTail(ListNode head, int k) {
        if (k == 0) {
            return null;
        }
        ListNode cur = head;
        while (cur != null && --k > 0) {
            cur = cur.next;
        }
        if (cur == null) {
            return null;
        }
        while (cur != null) {
            head = head.next;
            cur = cur.next;
        }
        return head;
    }
}
