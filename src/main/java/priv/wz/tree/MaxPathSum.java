package priv.wz.tree;

/**
 * 给定一个非空二叉树，返回其最大路径和。
 * <p>
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * <p>
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 输出: 42
 */
public class MaxPathSum {
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }

    /**
     * 返回以该点为路径一端的最大路径和
     *
     * @param root
     * @return
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        int curMax = root.val;
        if (left > 0) {
            curMax += left;
        }
        if (right > 0) {
            curMax += right;
        }
        if (curMax > max) {
            max = curMax;
        }
        if (left >= right && left > 0) {
            return left + root.val;
        } else if (right >= left && right > 0) {
            return right + root.val;
        } else {
            return root.val;
        }
    }
}

/**
 * 类似数组求最大子段和，只要是连续的，就可以用滑动窗口
 */