package priv.wz.tree;

import java.util.Stack;

/**
 * 深度优先搜索，包括：
 * 前，中，后序三种遍历
 */
public class DFS {
    /**
     * 前序遍历
     *
     * @param root
     */
    //递归
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    //迭代
    public void preOrder2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            System.out.println(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    //迭代
    public void preOrder3(TreeNode root) {
        if (root == null) {
            return;
        }
        //stack里都是右子树没处理的
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            System.out.println(cur.val);
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty()) {
            cur = stack.pop().right;
            while (cur != null) {
                System.out.println(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
        }
    }
    //简化一下上面的代码，有重复代码
    public void preOrder4(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                System.out.println(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop().right;
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    //递归
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    //迭代
    public void inOrder2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            System.out.println(cur.val);
            if (cur.right != null) {
                cur = cur.right;
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
    }

    //简化一下上面的代码，因为有重复代码
    public void inOrder3(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack  = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            System.out.println(cur.val);
            cur = cur.right;
        }
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    //递归
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }

    //迭代
    public void postOrder2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<NodeWithState> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(new NodeWithState(cur, false));
            cur = cur.left;
        }
        while (!stack.isEmpty()) {
            NodeWithState tmp = stack.pop();
            if (tmp.done) {
                System.out.println(tmp.node.val);
            } else {
                tmp.done = true;
                stack.push(tmp);
                cur = tmp.node.right;
                while (cur != null) {
                    stack.push(new NodeWithState(cur, false));
                    cur = cur.left;
                }
            }
        }
    }

    //简化下上面的代码
    public void postOrder3(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<NodeWithState> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(new NodeWithState(cur, false));
                cur = cur.left;
            }
            NodeWithState tmp = stack.pop();
            if (tmp.done) {
                System.out.println(tmp.node.val);
                cur = null;
            } else {
                tmp.done = true;
                stack.push(tmp);
                cur = tmp.node.right;
            }

        }
    }

    class NodeWithState {
        TreeNode node;
        boolean done;

        NodeWithState(TreeNode node, boolean done) {
            this.done = done;
            this.node = node;
        }
    }
}
