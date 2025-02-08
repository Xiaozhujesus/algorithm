package priv.wz.list;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != null && b != null) {
            if (a == b) {
                return a;
            }
            a = a.next;
            b = b.next;
        }
        if (a == null) {
            a = headB;
            while (b != null) {
                a = a.next;
                b = b.next;
            }
            b = headA;
            while (a != b) {
                a = a.next;
                b = b.next;
            }
            return a;
        } else {
            b = headA;
            while (a != null) {
                a = a.next;
                b =b.next;
            }
            a = headB;
            while (a != b) {
                a = a.next;
                b = b.next;
            }
            return a;
        }
    }
}
