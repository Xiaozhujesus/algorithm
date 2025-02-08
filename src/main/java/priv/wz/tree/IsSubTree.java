package priv.wz.tree;

/**
 * tree1：
 * 8
 * | \
 * 3  4
 *    | \
 *    1  2
 *    |
 *    2
 *
 * tree2：
 * 4
 * | \
 * 1  2
 * 则tree2是tree1的子树
 * 思路包含两部分，一部分是搜索相等的root节点，另一部分是从相等的root开始比较
 */
public class IsSubTree {
    // DFS
    public boolean isSubtree(TreeNode root1, TreeNode root2) {
        if (equal(root1, root2)) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        return isSubtree(root1.left, root2) || isSubtree(root1.right, root2);
    }

    /**
     * root1 与 root2 完全一致
     */
    private boolean equal(TreeNode root1, TreeNode root2) {
        if (root1 == null ) {
            if ( root2 == null) {
                return true;
            } else {
                return false;
            }
        }
        if (root2 == null) {
            return false;
        } else {
            if (root1.val != root2.val) {
                return false;
            } else {
                return equal(root1.left, root2.left) && equal(root1.right, root2.right);
            }
        }
    }
}
