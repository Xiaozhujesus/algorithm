package priv.wz.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
 * 示例1:
 * 输入: pattern = "abba", s = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 * 输入:pattern = "abba", s = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 * 输入: pattern = "aaaa", s = "dog cat cat dog"
 * 输出: false
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        if (pattern == null) {
            if (s == null) {
                return true;
            } else {
                return false;
            }
        }
        if (s == null) {
            return false;
        }
        Map<Character, Integer> pm = new HashMap<>();
        Map<String, Integer> sm = new HashMap<>();
        String[] sa = s.split(" ");
        if (pattern.length() != sa.length) {
            return false;
        }
        System.out.println(sa[128]);
        System.out.println(pattern.charAt(128));
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (!pm.containsKey(c)) {
                pm.put(c, i);
            }
            if (!sm.containsKey(sa[i])) {
                sm.put(sa[i], i);
            }
            if (!pm.get(c).equals(sm.get(sa[i]))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean b = new WordPattern().wordPattern("ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdd",
                "s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s t t");

    }
}
