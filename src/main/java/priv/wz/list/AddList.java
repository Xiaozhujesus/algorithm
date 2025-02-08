package priv.wz.list;

import java.util.Stack;

/**
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
 */
public class AddList {
    public ListNode f(ListNode a, ListNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        ListNode h1 = reverse(a);
        ListNode h2 = reverse(b);
        ListNode cur1 = h1;
        ListNode cur2 = h2;
        ListNode last = null;
        int overflow = 0;
        int sum = 0;
        while (cur1 != null && cur2 != null) {
            sum = cur1.val + cur2.val + overflow;
            cur1.val = sum % 10;
            if (sum > 9) {
                overflow = 1;
            } else {
                overflow = 0;
            }
            last = cur1;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        if (cur1 == null) {
            if (cur2 == null) {
                if (overflow == 1) {
                    last.next = new ListNode(1);
                }
            } else {
                last.next = cur2;
                if (overflow == 1) {
                    while (overflow == 1 && cur2 != null) {
                        cur2.val = cur2.val + 1;
                        if (cur2.val > 9) {
                            overflow = 1;
                        } else {
                            overflow = 0;
                        }
                        cur2.val = cur2.val % 10;
                        last = cur2;
                        cur2 = cur2.next;
                    }
                    if (overflow == 1) {
                        last.next = new ListNode(1);
                    }
                }
            }
        } else {
            if (overflow == 1) {
                while (overflow == 1 && cur1 != null) {
                    cur1.val = cur1.val + 1;
                    if (cur1.val > 9) {
                        overflow = 1;
                    } else {
                        overflow = 0;
                    }
                    cur1.val = cur1.val % 10;
                    last = cur1;
                    cur1 = cur1.next;
                }
                if (overflow == 1) {
                    last.next = new ListNode(1);
                }
            }
        }
        return reverse(h1);
    }

    private ListNode reverse(ListNode root) {
        ListNode pre = null;
        ListNode cur = root;
        ListNode next = root.next;
        while (next != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;
        }
        cur.next = pre;
        return cur;
    }

    /**
     * 也可以使用栈来解决
     */
    public ListNode stack(ListNode a, ListNode b) {
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        ListNode cur1 = a;
        ListNode cur2 = b;
        while (cur1 != null) {
            s1.push(cur1);
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            s2.push(cur2);
            cur2 = cur2.next;
        }
        int overflow = 0;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            cur1 = s1.pop();
            cur2 = s2.pop();
            cur1.val = cur1.val + cur2.val + overflow;
            if (cur1.val > 9) {
                overflow = 1;
                cur1.val = cur1.val % 10;
            } else {
                overflow = 0;
            }
        }
        if (s1.isEmpty()) {
            if (s2.isEmpty()) {
                if (overflow == 1) {
                    ListNode res = new ListNode(1);
                    res.next = a;
                    return res;
                } else {
                    return a;
                }
            } else {
                cur2 = s2.pop();
                cur2.next = a;
                while (overflow == 1 && cur2 != null) {
                    cur2.val = cur2.val + 1;
                    if (cur2.val > 9) {
                        overflow = 1;
                        cur2.val = cur2.val % 10;
                    } else {
                        overflow = 0;
                    }
                    if (s2.isEmpty()) {
                        cur2 = null;
                    } else {
                        cur2 = s2.pop();
                    }
                }
                if (overflow == 1 && cur2 == null) {
                    ListNode res = new ListNode(1);
                    res.next = b;
                    return res;
                } else {
                    return b;
                }
            }
        } else {
            cur1 = s1.pop();
            while (overflow == 1 && cur1 != null) {
                cur1.val = cur1.val + 1;
                if (cur1.val > 9) {
                    overflow = 1;
                    cur1.val = cur1.val % 10;
                } else {
                    overflow = 0;
                }
                if (s1.isEmpty()) {
                    cur1 = null;
                } else {
                    cur1 = s1.pop();
                }
            }
            if (overflow == 1 && cur1 == null) {
                ListNode res = new ListNode(1);
                res.next = a;
                return res;
            } else {
                return a;
            }
        }
    }
}

