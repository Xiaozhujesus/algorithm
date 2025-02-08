package priv.wz.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。
 * 序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），
 * 以 ！ 表示一个结点值的结束（value!）。
 * <p>
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 * 
 * 二叉树的序列化反序列化是 json 序列化反序列化的特例
 */
public class SerializeDeserialize {

    private static final String SEP = ",";
    public String encode(Node root) {
        StringBuilder sb = new StringBuilder();
        if (root.childs.isEmpty()) {
            return sb.append(0).append(SEP).append(root.value).append(SEP).toString();
        }

        for (Node cur : root.childs) {
            String content = encode(cur);
            sb.append(content);
        }
        return new StringBuilder().append(sb.length()).append(SEP).append(root.value).append(SEP).append(sb).toString();
    }

    /**
     * 1[2[4,5],3[6]]
     * len,root-value,childslen,root-value,childs...
     * 递归定义的终止为len=0，就说明是叶子节点
     *
     * @param input
     * @return
     */
    public Node decode(String input) {
        int begin = 0, end;
        int firstSep = input.indexOf(SEP);
        int len = Integer.parseInt(input.substring(begin, firstSep));
        firstSep++;
        int secondSep = input.indexOf(SEP, firstSep);
        Node root = new Node(Integer.parseInt(input.substring(firstSep, secondSep)));
        secondSep++;
        end = secondSep + len;
        begin = secondSep;
        while (begin < end) {
            firstSep = input.indexOf(SEP, begin);
            len = Integer.parseInt(input.substring(begin, firstSep));
            firstSep++;
            secondSep = input.indexOf(SEP, firstSep);
            secondSep++;
            root.childs.add(decode(input.substring(begin, secondSep + len)));
            begin = secondSep + len;
        }
        return root;
    }

    static class Node {
        int value;
        List<Node> childs;

        Node(int value) {
            this.value = value;
            this.childs = new ArrayList<>();
        }
    }
    
    
    private int pos;

    /**
     * dfs 序列化
     *
     * @param root
     * @return
     */
    String Serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        if (root == null) {
            return "#";
        }
        stringBuilder.append(root.val).append(",");
        stringBuilder.append(Serialize(root.left)).append(",");
        stringBuilder.append(Serialize(root.right));
        return stringBuilder.toString();
    }

    /**
     * 反序列化过程中pos变量是关键，该变量告诉右子树从数组的哪个点开始
     */
    TreeNode Deserialize(String str) {
        String[] strs = str.split(",");
        return util(strs);
    }

    private TreeNode util(String[] strs) {
        if (strs[pos].equals("#")) {
            pos++;
            return null;
        } else {
            TreeNode res = new TreeNode(Integer.valueOf(strs[pos]));
            pos++;
            res.left = util(strs);
            res.right = util(strs);
            return res;
        }
    }

    /**
     * bfs序列化
     */
    String Ser(TreeNode root) {
        if (root == null) {
            return "#";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur == null) {
                stringBuilder.append("#").append(",");
            } else {
                stringBuilder.append(cur.val).append(",");
                q.offer(cur.left);
                q.offer(cur.right);
            }
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    TreeNode Des(String str) {
        TreeNode root = null;
        String[] strs = str.split(",");
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode cur = getNode(strs[pos++]);
        root = cur;
        if (cur != null) {
            q.offer(cur);
        }
        while (!q.isEmpty()) {
            cur = q.poll();
            cur.left = getNode(strs[pos++]);
            cur.right = getNode(strs[pos++]);
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
        return root;
    }

    private TreeNode getNode(String str) {
        if (str.equals("#")) {
            return null;
        } else {
            return new TreeNode(Integer.valueOf(str));
        }
    }
}
