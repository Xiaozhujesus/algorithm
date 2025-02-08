package priv.wz.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1
 */
public class UniqChar {

    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] str = s.toCharArray();
        for (char c : str) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, 2);
            }
        }
        for (int i = 0; i < str.length; i++) {
            if (map.get(str[i]) == 1) {
                return i;
            }
        }
        return -1;
    }
}
