package priv.wz.stack;

import java.util.Collections;
import java.util.Stack;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。
 */
public class DecodeString {
    public String decodeString(String s) {
        s = "1[" + s + "]";
        Stack<String> ss = new Stack<>();
        Stack<Integer> ii = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c <= '9' && c >= '0') {
                StringBuilder sb = new StringBuilder();
                while (c <= '9' && c >= '0') {
                    sb.append(c);
                    c = s.charAt(++i);
                }
                ii.push(Integer.valueOf(sb.toString()));
            } else if (c == ']') {
                String tmp = "";
                while (!ss.peek().equals("[")) {
                    tmp = ss.pop() + tmp;
                }
                ss.pop();
                Integer repeat = ii.pop();
                ss.push(String.join("", Collections.nCopies(repeat, tmp)));
                i++;
            } else if (c >= 'a' && c <= 'z') {
                StringBuilder sb = new StringBuilder();
                while (c >= 'a' && c <= 'z') {
                    sb.append(c);
                    c = s.charAt(++i);
                }
                ss.push(sb.toString());
            } else {
                ss.push("[");
                i++;
            }
        }
        String tmp = "";
        while (!ss.isEmpty()) {
            tmp = ss.pop() + tmp;
        }
        return tmp;
    }

    // 其实用一个栈也可以解决
    public String decodeString1(String s) {
        return "";
    }

    public String decodeString2(String s) {
        return "";
    }


    public static void main(String[] args) {
        System.out.println(new DecodeString().decodeString("2[abc]3[cd]ef"));
    }
}
