package priv.wz.hash;

import priv.wz.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *
 * 两棵树重复是指它们具有相同的结构以及相同的结点值
 */
public class DuplicateSubtree {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, List<TreeNode>> map = new HashMap<>();
        List<TreeNode> ret = new ArrayList<>();
        dfs(root, map);
        for (List<TreeNode> list : map.values()) {
            if (list.size() > 1) {
                ret.add(list.get(0));
            }
        }
        return ret;
    }

    /**
     * 无法根据前序遍历还原二叉树，但是如果加入其他额外的信息，则可以还原二叉树
     * 中序配合前序或者后序，可以还原二叉树
     */
    public String dfs(TreeNode root, Map<String, List<TreeNode>> map) {
        String encode;
        // 对 null 节点也输出值可以保证结构不同的树 dfs 序列化后一定不同
        if (root == null) {
            encode = "# ";
        } else {
            encode = root.val + " " + dfs(root.left, map) + " " + dfs(root.right, map);
            if (map.containsKey(encode)) {
                map.get(encode).add(root);
            } else {
                List<TreeNode> list = new ArrayList<>();
                list.add(root);
                map.put(encode, list);
            }
        }
        return encode;
    }
}
