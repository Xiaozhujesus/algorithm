package priv.wz.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Map<Integer, Integer> merge = Stream.of(l, r)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)
                ));

        for (Map.Entry<Integer, Integer> entry : merge.entrySet()) {
            int curValue = entry.getKey() + root.val;
            ret.put(curValue, entry.getValue());
            if (curValue == target) {
                ans += entry.getValue();
            }
        }
        // 只有当前节点的情况
        ret.put(root.val, ret.getOrDefault(root.val, 0) + 1);
        if (root.val == target) {
            ans++;
        }
        return ret;
    }

    public int pathSum(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = rootSum(root, targetSum);
        ret += pathSum(root.left, targetSum);
        ret += pathSum(root.right, targetSum);
        return ret;
    }

    public int rootSum(TreeNode root, long targetSum) {
        int ret = 0;

        if (root == null) {
            return 0;
        }
        int val = root.val;
        if (val == targetSum) {
            ret++;
        }

        ret += rootSum(root.left, targetSum - val);
        ret += rootSum(root.right, targetSum - val);
        return ret;
    }
}
