package priv.wz.pointer;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 */
public class ReverseWords {
    public String reverseWords(String s) {
        if (s == null) {
            return s;
        }
        int i = 0, j, next;
        StringBuilder ans = new StringBuilder();
        while (i < s.length()) {
            while (i < s.length() && s.charAt(i) == ' ') {
                i++;
                ans.append(' ');
            }
            j = i + 1;
            while (j < s.length() && s.charAt(j) != ' ') {
                j++;
            }
            next = j;
            while (j-- != i) {
                ans.append(s.charAt(j));
            }
            i = next;
        }
        return ans.toString();
    }
}
