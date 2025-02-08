package priv.wz.dp;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 */
public class MinEditDistance {
    public int minDistance(String s1, String s2) {
        if (s1 == null || s1.length() == 0) {
            if (s2 == null) {
                return 0;
            } else {
                return s2.length();
            }
        }
        if (s2 == null || s2.length() == 0) {
            return s1.length();
        }
        int m = s1.length();
        int n = s2.length();
        /**
         * dp[i][j]表示s1.subString(0,i)变为s2.subString(0,j)所经过的最少操作，那么
         * dp[i+1][j+1] = dp[i][j],当s1.charAt(i) == s2.charAt(j)
         * dp[i+1][j+1] = dp[i+1][j] + 1，在s1最后插入s2最后的字符
         *                dp[i][j+1] + 1，将s1最后一个字符删除
         *                dp[i][j] + 1，替换s1最后一个字符为s2的最后一个字符
         * 上面三种情况中的最小值
         */
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int min = dp[i - 1][j - 1];
                    min = Math.min(min, dp[i - 1][j]);
                    min = Math.min(min, dp[i][j - 1]);
                    dp[i][j] = min + 1;
                }
            }
        }
        return dp[m][n];
    }
}
