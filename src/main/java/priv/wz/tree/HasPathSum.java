package priv.wz.tree;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。说明: 叶子节点是指没有子节点的节点。
 */
public class HasPathSum {

    public boolean hasPathSum(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.val == target && root.left == null && root.right == null) {
            return true;
        }
        int left = target - root.val;
        return hasPathSum(root.left, left) || hasPathSum(root.right, left);
    }
}
