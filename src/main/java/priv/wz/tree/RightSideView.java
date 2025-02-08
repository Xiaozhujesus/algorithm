package priv.wz.tree;

import java.util.*;

/**
 * 给定一个二叉树的根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 */
public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return Collections.EMPTY_LIST;
        }
        return BFS(root);
    }

    private List<Integer> BFS(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0) {
                    ans.add(cur.val);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
            }
        }
        return ans;
    }
}
