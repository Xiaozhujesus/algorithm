package priv.wz.tree;

public class InsertIntoBST {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode insert = new TreeNode(val);
        if (root == null) {
            return insert;
        }
        TreeNode current = root;
        while (true) {
            if (val == current.val) {
                return root;
            }
            if (val < current.val) {
                if (current.left == null) {
                    current.left = insert;
                    return root;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = insert;
                    return root;
                } else {
                    current = current.right;
                }
            }
        }
    }
}
