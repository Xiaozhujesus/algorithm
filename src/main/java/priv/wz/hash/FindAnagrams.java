package priv.wz.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词指由相同字母重排列形成的字符串（包括相同的字符串）
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * s 和 p 只由小写字母组成
 */
public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (p == null || p.length() == 0) {
            return ans;
        }
        int[] needed = new int[26];
        for (char c : p.toCharArray()) {
            needed[c - 'a']++;
        }
        int count = p.length();
        char[] ss = s.toCharArray();
        int i = 0, j = 0;
        while (j != s.length()) {
            if (needed[ss[j] - 'a'] > 0) {
                needed[ss[j] - 'a']--;
                count--;
                if (count == 0) {
                    ans.add(i);
                }
            } else {
                // i == j 时 ss[i] == ss[j]
                while (ss[i] != ss[j]) {
                    needed[ss[i] - 'a']++;
                    count++;
                    i++;
                }
                i++;
                if (count == 0) {
                    ans.add(i);
                }
            }
            j++;
        }
        return ans;
    }


    public List<Integer> findAnagrams2(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }

        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; ++i) {
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + pLen) - 'a'];

            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        new FindAnagrams().findAnagrams("abab", "ab");
    }
}
