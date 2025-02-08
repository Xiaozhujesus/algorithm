package priv.wz.tree;

/**
 * 给你一个二叉树，返回该树最深的叶节点的最近公共祖先，若最深的只有一个节点，那么该节点就是自己的最近公共祖先
 */
public class LcaDeepestLeaves {

    private TreeNode ans;
    private int maxDepth = -1;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    /**
     * 返回当前子树的叶子节点的最大高度
     */
    private int dfs(TreeNode root, int depth) {
        // 用来搜素最大深度
        if (root == null) {
            if (depth > maxDepth) {
                maxDepth = depth;
            }
            return depth;
        }
        int left = dfs(root.left, depth + 1);
        int right = dfs(root.right, depth + 1);
        // 只有左右相等才可能是答案，这里不需要 left >= maxDepth，因为后序遍历先处理叶节点，left 不可能大于 maxDepth
        if (left == right && left == maxDepth) {
            ans = root;
        }
        return Math.max(left, right);
    }
}
