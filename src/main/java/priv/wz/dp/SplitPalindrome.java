package priv.wz.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * <p>
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 */
public class SplitPalindrome {
    private List<List<String>> empty = new ArrayList<>();
    private List<List<String>>[] dp;

    public List<List<String>> partition(String s) {

        if (s == null) {
            return Collections.EMPTY_LIST;
        }
        if (s.isEmpty()) {
            List<String> one = new ArrayList<>();
            one.add("");
            empty.add(one);
            return empty;
        }
        dp = new List[s.length()];
        empty.add(new ArrayList<>());
        return util(s, s.length() - 1);
    }

    /**
     * 字符串s的字串s[0~n]闭区间的所有分割方案
     */
    private List<List<String>> util(String s, int n) {
        if (n < 0) {
            return empty;
        }
        if (dp[n] != null) {
            return dp[n];
        }
        List<List<String>> ret = new ArrayList<>();
        int cur = 0;
        while (cur <= n) {
            if (palindrome(s, cur, n)) {
                String tmp = s.substring(cur, n + 1);
                List<List<String>> sub = util(s, cur - 1);
                sub.forEach(item -> {
                    ArrayList<String> newItem = new ArrayList<>(item);
                    newItem.add(tmp);
                    ret.add(newItem);
                });
            }
            cur++;
        }
        dp[n] = ret;
        return ret;
    }

    /**
     * s[start,end]闭区间是否回文
     */
    private boolean palindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}