package priv.wz.tree;

import java.util.Stack;

/**
 * 二叉树的最大深度
 */
public class MaxDepth {
    private int ans;

    //递归
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return Math.max(l, r) + 1;
    }

    public void dfs1(TreeNode root, int depth) {
        if (root == null) {
            ans = Math.max(depth, ans);
            return;
        }
        dfs1(root.left, depth + 1);
        dfs1(root.right, depth + 1);
    }

    /**
     * 注意这个求树高的深搜和DFS里的有点不同，需要一个NodeWithDepth节点与高度绑定的结构，不能使用外部变量
     * 配合DFS来追踪树高，因为dfs里stack.pop()时候高度变化有可能不是1个单位的高度，不好确定
     * <p>
     * 这个dfs的stack里存的不是右侧子树未处理的节点，而是左右子树都没处理的节点
     *
     * @param root
     * @return
     */

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        Stack<NodeWithDepth> stack = new Stack<>();
        stack.push(new NodeWithDepth(root, 1));
        while (!stack.isEmpty()) {
            NodeWithDepth cur = stack.pop();
            if (cur.node != null) {
                if (cur.depth > max) {
                    max = cur.depth;
                }
                stack.push(new NodeWithDepth(cur.node.right, cur.depth + 1));
                stack.push(new NodeWithDepth(cur.node.left, cur.depth + 1));
            }
        }
        return max;
    }

    class NodeWithDepth {
        TreeNode node;
        int depth;

        NodeWithDepth(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
