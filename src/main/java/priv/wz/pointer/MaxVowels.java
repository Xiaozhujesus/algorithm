package priv.wz.pointer;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你字符串 s 和整数 k 。
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * 英文中的 元音字母 为（a, e, i, o, u）。
 */
public class MaxVowels {
    public int maxVowels(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        int ans = 0, cur;
        int i = 0, j = 0;
        while (j < k) {
            if (set.contains(s.charAt(j))) {
                ans++;
            }
            j++;
        }
        cur = ans;
        while (j < s.length()) {
            if (set.contains(s.charAt(i))) {
                cur--;
            }
            if (set.contains(s.charAt(j))) {
                cur++;
            }
            ans = Math.max(ans, cur);
            i++;
            j++;
        }
        return ans;
    }
}
