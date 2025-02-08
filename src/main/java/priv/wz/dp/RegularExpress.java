package priv.wz.dp;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配
 * <p>
 * '.' 可以匹配任何单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *
 */
public class RegularExpress {
    public boolean isMatch(String s, String p) {
        if (s == null || s.length() == 0) {
            if (p == null || p.length() == 0) {
                return true;
            } else {
                return false;
            }
        }
        if (p == null || p.length() == 0) {
            return false;
        }
        int m = s.length();
        int n = p.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 1;
        boolean preLetter = false;
        for (int i = 1; i <= n; i++) {
            if (preLetter && p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
                preLetter = false;
            } else {
                dp[0][i] = 0;
                preLetter = true;
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if (p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (p.charAt(j - 1) == '*') {
                        if (p.charAt(j - 2) != s.charAt(i - 1)) {
                            if (p.charAt(j - 2) == '.') {
                                dp[i][j] = 0;
                                for (int k = 0; k <= i; k++) {
                                    if (dp[k][j - 2] == 1) {
                                        dp[i][j] = 1;
                                        break;
                                    }
                                }
                            } else {
                                dp[i][j] = dp[i][j - 2];
                            }
                        } else {
                            dp[i][j] = (dp[i][j - 2] + dp[i - 1][j]) > 0 ? 1 : 0;
                        }
                    }
                }
            }
        }
        return dp[m][n] == 1;
    }

    public static void main(String[] args) {
        System.out.println(new RegularExpress().isMatch("ab", ".*"));
    }
}
