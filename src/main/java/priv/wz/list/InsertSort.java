package priv.wz.list;

/**
 * 单链表插入排序
 */
public class InsertSort {
    public ListNode sortInList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode h = head, pre = head, cur = head.next;
        while (cur != null) {
            if (cur.val >= pre.val) {
                pre = cur;
                cur = cur.next;
            } else {
                pre.next = cur.next;
                // 因为cur小于pre.val，因此head到pre之间一定可以找到一个位置
                h = insert(h, cur);
                cur = pre.next;
            }
        }
        return h;
    }
    // 返回新的head，因为有可能h就是新插入的node，会改变
    private ListNode insert(ListNode head, ListNode cur) {
        if (cur.val <= head.val) {
            cur.next = head;
            return cur;
        } else {
            ListNode p = head;
            while (p.next != null && cur.val > p.next.val) {
                p = p.next;
            }
            cur.next = p.next;
            p.next = cur;
            return head;
        }
    }
}
