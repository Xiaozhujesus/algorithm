package priv.wz.tree;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 */
public class RebuildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return util(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    /**
     * pre[a..b]是前序，in[c..d]是对应的中序，左闭右闭，返回对应的树
     */
    private TreeNode util(int[] pre, int a, int b, int[] in, int c, int d) {
        if (a > b) {
            return null;
        }
        if (a == b) {
            return new TreeNode(pre[a]);
        }
        TreeNode root = new TreeNode(pre[a]);
        int i;
        for (i = c; i <= d; i++) {
            if (in[i] == pre[a]) {
                break;
            }
        }
        root.left = util(pre, a + 1, a + i - c, in, c, i - 1);
        root.right = util(pre, a + i - c + 1, b, in, i + 1, d);
        return root;
    }
}
