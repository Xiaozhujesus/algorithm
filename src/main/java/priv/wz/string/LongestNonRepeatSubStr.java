package priv.wz.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，求它的无重复字符的最长子串，子串是连续的，而子序列可以不连续
 */
public class LongestNonRepeatSubStr {
    public String lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int maxLen = 0;
        int endPos = 0;
        int curLen = 0;
        int first = 0, second = 0;
        Set<Character> set = new HashSet<>();
        while (second != s.length()) {
            char c = s.charAt(second);
            if (set.contains(c)) {
                while (s.charAt(first) != c) {
                    set.remove(s.charAt(first));
                    first++;
                }
                first++;
                curLen = second - first + 1;
            } else {
                set.add(c);
                curLen++;
                if (curLen > maxLen) {
                    maxLen = curLen;
                    endPos = second;
                }
            }
            second++;
        }
        return s.substring(endPos - maxLen + 1, endPos + 1);
    }
}
