package priv.wz.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class IsWordSameChar {
    public boolean f(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() == t.length()) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char cur = s.charAt(i);
                map.put(cur, map.getOrDefault(cur, 0) + 1);
            }
            for (int i = 0; i < t.length(); i++) {
                char cur = t.charAt(i);
                Integer count = map.get(cur);
                if (count == null || count == 0) {
                    return false;
                } else {
                    map.put(cur, count - 1);
                }
            }
            return true;
        }
        return false;
    }
}
