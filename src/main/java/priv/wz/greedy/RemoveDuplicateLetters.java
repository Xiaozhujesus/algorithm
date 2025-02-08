package priv.wz.greedy;

import java.util.Stack;

/**
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。s 由小写英文字母组成
 * 示例 1：
 * <p>
 * 输入：s = "bcabc"
 * 输出："abc"
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        if (s == null) {
            return s;
        }
        char[] chars = s.toCharArray();
        int[] count = new int[26];
        boolean[] exist = new boolean[26];
        for (int i = 0; i < chars.length; i++) {
            count[chars[i] - 'a']++;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (!exist[chars[i] - 'a']) {
                while (!stack.isEmpty() && chars[i] < stack.peek() && count[stack.peek() - 'a'] > 0) {
                    exist[stack.pop() - 'a'] = false;
                }
                stack.push(chars[i]);
                exist[chars[i] - 'a'] = true;
            }
            count[chars[i] - 'a']--;
        }
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }
        return ans.reverse().toString();
    }


    public static void main(String[] args) {
        String bcabc = new RemoveDuplicateLetters().removeDuplicateLetters("abacb");
        System.out.println(bcabc);
    }
}
