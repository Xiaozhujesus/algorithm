package priv.wz.tree;

/**
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。
 * 合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 * <p>
 * 返回合并后的二叉树。
 */
public class MergeTrees {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return merge(root1, root2);
    }

    /**
     * @param a 第一棵树相同位置的节点
     * @param b 第二棵树相同位置的节点
     * @return
     */
    private TreeNode merge(TreeNode a, TreeNode b) {
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        } else {
            return new TreeNode(a.val + b.val, merge(a.left, b.left), merge(a.right, b.right));
        }
    }
}
