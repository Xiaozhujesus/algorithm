package priv.wz.list;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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
            // left 超链表长度
            if (preTail == null) {
                return head;
            }
            preTail = preTail.next;
            i++;
        }
        if (preTail == null) {
            return head;
        }
        // list翻转后的尾节点，也是翻转的第一个节点
        ListNode tail = preTail.next, prev = preTail, cur = tail;
        int count = right - left;
        while (cur != null && count != 0) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            count--;
        }
        ListNode next;
        if (cur == null) {
            next = null;
            // 统一处理
            cur = prev;
        } else {
            next = cur.next;
            // 最后一个接上
            cur.next = prev;
        }
        preTail.next = cur;
        tail.next = next;

        return dummy.next;
    }
}
