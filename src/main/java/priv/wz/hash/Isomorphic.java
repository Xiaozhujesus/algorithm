package priv.wz.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * <p>
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身.
 * <p>
 * s = "egg", t = "add" true
 * s = "foo", t = "bar" false
 */
public class Isomorphic {

    /**
     * 如下解法统一转成中间格式了
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] arrs = s.toCharArray();
        char[] arrt = t.toCharArray();
        Map<Character, Integer> maps = new HashMap<>();
        Map<Character, Integer> mapt = new HashMap<>();
        int[] compare = new int[arrs.length];

        int flag = 0;
        for (int i = 0; i < arrs.length; i++) {
            if (!maps.containsKey(arrs[i])) {
                maps.put(arrs[i], ++flag);
            }
            compare[i] = maps.get(arrs[i]);
        }
        flag = 0;
        for (int i = 0; i < arrt.length; i++) {
            if (!mapt.containsKey(arrt[i])) {
                mapt.put(arrt[i], ++flag);
            }
            if (compare[i] != mapt.get(arrt[i])) {
                return false;
            }
        }
        return true;
    }
}
