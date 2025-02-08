package priv.wz.dp;

/**
 * 最长公共子序列问题的递归解法会多次解决相同的子问题，子序列可以不连续，与最长公共子串区分
 * <p>
 * 输入: str1 = "abcde", str2 = "ace"
 * 输出: 3
 */
public class LongestCommonSubSeq {

    public int lcs(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 1; i <= x.length(); i++) {
            for (int j = 1; j <= y.length(); j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[x.length()][y.length()];
    }

    // 返回最长公共子序列
    public String LCS(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 1; i < x.length() + 1; i++) {
            for (int j = 1; j < y.length() + 1; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    // 斜着
                    dp[i][j] = 0;
                } else {
                    if (dp[i - 1][j] >= dp[i][j - 1]) {
                        // 竖着
                        dp[i][j] = -1;
                    } else {
                        // 横着
                        dp[i][j] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = x.length();
        int j = y.length();
        while (i > 0 && j > 0) {
            if (dp[i][j] == 0) {
                sb.append(x.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i][j] > 0) {
                j--;
            } else {
                i--;
            }
        }
        return sb.reverse().toString();
    }
}
