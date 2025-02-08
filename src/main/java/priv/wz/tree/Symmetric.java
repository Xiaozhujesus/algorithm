package priv.wz.tree;

/**
 * 判断二叉树是否对称
 */
public class Symmetric {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return util(root.left, root.right);
    }

    private boolean util(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            return left.val == right.val && util(left.left, right.right) && util(left.right, right.left);
        } else {
            return false;
        }
    }
}
