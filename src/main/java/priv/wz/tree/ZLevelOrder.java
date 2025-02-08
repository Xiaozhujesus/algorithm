package priv.wz.tree;

import java.util.*;

/**
 * 给定一个二叉树，返回该二叉树的之字形层序遍历，（第一层从左向右，下一层从右向左，一直这样交替）
 */
public class ZLevelOrder {

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 标记奇偶层
        boolean flag = true;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> l = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (flag) {
                    l.add(cur.val);
                } else {
                    l.add(0, cur.val);
                }
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
            flag = !flag;
            res.add(l);
        }
        return res;
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // a是单数层的节点，并且从左到右
        Stack<TreeNode> a = new Stack<>();
        // b是双数层的节点，并且从右到左
        Stack<TreeNode> b = new Stack<>();
        a.push(root);
        ArrayList<Integer> cur = new ArrayList<>();
        while (!a.isEmpty() || !b.isEmpty()) {
            if (a.isEmpty()) {
                while (!b.isEmpty()) {
                    TreeNode n = b.pop();
                    cur.add(n.val);
                    if (n.right != null) {
                        a.push(n.right);
                    }
                    if (n.left != null) {
                        a.push(n.left);
                    }
                }
                res.add(cur);
                cur = new ArrayList<>();
            } else {
                while (!a.isEmpty()) {
                    TreeNode n = a.pop();
                    cur.add(n.val);
                    if (n.left != null) {
                        b.push(n.left);
                    }
                    if (n.right != null) {
                        b.push(n.right);
                    }
                }
                res.add(cur);
                cur = new ArrayList<>();
            }
        }
        return res;
    }
}
