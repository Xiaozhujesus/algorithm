package priv.wz.tree;

/**
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。计算从根到叶子节点生成的所有数字之和。
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class SumNumbers {
    private int sum = 0;

    public int sumNumbers(TreeNode root) {
        dfs(0, root);
        return sum;
    }

    private void dfs(int preSum, TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            sum += root.val + preSum * 10;
            return;
        }
        int curSum = preSum * 10 + root.val;
        dfs(curSum, root.left);
        dfs(curSum, root.right);
    }
}
