package priv.wz.recursive;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母
 *
 * 是个递归问题，也是树的深度遍历
 */
public class LetterCombinations {
    private static Map<Character, char[]> map = new HashMap<>();

    static {
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
    }

    public List<String> letterCombination(String s) {
        List<String> ret = new LinkedList<>();
        if (s == null || s.length() == 0) {
            return ret;
        }
        List<String> tmp = letterCombination(s.substring(1));
        if (tmp.size() == 0) {
            for (char c : map.get(s.charAt(0))) {
                ret.add("" + c);
            }
        } else {
            for (char c : map.get(s.charAt(0))) {
                for (String ss : tmp) {
                    ret.add(c + ss);
                }
            }
        }
        return ret;
    }
}
