package priv.wz.tree;

/**
 * 二叉搜索树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 */
public class RecoverTree {
    private TreeNode pre;
    private TreeNode first;
    private TreeNode second;

    public void recoverTree(TreeNode root) {
        inOrder(root);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        if (pre != null && root.val < pre.val) {
            if (first == null) {
                first = pre;
            }
            second = root;
        }
        pre = root;
        inOrder(root.right);
    }
}
