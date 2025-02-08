package priv.wz.tree;

/**
 * 判断 2 颗树是否是完全相等的。
 */
public class SameTree {
    public boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null) {
            if (b == null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (b == null) {
                return false;
            } else {
                return a.val == b.val && isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
            }
        }
    }
}
