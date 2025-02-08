package priv.wz.dp;

import priv.wz.tree.TreeNode;

/**
 * 小偷又发现了一个新的可行窃的地区，这个地区只有一个入口，我们称之为 root。除了 root 之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。如果两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * 给定二叉树的 root，返回在不触动警报的情况下，小偷能够盗取的最高金额。
 * <p>
 * 示例 1:
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释:小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * <p>
 * 示例 2:
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释:小偷一晚能够盗取的最高金额 4 + 5 = 9
 */
public class HouseRobber3 {
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        DPNode dproot = buildTree(root);
        fill(dproot);
        return Math.max(dproot.robMax, dproot.noRobMax);
    }

    private void fill(DPNode root) {
        if (root.left != null) {
            fill(root.left);
        }
        if (root.right != null) {
            fill(root.right);
        }

        root.robMax = root.val;
        if (root.left != null) {
            root.robMax += root.left.noRobMax;
            root.noRobMax = Math.max(root.left.noRobMax, root.left.robMax);
        }
        if (root.right != null) {
            root.robMax += root.right.noRobMax;
            root.noRobMax += Math.max(root.right.noRobMax, root.right.robMax);
        }
    }

    private DPNode buildTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        DPNode ans = new DPNode();
        ans.left = buildTree(root.left);
        ans.right = buildTree(root.right);
        ans.val = root.val;
        return ans;
    }

    class DPNode {
        DPNode left, right;
        int robMax, noRobMax, val;
    }

    public int rob2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] util = util(root);
        return Math.max(util[0], util[1]);
    }

    // 上面的构建树可以优化，因为 root 仅仅跟 left 和 right 有关
    // 返回root打劫和不打劫的最大值
    public int[] util(TreeNode root) {
        int[] ans = new int[2];
        if (root == null) {
            return ans;
        }
        int[] left = util(root.left);
        int[] right = util(root.right);

        ans[0] = root.val + left[1] + right[1];
        ans[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return ans;
    }
}
