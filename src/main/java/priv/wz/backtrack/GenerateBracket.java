package priv.wz.backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合
 * <p>
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class GenerateBracket {
    //分别表示左右括号剩余多少个
    private int xleft;
    private int yleft;
    private List<String> list;
    private char[] cur;

    public List<String> backtrace(int n) {
        cur = new char[n * 2];
        xleft = n;
        yleft = n;
        list = new LinkedList<>();
        if (n == 0) {
            return list;
        }
        walk(0);
        return list;
    }

    private void walk(int index) {
        if (xleft > yleft) {
            return;
        }
        if (index == cur.length) {
            list.add(new String(cur));
            return;
        }
        if (xleft > 0) {
            cur[index] = '(';
            xleft--;
            walk(index + 1);
            //backtrace
            xleft++;
        }
        if (yleft > 0) {
            cur[index] = ')';
            yleft--;
            walk(index + 1);
            yleft++;
        }
    }


    //动态规划
    public List<String> f(int n) {
        if (n == 0) {
            return Collections.EMPTY_LIST;
        }
        List<List<String>> dp = new LinkedList<>();
        List<String> dp0 = new LinkedList<>();
        dp0.add("");
        dp.add(dp0);
        for (int i = 1; i <= n; i++) {
            List<String> tmp = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                List<String> str1 = dp.get(j);
                //除去要加的括号，所以减去1
                List<String> str2 = dp.get(i - j - 1);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        tmp.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dp.add(tmp);
        }
        return dp.get(n);
    }
}
