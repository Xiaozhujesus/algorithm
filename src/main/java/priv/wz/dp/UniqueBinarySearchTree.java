package priv.wz.dp;

import priv.wz.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个整数 n，求以 1 … n 为节点组成的二叉搜索树有多少种？
 *
 * Example:
 *
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class UniqueBinarySearchTree {
    // 不同树的数量
    public int numTrees(int n) {
        if (n < 2) {
            return 1;
        }
        // 表示长度为 n 的连续数组构成的二叉搜索树个数，比如 dp[3] 可以表示 3~5 组成的二叉搜索树个数，也可以表示 1~3
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += (dp[j] * dp[i-j-1]);
            }
        }
        return dp[n];
    }

    //返回所有具体的不同的树
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        return generate(1, n);
    }

    private List<TreeNode> generate(int from, int to) {
        List<TreeNode> ans = new ArrayList<>();
        if (from > to) {
            ans.add(null);
            return ans;
        }
        if (from == to) {
            ans.add(new TreeNode(from));
            return ans;
        }
        for (int i = from; i <= to; i++) {
            List<TreeNode> lefts = generate(from, i - 1);
            List<TreeNode> rights = generate(i + 1, to);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    ans.add(root);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<TreeNode> treeNodes = new UniqueBinarySearchTree().generateTrees(3);
    }
}


