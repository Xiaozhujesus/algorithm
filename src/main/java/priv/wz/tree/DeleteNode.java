package priv.wz.tree;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的key对应的节点，并保证二叉搜索树的性质不变。
 * 返回二叉搜索树（有可能被更新）的根节点的引用。
 */
public class DeleteNode {

    public TreeNode deleteNode(TreeNode root, int key) {

        TreeNode cur = root;
        TreeNode parent = null;
        while (cur != null && cur.val != key) {
            parent = cur;
            if (key < cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        // 不存在删除节点
        if (cur == null) {
            return root;
        }
        //用删除节点的前置或者后置节点替换当前节点，树本质就是链表，BST就是有序链表
        if (cur.left != null) {
            // 删除的节点左子树不空，从左子树找删除节点的前置节点替换当前节点
            TreeNode parent2 = cur;
            TreeNode pre = cur.left;
            while (pre.right != null) {
                parent2 = pre;
                pre = pre.right;
            }
            cur.val = pre.val;
            if (parent2 == cur) {
                parent2.left = pre.left;
            } else {
                parent2.right = pre.left;
            }
            return root;
        } else if (cur.right != null) {
            // 删除的节点右子树不空，从左子树找删除节点的后置节点替换当前节点
            TreeNode parent2 = cur;
            TreeNode sur = cur.right;
            while (sur.left != null) {
                parent2 = sur;
                sur = sur.left;
            }
            cur.val = sur.val;
            if (parent2 == cur) {
                parent2.right = sur.right;
            } else {
                parent2.left = sur.right;
            }
            return root;
        } else {
            // 删除的节点左右都空
            if (parent != null) {
                if (parent.left == cur) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                return root;
            } else {
                //删除根
                return null;
            }
        }
    }
}
