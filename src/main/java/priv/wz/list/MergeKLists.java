package priv.wz.list;

import java.util.PriorityQueue;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> q = new PriorityQueue<>((a,b) -> a.val - b.val);
        for (ListNode i : lists) {
            if (i != null) {
                q.offer(i);
            }
        }
        ListNode dummy = new ListNode(), tail = dummy;
        while (!q.isEmpty()) {
            ListNode cur = q.poll();
            if (cur.next != null) {
                q.offer(cur.next);
            }
            tail.next = cur;
            tail = cur;
        }
        return dummy.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(1);
        ListNode pre = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                pre.next = list1;
                pre = list1;
                list1 = list1.next;
            } else {
                pre.next = list2;
                pre = list2;
                list2 = list2.next;
            }
        }
        pre.next = list1 == null ? list2 : list1;
        return dummy.next;
    }
}
