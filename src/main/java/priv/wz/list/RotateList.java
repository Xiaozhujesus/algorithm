package priv.wz.list;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 */
public class RotateList {
    public ListNode solution(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int internal = k;
        ListNode first = head;
        ListNode second = head;
        while (internal != 0 && first.next != null) {
            internal--;
            first = first.next;
        }

        if (internal != 0) {
            int listLength = k - internal + 1;
            int p = k % listLength;
            if (p == 0) {
                return head;
            }
            first = head;
            while (p != 0) {
                first = first.next;
                p--;
            }
        }

        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        ListNode ret = second.next;
        second.next = null;
        first.next = head;
        return ret;
    }
}
