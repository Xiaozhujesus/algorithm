package priv.wz.list;

/**
 * 在 O(1) 时间内删除链表节点
 */
public class DeleteListNode {
    public void deleteNode(ListNode head, ListNode del) {
        if (head == null || del == null) {
            return;
        }
        if (del.next != null) {
            del.val = del.next.val;
            del.next = del.next.next;
        } else {
            ListNode pre = null;
            ListNode cur = head;
            while (cur != del) {
                pre = cur;
                cur = cur.next;
            }
            if (pre == null) {
                head = head.next;
            } else {
                pre.next = cur.next;
            }
        }
    }
}
