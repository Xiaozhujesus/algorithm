package priv.wz.dp;

/**
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度，与最长公共子串一样
 * <p>
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 */
public class LongestCommonSubArr {
    public int[] solution(int[] x, int[] y) {
        if (x == null || y == null || x.length == 0 || y.length == 0) {
            return null;
        }
        int xlen = x.length;
        int ylen = y.length;
        int[][] dp = new int[xlen + 1][ylen + 1];
        int maxLen = 0;
        int xEndPos = 0;
        for (int i = 1; i <= xlen; i++) {
            for (int j = 1; j <= ylen; j++) {
                if (x[i - 1] == y[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    xEndPos = i;
                }
            }
        }
        int[] ret = new int[maxLen];
        System.arraycopy(x, xEndPos - maxLen + 1, ret, 0, maxLen);
        return ret;
    }

    /**
     * 滑动窗口，num1 不动，num2 滑动
     */
    public int findLength(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return 0;
        }
        int ans = 0;
        // 滑动次数
        for (int i = 1, end = nums1.length + nums2.length; i < end; i++) {
            // 滑动窗口在两个数组的起始位置
            int x, y;
            if (i > nums2.length) {
                y = 0;
                x = i - nums2.length;
            } else {
                x = 0;
                y = nums2.length - i;
            }
            int cur = 0;
            while (x < nums1.length && y < nums2.length) {
                if (nums1[x] == nums2[y]) {
                    cur++;
                    if (cur > ans) {
                        ans = cur;
                    }
                } else {
                    cur = 0;
                }
                x++;
                y++;
            }
        }
        return ans;
    }
}
