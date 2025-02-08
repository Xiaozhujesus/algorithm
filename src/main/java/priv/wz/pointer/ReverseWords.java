package priv.wz.pointer;

import java.util.Stack;

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

    /**
     * 给你一个字符串 s ，请你反转字符串中单词的顺序。
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的单词分隔开。
     * 返回单词顺序颠倒且单词 之间用单个空格连接的结果字符串。
     * 注意：输入字符串 s 中可能会存在前导空格、尾随空格或者单词间的多个空格。
     * 返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
     */
    public String reverseWords2(String s) {
        if (s == null) {
            return s;
        }
        Stack<String> stack = new Stack<>();
        int i = 0, j;
        while (i < s.length()) {
            while (i < s.length() && s.charAt(i) == ' ') {
                i++;
            }
            j = i + 1;
            while (j < s.length() && s.charAt(j) != ' ') {
                j++;
            }
            if (i < s.length()) {
                stack.push(s.substring(i, j));
                i = j;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            sb.append(' ');
        }
        return sb.substring(0, sb.length() - 1);
    }
}
