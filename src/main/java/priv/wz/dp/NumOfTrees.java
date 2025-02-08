package org.practice.dp;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 关键点是树的形态，而与具体节点中存的数据无关，比如2个节点组成的2插搜索树就两个，不论树的节点值是1，2还是2，3
 * 所以就有重复子问题了，g(n)为由n个节点组成的二叉搜索树的个数，因为树的个数与节点值无关，这里节点值使用1到n
 * backtrace(i,n)表示以i为根，一共n个节点的二叉搜索树的个数，那么g(n) = backtrace(i,n),i从1到n的和
 * 而f(i,n) = g(i-1)+g(n-i)
 */
public class NumOfTrees {
    public int solution(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }
}
