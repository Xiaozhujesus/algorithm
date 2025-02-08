package priv.wz.tree;

/**
 * 完全二叉树节点数
 */
public class CountNodes {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 0, high = (1 << level) - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (exists(root, level, mid)) {
                if (low == mid) {
                    if (exists(root, level, high)) {
                        low = high;
                    }
                    break;
                }
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return (1 << level) + low;
    }

    /**
     * 判断完全二叉树 level 层第 k 个节点是否存在
     * level 和 k 都从 0 开始
     */
    public boolean exists(TreeNode root, int level, int k) {
        if (level == 0) {
            return root != null;
        }
        int mask = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && mask > 0) {
            if ((mask & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            mask >>= 1;
        }
        return node != null;
    }
}