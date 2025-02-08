package priv.wz.list;

import java.util.Comparator;
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
        PriorityQueue<ListNode> q = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode a, ListNode b) {
                return a.val - b.val;
            }
        });
        for (ListNode i : lists) {
            q.offer(i);
        }
        ListNode head = q.poll();
        ListNode tail = head;
        if (head.next != null) {
            q.offer(head.next);
        }
        while (!q.isEmpty()) {
            ListNode cur = q.poll();
            if (cur.next != null) {
                q.offer(cur.next);
            }
            tail.next = cur;
            tail = cur;
        }
        return head;
    }
}
