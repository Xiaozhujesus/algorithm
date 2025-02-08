package priv.wz.tree;

/**
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如，(5，3，7，2，4，6，8) 中，按结点数值大小顺序第三小结点的值为4。
 */
public class KthSmallest {
    private int count;
    private int k;

    public TreeNode kthSmallest(TreeNode root, int k) {
        this.k = k;
        return dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode ans = dfs(root.left);
        if (ans != null) {
            return ans;
        }
        count++;
        if (count == k){
            return root;
        } else {
            return dfs(root.right);
        }
    }
}
