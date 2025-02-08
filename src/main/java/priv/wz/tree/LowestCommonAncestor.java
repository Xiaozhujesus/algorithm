package priv.wz.tree;

/**
 * 给定一棵二叉树以及这棵树上的两个节点 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode a, TreeNode b) {
        return dfs(root, a, b).ans;
    }

    /**
     * 无法处理给定数值不在树中的情况，因为找到一个值就返回
     */
    public TreeNode dfs1(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null || root == a || root == b) {
            return root;
        }
        TreeNode left = dfs1(root.left, a, b);
        TreeNode right = dfs1(root.right, a, b);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    /**
     * 这种方法可以处理给定节点不再树种的情况
     */
    private Ans dfs(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return new Ans();
        }
        Ans left = dfs(root.left, a, b);
        if (left.found) {
            return left;
        }
        Ans right = dfs(root.right, a, b);
        if (right.found) {
            return right;
        }
        if (root == a || root == b) {
            if (left.ans != null || right.ans != null) {
                return new Ans(root, true);
            } else {
                return new Ans(root, false);
            }
        } else {
            if (left.ans != null) {
                if (right.ans != null) {
                    return new Ans(root, true);
                } else {
                    return left;
                }
            } else {
                if (right.ans != null) {
                    return right;
                } else {
                    return new Ans();
                }
            }
        }
    }

    class Ans {
        // found 为 TRUE，ans 存储公共祖先；否则如果 ans 不空，存储找到的其中一个 node
        TreeNode ans;
        // 是否找到公共祖先
        boolean found;

        public Ans() {
        }

        Ans(TreeNode ans, boolean found) {
            this.found = found;
            this.ans = ans;
        }
    }
}


