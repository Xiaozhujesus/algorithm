package priv.wz.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的路径的数目。
 * <p>
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class PathSum3 {
    private int ans;
    private int target;

    public int pathSum(TreeNode root, int targetSum) {
        this.target = targetSum;
        dfs(root);
        return ans;
    }

    /**
     * key 是以root 为起点的路径可能的和，value 是这个和有多少种
     */
    private Map<Integer, Integer> dfs(TreeNode root) {
        Map<Integer, Integer> ret = new HashMap<>();
        if (root == null) {
            return ret;
        }
        Map<Integer, Integer> l = dfs(root.left);
        Map<Integer, Integer> r = dfs(root.right);
        Map<Integer, Integer> merge;
        if (l.isEmpty()) {
            merge = r;
        } else if (r.isEmpty()) {
            merge = l;
        } else {
            for (Map.Entry<Integer, Integer> entry : r.entrySet()) {
                Integer lvalue = l.getOrDefault(entry.getKey(), 0);
                l.put(entry.getKey(), lvalue + entry.getValue());
            }
            merge = l;
        }
        for (Map.Entry<Integer, Integer> entry : merge.entrySet()) {
            if (entry.getKey() != 0) {
                int curValue = entry.getKey() + root.val;
                ret.put(curValue, entry.getValue());
                if (curValue == target) {
                    ans += entry.getValue();
                }
            }
        }
        Integer zero = merge.getOrDefault(0, 0);
        ret.put(root.val, zero + 1);
        if (root.val == target) {
            ans += (zero + 1);
        }
        return ret;
    }
}
