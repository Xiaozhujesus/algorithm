package priv.wz.dp;

/**
 * 给定两个字符串，找出两个字符串的最长公共子串，子串需要连续，注意与子序列区分
 */
public class LongestCommonSubStr {
    public String f(String x, String y) {
        if (x == null || y == null || x.length() == 0 || y.length() == 0) {
            return "";
        }
        int xlen = x.length();
        int ylen = y.length();
        int maxLen = 0;
        int xEndPos = 0;
        int[][] dp = new int[xlen][ylen];
        for (int i = 0; i < xlen; i++) {
            for (int j = 0; j < ylen; j++) {
                if (x.charAt(i) == y.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                } else {
                    dp[i][j] = 0;
                }
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    xEndPos = i;
                }
            }
        }
        return x.substring(xEndPos - maxLen + 1, xEndPos + 1);
    }
}
