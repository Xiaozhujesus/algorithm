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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        ListNode dummy = new ListNode(head);
        ListNode preTail = dummy;
        ListNode start = head;
        while (start != null) {
            Pair reverse = reverse(start, k);
            preTail.next = reverse.head;
            preTail = reverse.tail;
            start = preTail.next;
        }
        return dummy.next;
    }

    /**
     * 将 head 链表前 k 个元素翻转，返回翻转后这 k 个的 head 和 tail
     * 若链表长度比 k 大，后面的不动
     */
    private Pair reverse(ListNode head, int k) {
        Pair pair = new Pair();
        // 检测剩余节点数够不够 k
        ListNode pre = null, cur = head;
        int count = k;
        while (cur != null && count != 0) {
            count--;
            pre = cur;
            cur = cur.next;
        }
        // 不够 k
        if (count != 0) {
            pair.head = head;
            pair.tail = pre;
            return pair;
        }
        // 够 k
        pre = null;
        cur = head;
        ListNode next = cur.next;
        while (--k != 0) {
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;
        }
        cur.next = pre;
        pair.tail = head;
        pair.head = cur;
        pair.tail.next = next;
        return pair;
    }

    class Pair {
        ListNode head;
        ListNode tail;
    }
}
