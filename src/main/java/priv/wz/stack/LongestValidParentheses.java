package priv.wz.stack;

import java.util.Stack;

/**
 * 给你一个只包含 ‘(’ 和 ‘)’ 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * Example 1:
 * <p>
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 * <p>
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 * <p>
 * Input: s = ""
 * Output: 0
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null) {
            return 0;
        }
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.size() == 0) {
                    stack.push(i);
                } else {
                    Integer peek = stack.peek();
                    if (i - peek > ans) {
                        ans = i - peek;
                    }
                }
            }
        }
        return ans;
    }

    // 上面的优化
    public int longestValidParentheses2(String s) {
        if (s == null) {
            return 0;
        }
        int left = 0, right = 0, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
                if (right == left) {
                    if (left * 2 > ans) {
                        ans = left * 2;
                    }
                } else if (right > left) {
                    left = right = 0;
                }
            }
        }

        // 反过来处理一下，用于处理 (()这种情况
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                right++;
            } else {
                left++;
                if (left == right) {
                    if (left * 2 > ans) {
                        ans = left * 2;
                    }
                } else if (left > right) {
                    left = right = 0;
                }
            }
        }
        return ans;
    }

    public int longestValidParentheses3(String s) {
        int ans = 0;
        int n = 0, left = 0;
        for (int i = 0, end = s.length(); i < end; i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                if (left == 0) {
                    n = 0;
                } else {
                    left--;
                    if (++n > ans) {
                        ans = n;
                    }
                }
            }
        }
        return ans * 2;
    }
}
