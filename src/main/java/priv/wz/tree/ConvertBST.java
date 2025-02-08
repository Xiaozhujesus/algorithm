package priv.wz.tree;

/**
 * 给出二叉搜索树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
 * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 */
public class ConvertBST {

    private int sum = 0;
    // 相当于一个有序数组，从后往前累加
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root) {
        if (root != null) {
            dfs(root.right);
            sum += root.val;
            root.val = sum;
            dfs(root.left);
        }
    }
}
