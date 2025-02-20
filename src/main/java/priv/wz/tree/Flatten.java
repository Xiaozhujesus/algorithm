package priv.wz.tree;

/**
 * 输入一棵二叉树，将该二叉搜索树展开成一个双向链表，展开后的链表应该与二叉树中遍历顺序相同。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向
 * 展开后的链表应该同样使用 TreeNode，其中 left 指针指向链表中前一个节点，right 指针指向链表中下一个结点。
 * <p>
 * 展开成单链表呢？
 * <p>
 * 单，双链表可以用同样的方法，下面展示两种不同的方法
 */
public class Flatten {
    // 单链表，递归
    public TreeNode flatten(TreeNode root) {
        return util(root).head;
    }

    public Pair util(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode head = root, tail = root;
        Pair left = util(root.left);
        Pair right = util(root.right);
        if (left != null) {
            head = left.head;
            left.tail.right = root;
            root.left = null;
        }
        if (right != null) {
            tail = right.tail;
            root.right = right.head;
        }
        return new Pair(head, tail);
    }


    class Pair {
        TreeNode head;
        TreeNode tail;

        public Pair(TreeNode head, TreeNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    // 双链表，中序遍历
    TreeNode pre = null;
    TreeNode head = null;

    public TreeNode flatten2(TreeNode root) {
        inOrder(root);
        return head;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        root.left = pre;
        if (pre != null) {
            pre.right = root;
        }
        pre = root;
        if (head == null) {
            head = root;
        }
        inOrder(root.right);
    }
}
