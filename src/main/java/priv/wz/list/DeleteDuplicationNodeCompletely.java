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

    // 重复节点只保留一个
    public ListNode delete2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head, cur = head.next;
        while (cur != null) {
            while (cur.val == pre.val) {
                cur = cur.next;
            }
            pre.next = cur;
            pre = cur;
            cur = cur.next;
        }
        pre.next = cur;
        return head;
    }

    // 循环
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(head);
        ListNode preTail = dummy;
        ListNode curHead = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == curHead.val) {
                cur = cur.next;
            } else {
                // 相邻不等
                if (curHead.next == cur) {
                    preTail = curHead;

                } else {
                    preTail.next = cur;
                }
                curHead = cur;
                cur = cur.next;
            }
        }
        // 最后一个连续
        if (curHead.next != null) {
            preTail.next = null;
        }
        return dummy.next;
    }
}
