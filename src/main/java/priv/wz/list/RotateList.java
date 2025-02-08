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
    public ListNode rotateRight(ListNode head, int k) {
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

        // 移动的距离比链表长
        if (internal != 0) {
            int listLength = k - internal + 1;
            int p = k % listLength;
            if (p == 0) {
                return head;
            }
            // 再次执行第一个指针先移动 k 的逻辑，这次一定不会超出链表长度
            first = head;
            while (p != 0) {
                first = first.next;
                p--;
            }
        }

        // 找到断链位置 second
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        ListNode ans = second.next;
        second.next = null;
        first.next = head;
        return ans;
    }
}
