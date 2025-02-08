package priv.wz.list;

import java.util.ArrayList;

/**
 * 请判断一个链表是否为回文链表。
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 * 用O(n)时间复杂度,O(1)空间复杂度
 */
public class Palindrome {
    public boolean palindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode ptr1 = head;
        ListNode ptr2 = head;
        while (ptr2.next != null && ptr2.next.next != null) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next.next;
        }
        ListNode mid = ptr1;
        boolean ret;
        boolean odd;
        if (ptr2.next == null) {
            odd = false;
        } else {
            odd = true;
        }
        ptr1 = head;
        if (odd) {
            ptr2 = reverse(mid.next);
        } else {
            ptr2 = reverse(mid);
        }
        ListNode tail = ptr2;
        while (ptr2 != null && ptr1.val == ptr2.val) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        if (ptr2 == null) {
            ret = true;
        } else {
            ret = false;
        }
        reverse(tail);

        return ret;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head.next;
        while (next != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            next = cur.next;
        }
        cur.next = pre;
        return cur;
    }

    /**
     * 笨办法
     *
     * @param head
     * @return
     */
    public boolean util(ListNode head) {
        if (head == null) {
            return false;
        }
        ArrayList<Integer> arr = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            arr.add(cur.val);
            cur = cur.next;
        }
        int l = 0, h = arr.size() - 1;
        while (l < h) {
            //todo 注意这里用equal比较，不可以用== 或者 !=
            if (!arr.get(l).equals(arr.get(h))) {
                return false;
            }
            l++;
            h--;
        }
        return true;
    }
}
