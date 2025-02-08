package priv.wz.dp;

/**
 * 给定三个字符串 s1、s2、s3，请你帮忙验证s3是否是由s1和s2 交错组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干非空子字符串：
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 * <p>
 * 这题也有回溯解法
 */
public class StrInterleave {
    private boolean dp[][];

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int m = s1.length() + 1;
        int n = s2.length() + 1;
        this.dp = new boolean[m][];
        for (int i = 0; i < m; i++) {
            dp[i] = new boolean[n];
        }
        for (int i = 1; i < n; i++) {
            if (s2.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
                dp[i][0] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    if (dp[i - 1][j]) {
                        dp[i][j] = true;
                        continue;
                    }
                }
                if (s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 对上面的函数进行空间优化
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int m = s1.length();
        int n = s2.length();
        boolean[] s1match = new boolean[m];
        boolean[] dp = new boolean[n];
        for (int i = 0; i < m; i++) {
            if (s1.charAt(i) == s3.charAt(i)) {
                s1match[i] = true;
            } else {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (s2.charAt(i) == s3.charAt(i)) {
                dp[i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s1.charAt(i) == s3.charAt(i + j + 1)) {
                    if (dp[j]) {
                        continue;
                    }
                }
            }
        }
        return dp[n - 1];
    }
}
