package priv.wz.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个值 sum，请找出所有的根节点到叶子节点的节点值之和等于 sum 的路径，
 */
public class PathSum {
    private int curSum = 0;
    private int target;
    private List<Integer> curPath = new ArrayList<>();
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) {
            return ans;
        }
        this.target = target;
        dfs(root);
        return ans;
    }

    /**
     * 搜索，有点回溯的味道，状态改变和恢复
     */
    private void dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            if ( curSum + root.val == target) {
                List<Integer> tmp = new ArrayList<>();
                tmp.addAll(curPath);
                tmp.add(root.val);
                ans.add(tmp);
            }
            return;
        }

        if (root.left != null) {
            curPath.add(root.val);
            curSum += root.val;
            dfs(root.left);
            curPath.remove(curPath.size() - 1);
            curSum = curSum - root.val;
        }
        if (root.right != null) {
            curPath.add(root.val);
            curSum += root.val;
            dfs(root.right);
            curPath.remove(curPath.size() - 1);
            curSum = curSum - root.val;
        }
    }
}
