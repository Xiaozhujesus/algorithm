package priv.wz.tree;

/**
 * 输入一个二叉树，输出其镜像
 */
public class Mirror {
    public TreeNode mirror(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode tmpLeft = root.left;
        root.left = mirror(root.right);
        root.right = mirror(tmpLeft);
        return root;
    }
}
