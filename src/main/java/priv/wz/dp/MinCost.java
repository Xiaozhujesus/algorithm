package org.practice.dp;

/**
 * 编辑距离的变种
 */
public class MinCost {
    public static void main(String[] args) {
        System.out.println(
                new MinCost().findMinCost("abc", 3, "adc", 3, 5, 3, 100));
    }

    public int findMinCost(String A, int n, String B, int m, int c0, int c1, int c2) {
        // write code here
        if (A == null || A.length() == 0) {
            return c0 * B.length();
        }
        if (B == null || B.length() == 0) {
            return c1 * A.length();
        }
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i * c0;
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i * c1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(j - 1) == B.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int append = dp[i - 1][j] + c0;
                    int del = dp[i][j - 1] + c1;
                    int change = dp[i - 1][j - 1] + c2;
                    int min = append > del ? del : append;
                    if (change < min) {
                        min = change;
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[m][n];
    }
}
