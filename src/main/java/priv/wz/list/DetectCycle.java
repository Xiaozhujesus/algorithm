package priv.wz.list;

/**
 * 对于一个给定的链表，返回环的入口节点，如果没有环，返回 null
 * 拓展：
 * 你能给出不利用额外空间的解法么？
 */
public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        if (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
        } else {
            return null;
        }
        slow = slow.next;
        while (slow != fast) {
            if (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            slow = slow.next;
        }
        // slow 和 fast 相遇时，假设 slow 移动的距离是 S，那么 fast 移动的距离 2S，此时让
        // fast 充当 slow 从头走，那么当 fast 移动 S 时，slow 共移动了 2S，与第一次的情形一样
        // 二者一定在第一次相遇的点相遇，由于这次步长都是 1，因此二者相遇的点即使入口
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
