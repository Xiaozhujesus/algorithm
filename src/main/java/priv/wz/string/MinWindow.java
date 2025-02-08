package priv.wz.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 * 示例：
 *
 * 输入：S = "ADOBECODEBANC", T = "ABC"
 * 输出："BANC"
 *  
 * 输入：S = "AA", T = "AA"
 * 输出："AA"
 *
 * 提示：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 */
public class MinWindow {
    public String f(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        //初始条件的设置，注意
        int minLen = s.length();
        int endPos = -1;
        int i = 0, j = 0;
        Map<Character, Integer> now = new HashMap<>();
        Map<Character, Integer> orig = new HashMap<>();
        for (int p = 0; p < t.length(); p++) {
            now.put(t.charAt(p), 0);
            orig.put(t.charAt(p), orig.getOrDefault(t.charAt(p), 0)+1);
        }
        while (j < s.length()) {
            char cur = s.charAt(j);
            if (now.containsKey(cur)) {
                now.put(cur, now.get(cur)+1);
                //注意是小于等于，因为s = "a", t = "a" 的情况
                while (i <= j) {
                    Integer count = now.get(s.charAt(i));
                    if (count == null) {
                        i++;
                    } else if (count > orig.get(s.charAt(i))) {
                        now.put(s.charAt(i), count-1);
                        i++;
                    } else {
                        if (full(orig,now)) {
                            int tmp = j-i+1;
                            //注意是小于等于，因为s = "a", t = "a" 的情况下也要设置endPos
                            if (tmp <= minLen) {
                                minLen = tmp;
                                endPos = j;
                            }
                        }
                        break;
                    }
                }
            }
            j++;
        }
        if (endPos < 0) {
            return "";
        }
        return s.substring(endPos-minLen+1, endPos+1);
    }

    private boolean full(Map<Character, Integer> orig, Map<Character, Integer> now) {
        for (Map.Entry<Character, Integer> entry : orig.entrySet()) {
            Character c = entry.getKey();
            if (orig.get(c) > now.get(c)) {
                return false;
            }
        }
        return true;
    }
}
