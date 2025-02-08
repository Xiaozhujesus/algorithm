package priv.wz.backtrack;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 * <p>
 * 示例 1：
 * 输入：s = "()())()"
 * 输出：["(())()","()()()"]
 * <p>
 * 示例 2：
 * 输入：s = "(a)())()"
 * 输出：["(a())()","(a)()()"]
 * <p>
 * 示例 3：
 * 输入：s = ")("
 * 输出：[""]
 */
public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return Collections.emptyList();
        }
        List<Sub> parse = parse(s);
        List<List<String>> ret = new ArrayList<>();

        for (Sub sub : parse) {
            if (sub.count != 0) {
                List<String> ans = new ArrayList<>();
                if (sub.left) {
                    removeLeft(sub.sub, sub.sub.length() - 1, sub.count, 0, "", ans);
                } else {
                    removeRight(sub.sub, 0, sub.count, 0, "", ans);
                }
                ret.add(ans);
            } else {
                ret.add(Arrays.asList(sub.sub));
            }

        }
        // ret 的组合

        Iterator<List<String>> it = ret.iterator();
        List<String> cur = it.next();
        while (it.hasNext()) {
            List<String> next = it.next();
            cur = cur.stream().flatMap(item -> next.stream().map(item2 -> item + item2)).collect(Collectors.toList());
        }
        return cur;
    }

    /**
     * 将字符串 s 中非法子串分析出来，最后一个有可能是合法的，根据 count 是否为 0 判断
     * 比如()())()得到["()())","()"]
     * 比如())(()得到["())","(()"]
     * 将问题分解为子问题
     */
    private List<Sub> parse(String s) {
        List<Sub> ans = new ArrayList<>();
        int begin = 0, end = 0, left = 0, right = 0;
        while (end != s.length()) {
            char c = s.charAt(end);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    // 直到 (
                    while (c != '(') {
                        if (c == ')') {
                            right++;
                        }
                        end++;
                        c = s.charAt(end);
                    }
                    end--;
                }
            }
            end++;
            if (right > 0) {
                ans.add(new Sub(s.substring(begin, end), false, right));
                begin = end;
                left = right = 0;
            }
        }
        if (left >= 0) {
            ans.add(new Sub(s.substring(begin, end), true, left));
        }
        return ans;
    }

    /**
     * (a()(()
     * 回溯搜素，似乎从右侧开始比较简单，遇到字母和右括号直接加入 cur
     * 当遇到左括号数大于右括号数的非法结果直接跳过，相当于剪枝了
     * 去重策略与 CombinationSum 题目中使用的一样
     * @param s
     * @param begin s[0..begin]是要处理的子串，从右往左处理
     * @param remove 剩余几个左括号没删
     * @param right 当前结果中左右括号抵消后剩余的右括号数
     * @param cur 当前结果
     * @param ans
     */
    private void removeLeft(String s, int begin, int remove, int right, String cur, List<String> ans) {
        if (remove == 0) {
            ans.add(s.substring(0, begin + 1) + cur);
            return;
        }
        char c = s.charAt(begin);
        if (c == '(') {
            if (right > 0) {
                // 与下面标记的函数遍历有重复，这里只要(连续的部分，去重
                int tmpR = right, tmpB = begin;
                String tmp = "";
                while (tmpB >= 0 && s.charAt(tmpB) == '(') {
                    tmpB--;
                    tmpR--;
                    tmp = tmp + "(";
                }
                if (tmpR >= 0) {
                    removeLeft(s, tmpB, remove, tmpR, tmp + cur, ans);
                }
            }
            // 与上面重复遍历
            removeLeft(s, begin - 1, remove - 1, right, cur, ans);
        } else {
            while (c != '(') {
                if (c == ')') {
                    right++;
                    cur = ")" + cur;
                } else {
                    cur = c + cur;
                }
                begin--;
                c = s.charAt(begin);
            }
            removeLeft(s, begin, remove, right, cur, ans);
        }
    }

    private void removeRight(String s, int begin, int remove, int left, String cur, List<String> ans) {
        if (remove == 0) {
            ans.add(cur + s.substring(begin));
            return;
        }
        char c = s.charAt(begin);
        if (c == ')') {
            if (left > 0) {
                // 与下面标记的函数遍历有重复，这里只要)连续的部分，去重
                int tmpL = left, tmpB = begin;
                String tmp = "";
                while (tmpB < s.length() && s.charAt(tmpB) == ')') {
                    tmpB++;
                    tmpL--;
                    tmp = tmp + ")";
                }
                if (tmpL >= 0) {
                    removeRight(s, tmpB, remove, tmpL, cur + tmp, ans);
                }
            }
            // 与上面重复遍历
            removeRight(s, begin + 1, remove - 1, left, cur, ans);
        } else {
            while (c != ')') {
                if (c == '(') {
                    left++;
                    cur = cur + "(";
                } else {
                    cur = cur + c;
                }
                begin++;
                c = s.charAt(begin);
            }
            removeRight(s, begin, remove, left, cur, ans);
        }
    }

    /**
     * 表示 sub 多了几个括号，多的是左还是右括号由 left 指定，count 表示多出的数量
     */
    class Sub {
        String sub;
        boolean left;
        int count;

        public Sub(String sub, boolean left, int count) {
            this.sub = sub;
            this.left = left;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        List<String> strings = new RemoveInvalidParentheses().removeInvalidParentheses("()())()");
        strings.forEach(item-> System.out.println(item));
    }
}
