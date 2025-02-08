package priv.wz.list;

/**
 * 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
 * 例如：
 * 给出的链表为1->2->3->3->4->4->5, 返回1->2->5
 * 给出的链表为1->1->1->2->3, 返回2->3.
 */
public class DeleteDuplicationNodeCompletely {
    // 递归
    public ListNode delete(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head.next;
        // 不重复节点
        if (cur == null || cur.val != head.val) {
            head.next = delete(cur);
            return head;
        }
        // 重复节点
        while (cur != null && cur.val == head.val) {
            cur = cur.next;
        }
        return delete(cur);
    }

    public ListNode del(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        ListNode pre = dummy, cur = head.next;
        while (cur != null) {
            if (cur.val == head.val) {
                cur = cur.next;
            } else {
                // 相邻不等
                if (head.next == cur) {
                    pre = head;
                } else {
                    // 跳过重复的一段
                    pre.next = cur;
                }
                head = cur;
                cur = cur.next;
            }
        }
        // 最后一段如果相等需要跳过
        if (head.next != null) {
            pre.next = null;
        }
        return dummy.next;
    }
}
