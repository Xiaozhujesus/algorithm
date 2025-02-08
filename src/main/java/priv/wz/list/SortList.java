package priv.wz.list;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 */
public class SortList {
    // 第二种方法，merge
    public ListNode sortList(ListNode head) {
        return head == null ? null : mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head.next == null) {
            return head;
        }
        // 快慢指针找中点
        ListNode p = head, q = head, pre = null;
        while (q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }
        // 断链操作
        pre.next = null;
        ListNode l = mergeSort(head);
        ListNode r = mergeSort(p);
        return merge(l, r);
    }

    ListNode merge(ListNode l, ListNode r) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                cur.next = l;
                cur = cur.next;
                l = l.next;
            } else {
                cur.next = r;
                cur = cur.next;
                r = r.next;
            }
        }
        if (l != null) {
            cur.next = l;
        }
        if (r != null) {
            cur.next = r;
        }
        return dummyHead.next;
    }

    // 第一种方法，快排
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        quickSort(head, null);
        return head;
    }

    // 左闭右开，不包含tail
    private void quickSort(ListNode head, ListNode tail) {
        if (head == tail || head.next == tail) {
            return;
        }
        ListNode mid = partition(head, tail);
        quickSort(head, mid);
        quickSort(mid.next, null);
    }

    // 分区函数，上面的快排过程保证至少有两个节点需要分区函数，左闭右开，不包含tail
    private ListNode partition(ListNode head, ListNode tail) {
        ListNode first = head;
        ListNode second = head.next;
        while (second != tail) {
            if (second.val < head.val) {
                first = first.next;
                int tmp = first.val;
                first.val = second.val;
                second.val = tmp;
            }
            second = second.next;
        }
        int tmp = head.val;
        head.val = first.val;
        first.val = tmp;
        return first;
    }
}
