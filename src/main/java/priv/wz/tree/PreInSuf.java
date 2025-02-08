package priv.wz.tree;

import java.util.ArrayList;
import java.util.List;

public class PreInSuf {

    /**
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders (TreeNode root) {
        // write code here
        int[][] ret = new int[3][];
        if(root == null) {
            return ret;
        }
        List<Integer> list = new ArrayList<>();
        pre(root, list);
        ret[0] = list.stream().mapToInt(i->i).toArray();
        list.clear();
        in(root,list);
        ret[1] = list.stream().mapToInt(i->i).toArray();
        list.clear();
        suf(root, list);
        ret[2] = list.stream().mapToInt(i->i).toArray();
        return ret;
    }

    private void pre(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        pre(root.left, list);
        pre(root.right, list);
    }

    private void in(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        in(root.left, list);
        list.add(root.val);
        in(root.right, list);
    }

    private void suf(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        suf(root.left, list);
        suf(root.right, list);
        list.add(root.val);
    }
}
