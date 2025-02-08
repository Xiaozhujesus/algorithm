package priv.wz.tree;

import java.util.Stack;

public class BSTIterator {

    /**
     * 迭代版本的 DFS
     */
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        if (root != null) {
            stack.push(root);
            while (root.left != null) {
                stack.push(root.left);
                root = root.left;
            }
        }
    }

    public TreeNode next() {
        if (stack.isEmpty()) {
            throw new RuntimeException("there is no more element!");
        }
        TreeNode cur = stack.pop();
        TreeNode ret = cur;
        if (cur.right != null) {
            stack.push(cur.right);
            cur = cur.right;
            while (cur.left != null) {
                stack.push(cur.left);
                cur = cur.left;
            }
        }
        return ret;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
