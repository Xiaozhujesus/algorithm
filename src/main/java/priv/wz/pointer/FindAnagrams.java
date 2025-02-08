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
        if (s.length() == 0 || p.length() == 0 || s.length() < p.length()) {
            return ans;
        }
        int[] bound = new int[26];
        for (char c : p.toCharArray()) {
            bound[c - 'a']++;
        }
        int[] count = new int[26];
        int l = 0, r = 0, len = 0;
        while (r != s.length()) {
            int rc = s.charAt(r) - 'a';
            // 不存在的直接跳过
            if (bound[rc] == 0) {
                l = r = r + 1;
                len = 0;
                Arrays.fill(count, 0);
            } else {
                count[rc]++;
                len++;
                // 超过上限
                if (count[rc] > bound[rc]) {
                    int lc = s.charAt(l) - 'a';
                    while (lc != rc) {
                        count[lc]--;
                        l++;
                        len--;
                        lc = s.charAt(l) - 'a';
                    }
                    count[lc]--;
                    l++;
                    len--;
                    r++;
                } else if (len == p.length()) {
                    // 未超限且长度匹配，就是异位词
                    ans.add(l);
                    count[s.charAt(l) - 'a']--;
                    l++;
                    r++;
                    len--;
                } else {
                    // 未超限且长度不够，直接下一个
                    r++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new FindAnagrams().findAnagrams("abaacbabc","abc"));
    }
}
