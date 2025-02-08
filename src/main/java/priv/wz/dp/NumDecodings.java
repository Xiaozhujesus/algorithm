package priv.wz.dp;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数
 */
public class NumDecodings {

    // 自底向上
    public int numDecodings(String str) {
        if (str.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[str.length() + 1];
        dp[0] = 1;

        for (int i = 1, end = str.length(); i <= end; i++) {
            char cur = str.charAt(i - 1);
            if (cur != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1 && str.charAt(i - 2) != '0' && 10 * (str.charAt(i - 2) - '0') + (cur - '0') <= 26)
                dp[i] += dp[i - 2];
        }
        return dp[str.length()];
    }
}
