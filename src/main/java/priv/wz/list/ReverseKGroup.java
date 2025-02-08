package priv.wz.list;

/**
 * 将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表
 * 如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
 * 你不能更改节点中的值，只能更改节点本身。
 * 要求空间复杂度 O(1)
 * 例如：
 * 给定的链表是 1→2→3→4→5
 * 对于 k = 2, 你应该返回 2→1→4→3→5
 * 对于 k = 3, 你应该返回 3→2→1→4→5
 */
public class ReverseKGroup {

    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        ListNode dummy = new ListNode(head);
        ListNode preTail = dummy, subHead = head, subTail;
        while ((subTail = getTail(subHead, k)) != null) {
            ListNode nextHead = subTail.next;
            reverse(subHead, subTail);
            preTail.next = subTail;
            subHead.next = nextHead;
            preTail = subHead;
            subHead = nextHead;
        }
        return dummy.next;
    }

    ListNode getTail(ListNode head, int k) {
        while (head != null && k > 1) {
            head = head.next;
            k--;
        }
        return head;
    }

    private void reverse(ListNode head, ListNode tail) {
        ListNode pre = null, cur = head;
        while (cur != tail) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
    }
}
