package priv.wz.list;

/**
 * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回反转后的链表 。
 */
public class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode preTail = dummy;
        int i = 1;
        while (i != left) {
            preTail = preTail.next;
            i++;
            // left 超链表长度
            if (preTail == null) {
                return head;
            }
        }
        // list翻转后的尾节点，也是翻转的第一个节点
        ListNode tail = preTail.next;
        ListNode prev = preTail, cur = tail;
        if (cur == null) {
            return head;
        }
        ListNode next = cur.next;
        if (next == null) {
            return head;
        }
        int count = right - left;
        while (next != null && count != 0) {
            cur.next = prev;
            prev = cur;
            cur = next;
            next = next.next;
            count--;
        }
        cur.next = prev;
        preTail.next = cur;
        tail.next = next;

        return dummy.next;
    }
}
