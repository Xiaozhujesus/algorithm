package priv.wz.stack;

import java.util.Stack;

/**
 * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
 * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
 */
public class BracketMatch {
    public boolean f(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        if (str.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0, n = str.length(); i < n; i++) {
            char c = str.charAt(i);
            if (left(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (match(stack.peek(), c)) {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean g(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        if (str.length() % 2 == 1) {
            return false;
        }
        int a = 0, b = 0, c = 0;
        boolean over = false;
        for (int i = 0; i < str.length(); i++) {
            if (over) {
                return false;
            }
            switch (str.charAt(i)) {
                case '(':
                    a++;
                    break;
                case '{':
                    b++;
                    break;
                case '[':
                    c++;
                    break;
                case ')':
                    if (--a < 0) {
                        over = true;
                    }
                    break;
                case '}':
                    if (--b < 0) {
                        over = true;
                    }
                    break;
                case ']':
                    if (--c < 0) {
                        over = true;
                    }
                    break;
            }
        }
        return true;
    }

    private boolean left(char c) {
        if (c == '(' || c == '[' || c == '{') {
            return true;
        }
        return false;
    }

    private boolean match(char a, char b) {
        return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}');
    }
}
