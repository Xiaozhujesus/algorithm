package priv.wz.list;

/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复节点只保留一个，返回链表头指针
 */
public class DeleteDuplicationNode {
    // 递归
    public ListNode deleteDuplication(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        while (next != null && head.val == next.val) {
            next = next.next;
        }
        head.next = deleteDuplication(next);
        return head;
    }

    // 循环
    public ListNode deleteDuplication2(ListNode head) {
        ListNode subHead = head, cur = head;
        while (subHead != null) {
            while (cur != null && cur.val == subHead.val) {
                cur = cur.next;
            }
            subHead.next = cur;
            subHead = cur;
        }
        return head;
    }
}
