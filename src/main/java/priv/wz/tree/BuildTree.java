package priv.wz.tree;

public class BuildTree {
    /**
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
     * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * 输出: [3,9,20,null,null,15,7]
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return build1(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode build1(int[] pre, int preBegin, int preEnd, int[] in, int inBegin, int inEnd) {
        if (preBegin > preEnd) {
            return null;
        }
        if (preBegin == preEnd) {
            return new TreeNode(pre[preBegin]);
        }
        TreeNode root = new TreeNode(pre[preBegin]);
        int rootPos = inBegin;
        for (int i = inBegin + 1; i <= inEnd; i++) {
            if (in[i] == root.val) {
                rootPos = i;
                break;
            }
        }
        int left = rootPos - inBegin;
        root.left = build1(pre, preBegin + 1, preBegin + left, in, inBegin, rootPos - 1);
        root.right = build1(pre, preBegin + left + 1, preEnd, in, rootPos + 1, inEnd);
        return root;
    }

    public static void main(String[] args) {
        BuildTree buildTree = new BuildTree();
        TreeNode root1 = buildTree.buildTree1(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        TreeNode root2 = buildTree.buildTree2(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
    }


    /**
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历，
     * postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * 输出：[3,9,20,null,null,15,7]
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        return build2(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build2(int[] in, int inBegin, int inEnd, int[] post, int postBegin, int postEnd) {
        if (inBegin > inEnd) {
            return null;
        }
        if (inBegin == inEnd) {
            return new TreeNode(in[inBegin]);
        }
        TreeNode root = new TreeNode(post[postEnd]);
        int rootPos = inBegin;
        for (int i = inBegin + 1; i <= inEnd; i++) {
            if (in[i] == root.val) {
                rootPos = i;
                break;
            }
        }
        int left = rootPos - inBegin;
        root.left = build2(in, inBegin, rootPos - 1, post, postBegin, postBegin + left - 1);
        root.right = build2(in, rootPos + 1, inEnd, post, postBegin + left, postEnd - 1);
        return root;
    }
}
