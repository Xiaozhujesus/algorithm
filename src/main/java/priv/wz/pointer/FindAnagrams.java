package priv.wz.pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个字符串s和p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。字符串只包含小写字母
 */
public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return ans;
        }
        // 滑动窗口
        int[] bound = new int[26];
        for (char c : p.toCharArray()) {
            bound[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (bound[i] == 0) {
                bound[i] = -1;
            }
        }
        int l = 0, r = 0, len = 0;
        while (r != s.length()) {
            int rc = s.charAt(r) - 'a';
            // 不存在的直接跳过
            if (bound[rc] == -1) {
                while (l != r) {
                    bound[s.charAt(l) - 'a']++;
                    l++;
                }
                l = r = r + 1;
                len = 0;
            } else {
                len++;
                // 超过上限
                if (bound[rc] == 0) {
                    int lc = s.charAt(l) - 'a';
                    while (lc != rc) {
                        bound[lc]++;
                        l++;
                        len--;
                        lc = s.charAt(l) - 'a';
                    }
                    l++;
                    len--;
                    if (len == p.length()) {
                        ans.add(l);
                    }
                } else {
                    bound[rc]--;
                    if (len == p.length()) {
                        ans.add(l);
                    }
                }
                r++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new FindAnagrams().findAnagrams("abab", "ab"));
    }
}
