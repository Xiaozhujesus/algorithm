package priv.wz.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给出二叉树的根节点root，树上每个节点都有一个不同的值。
 * 如果节点值在to_delete中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 */
public class DelNodes {
    List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for (int d : to_delete) {
            set.add(d);
        }

        dfs(null, root, set);
        if (!set.contains(root.val)) {
            res.add(root);
        }
        // dfs2(null, root, set); 不需要额外处理一次 root
        return res;
    }

    private void dfs(TreeNode parent, TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }

        dfs(root, root.left, set);
        dfs(root, root.right, set);

        if (set.contains(root.val)) {
            if (parent != null && parent.left == root) {
                parent.left = null;
            }
            if (parent != null && parent.right == root) {
                parent.right = null;
            }

            if (root.left != null) {
                res.add(root.left);
            }
            if (root.right != null) {
                res.add(root.right);
            }
        }
    }

    private void dfs2(TreeNode parent, TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }
        if (set.contains(root.val)) {
            if (parent != null) {
                if (parent.left == root) {
                    parent.left = null;
                }
                if (parent.right == root) {
                    parent.right = null;
                }
            }
            dfs2(null, root.left, set);
            dfs2(null, root.right, set);
        } else {
            if (parent == null) {
                res.add(root);
            }
            dfs2(root, root.left, set);
            dfs2(root, root.right, set);
        }
    }
}
