package priv.wz.tree;

/**
 * 给定一个二叉树，找出其最小深度。最小深度是从根节点到最近叶子节点的最短路径上的节点数量。说明: 叶子节点是指没有子节点的节点。
 */
public class MinDepth {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        if (left == 0) {
            return 1;
        }
        int right = minDepth(root.right);
        return 1 + Math.min(left, right);
    }
}
