package priv.wz.string;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。s 仅由数字和英文字母组成
 */
public class LongestPalindrome {
    private int maxLen = 0;
    private String ans = "";

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        for (int i = 0; i < s.length(); i++) {
            expand(s, i, i);
            if (i != s.length() - 1) {
                expand(s, i, i + 1);
            }
        }
        return ans;
    }

    private void expand(String s, int index1, int index2) {
        while (index1 >= 0 && index2 < s.length() && s.charAt(index1) == s.charAt(index2)) {
            index1--;
            index2++;
        }
        int curLen = index2 - index1 - 1;
        if (curLen > maxLen) {
            maxLen = curLen;
            ans = s.substring(index1 + 1, index2);
        }
    }
}
