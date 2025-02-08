package priv.wz.tree;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树
 */
public class IsValidBST {

    // 中序遍历，比较相邻两个节点
    private TreeNode pre = null;

    public boolean isValidBST2(TreeNode root) {
        return inOrder(root);
    }

    private boolean inOrder(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (inOrder(root.left)) {
            if (pre != null && root.val < pre.val) {
                return false;
            } else {
                pre = root;
                return inOrder(root.right);
            }
        }
        return false;
    }

    // 递归
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return util(root).isValidBST;
    }

    public Status util(TreeNode root) {
        if (root == null) {
            return null;
        }
        Status left = util(root.left);
        Status right = util(root.right);
        Status ans = new Status();
        if (left != null) {
            if (!left.isValidBST || left.max >= root.val) {
                return ans;
            } else {
                ans.min = left.min;
            }
        } else {
            ans.min = root.val;
        }
        if (right != null) {
            if (!right.isValidBST || right.min <= root.val) {
                return ans;
            } else {
                ans.max = right.max;
            }
        } else {
            ans.max = root.val;
        }
        ans.isValidBST = true;
        return ans;
    }

    class Status {
        boolean isValidBST;
        int min;
        int max;
    }
}

