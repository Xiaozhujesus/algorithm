package priv.wz.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先搜素
 */
public class BFS {
    public void bfs(TreeNode root) {
        if (root == null) {
            return;
        } else {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            int level = 0;
            while (!q.isEmpty()) {
                // 当前层的节点数
                int size = q.size();
                System.out.println("level " + level++);
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    System.out.println(cur.val);
                    if (cur.left != null) {
                        q.offer(cur.left);
                    }
                    if (cur.right != null) {
                        q.offer(cur.right);
                    }
                }
            }
        }
    }
}
