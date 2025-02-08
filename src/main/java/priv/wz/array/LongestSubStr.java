package priv.wz.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
 * 输入:
 * s = "ababbc", k = 2
 * <p>
 * 输出:
 * 5
 * <p>
 * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次
 */
public class LongestSubStr {
    private int max;

    public int f(String str, int k) {
        if (str == null || str.length() == 0 || k == 0) {
            return 0;
        }
        util(str, k);
        return max;
    }

    private void util(String str, int k) {
        if (str.length() < k) {
            return;
        }
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            count.put(cur, count.getOrDefault(cur, 0) + 1);
        }
        Set<Character> lessK = new HashSet<>();
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            if (entry.getValue() < k) {
                lessK.add(entry.getKey());
            }
        }
        if (lessK.isEmpty()) {
            if (str.length() > max) {
                max = str.length();
            }
        } else {
            int begin = -1;
            for (int i = 0; i < str.length(); i++) {
                if (lessK.contains(str.charAt(i))) {
                    if (begin != -1) {
                        util(str.substring(begin, i), k);
                        begin = -1;
                    }
                } else {
                    if (begin == -1) {
                        begin = i;
                    }
                }
            }
            if (begin != -1) {
                util(str.substring(begin), k);
            }
        }
    }
}
