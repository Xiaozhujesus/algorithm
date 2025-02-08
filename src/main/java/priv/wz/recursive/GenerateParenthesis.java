package priv.wz.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 这题与电话号码的字母组合一样
 */
public class GenerateParenthesis {
    private List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        util("", 0, n, n);
        return ans;
    }

    /**
     * @param cur
     * @param left cur 中左右括号匹配约掉后，剩余左括号个数
     * @param l    剩余可用左括号个数
     * @param r    剩余可用右括号个数
     */
    private void util(String cur, int left, int l, int r) {
        if (left < 0) {
            return;
        }
        if (l == 0 && r == 0) {
            ans.add(cur);
            return;
        }
        if (l > 0) {
            util(cur + "(", left + 1, l - 1, r);
        }
        if (r > 0) {
            util(cur + ")", left - 1, l, r - 1);
        }
    }


    /**
     * 上面这种方法是搜索剪枝，其实等同于回溯，只不过每次都使用新的字符串，如果使用同一个字符串，就是回溯，如下
     */
    private int xleft;
    private int yleft;
    private List<String> ret;
    private char[] cur;

    public List<String> generateParenthesis2(int n) {
        cur = new char[n*2];
        xleft = n;
        yleft = n;
        ret = new ArrayList<>();
        util(0);
        return ret;
    }

    private void util(int index) {
        if (xleft + yleft == 0) {
            ret.add(new String(cur));
            return;
        }
        if (!valid()) {
            return;
        }
        if (xleft > 0) {
            cur[index] = '(';
            xleft--;
            util(index+1);
            //backtrace
            xleft++;
            cur[index] = 0;
        }
        if (yleft > 0) {
            cur[index] = ')';
            yleft--;
            util(index+1);
            yleft++;
            cur[index] = 0;
        }
    }
    private boolean valid() {
        return xleft <= yleft;
    }
}
