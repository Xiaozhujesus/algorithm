package priv.wz.tree;

/**
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 */
public class NumTrees {
    public int numTrees(int n) {
        if (n == 1) {
            return 1;
        }
        int[] ans = new int[n + 1];
        ans[0] = 1;
        ans[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int right = 0; right < i / 2; right++) {
                ans[i] += ans[i - 1 - right] * ans[right];
            }
            ans[i] *= 2;
            if (i % 2 != 0) {
                ans[i] += Math.pow(ans[(i - 1) / 2], 2);
            }
        }
        return ans[n];
    }
}
