package priv.wz.tree;

/**
 * 给定二叉树，找出存在于不同节点 A 和 B 间差值绝对值的最大值 V，也就是 V = |A.val - B.val|，要求 A 是 B 的祖先。
 * 如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先
 */
public class MaxAncestorDiff {

    private int ans = Integer.MIN_VALUE;

    public int maxAncestorDiff(TreeNode root) {
        dfs(root);
        return ans;
    }

    /**
     * 返回这棵树的最大最小值，int[0]最大，int[1]最小，同时计算以根为锚点可以得到的最大差值实时更新 ans
     * 其实就是搜素，因为结果一定是子树的根与该子树上的某个节点组成，dfs 搜素所有子树
     */
    public int[] dfs(TreeNode root) {
        if (root.left == null) {
            if (root.right == null) {
                return new int[]{root.val, root.val};
            } else {
                int[] right = dfs(root.right);
                update(right, root.val);
                return right;
            }
        } else {
            int[] left = dfs(root.left);
            if (root.right == null) {
                update(left, root.val);
                return left;
            } else {
                int[] right = dfs(root.right);
                update(left, root.val);
                update(right, root.val);
                return getBound(left, right);
            }
        }
    }

    // 更新最大最小值，同时更新 ans，arr = {max, min}
    private void update(int[] arr, int val) {
        if (val > arr[0]) {
            int abs = val - arr[1];
            if (abs > ans) {
                ans = abs;
            }
            arr[0] = val;
        } else if (val < arr[1]) {
            int abs = arr[0] - val;
            if (abs > ans) {
                ans = abs;
            }
            arr[1] = val;
        } else {
            int max = Math.max(arr[0] - val, val - arr[1]);
            if (max > ans) {
                ans = max;
            }
        }
    }

    private int[] getBound(int[] left, int[] right) {
        left[0] = Math.max(left[0], right[0]);
        left[1] = Math.min(left[1], right[1]);
        return left;
    }
}
