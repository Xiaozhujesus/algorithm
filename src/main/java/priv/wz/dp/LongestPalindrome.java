package priv.wz.dp;

/**
 * 最长回文串，ababd，最长回文串为aba或bab
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if ((s == null) || s.equals("")) {
            return "";
        }
        int maxLen = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandCenter(s, i, i);
            int len2 = expandCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                end = i + len / 2;
            }
        }
        return s.substring(end - maxLen + 1, end + 1);
    }

    private int expandCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
