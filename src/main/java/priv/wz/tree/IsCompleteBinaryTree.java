package priv.wz.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一棵二叉树，请判断该二叉树是否为完全二叉树。
 */
public class IsCompleteBinaryTree {

    /**
     * 广度优先
     */
    private boolean BFS(TreeNode root) {
        boolean ans = true;
        if (root == null) {
            return true;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur == null) {
                break;
            } else {
                q.offer(cur.left);
                q.offer(cur.right);
            }
        }
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur != null) {
                ans = false;
                break;
            }
        }
        return ans;
    }
}
