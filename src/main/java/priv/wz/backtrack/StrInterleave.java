package priv.wz.backtrack;

import java.util.Stack;

/**
 * 给定三个字符串 s1、s2、s3，请你帮忙验证s3是否是由s1和s2 交错组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干非空子字符串：
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 *
 * 这题也有 DP 解法
 */
public class StrInterleave {

    private String s1;
    private String s2;
    private String s3;
    public Stack<Character> stack;

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.stack = new Stack<>();
        return walk(0, 0, 0);
    }

    // 有点类似二叉树深度优先搜索
    private boolean walk(int i, int j, int cur) {
        if (cur == s3.length()) {
            return true;
        }
        if (i < s1.length() && s1.charAt(i) == s3.charAt(cur)) {
            stack.push('1');
            if (walk(i + 1, j, cur + 1)) {
                return true;
            }
            stack.pop();
        }
        if (j < s2.length() && s2.charAt(j) == s3.charAt(cur)) {
            stack.push('2');
            if (walk(i, j + 1, cur + 1)) {
                return true;
            }
            stack.pop();
        }
        return false;
    }
}
