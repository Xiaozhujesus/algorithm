package priv.wz.tree;

/**
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。
 * 如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 * 给出遍历输出 S，还原树并返回其根节点 root。
 *
 * 示例 1：
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 *
 * 示例 2：
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 *
 * 示例 3：
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 */
public class RecoverFromPreorder {

    private int pos = 0;

    // 参考二叉树序列化反序列化
    public TreeNode recoverFromPreorder(String traversal) {
        return recover(traversal, -1);
    }

    private TreeNode recover(String s, int preDepth) {
        int count = 0;
        int cur = pos;
        TreeNode ans = null;
        while (cur < s.length() && s.charAt(cur) == '-') {
            count++;
            cur++;
        }
        if (count > preDepth) {
            pos = cur;
            while (cur < s.length() && s.charAt(cur) != '-') {
                cur++;
            }
            ans = new TreeNode(Integer.parseInt(s.substring(pos, cur)));
            pos = cur;
            ans.left = recover(s, preDepth + 1);
            ans.right = recover(s, preDepth + 1);
        }
        return ans;
    }
}
