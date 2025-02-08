package priv.wz.tree;

/**
 * 二叉搜索树两个节点的最近公共祖先
 */
public class LowestCommonAncestorBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode small, TreeNode big) {
        if (small.val < root.val && root.val < big.val) {
            return root;
        }
        if (small.val > root.val) {
            return lowestCommonAncestor(root.right, small, big);
        }
        return lowestCommonAncestor(root.left, small, big);
    }
}
