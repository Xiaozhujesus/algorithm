package priv.wz.recursive;

import priv.wz.list.ListNode;
import priv.wz.tree.TreeNode;

/**
 * 给定一个单链表的头节点 head，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差不超过 1。
 */
public class SortedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        return convert(head, cur);
    }

    private TreeNode convert(ListNode begin, ListNode end) {
        if (begin == end) {
            return new TreeNode(begin.val);
        }
        if (begin.next == end) {
            return new TreeNode(end.val, new TreeNode(begin.val), null);
        }
        ListNode slow = begin, fast = begin, prev = null;
        while (fast != end && fast.next != end) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
        }
        return new TreeNode(slow.val, convert(begin, prev), convert(slow.next, end));
    }
}
