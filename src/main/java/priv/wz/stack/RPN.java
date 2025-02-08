package priv.wz.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 逆波兰表达式求值
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：该算式转化为常见的中缀算术表达式为：
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class RPN {
    private Set<String> OPS = new HashSet<>();

    public int evalRPN(String[] tokens) {
        OPS.add("+");
        OPS.add("-");
        OPS.add("*");
        OPS.add("/");
        Stack<Integer> nums = new Stack<>();
        int p = 0;
        while (p < tokens.length) {
            if (OPS.contains(tokens[p])) {
                // 这里注意顺序
                int two = nums.pop();
                int one = nums.pop();
                switch (tokens[p]) {
                    case "+":
                        nums.push(one + two);
                        break;
                    case "-":
                        nums.push(one - two);
                        break;
                    case "*":
                        nums.push(one * two);
                        break;
                    case "/":
                        nums.push(one / two);
                        break;
                }
            } else {
                nums.push(Integer.parseInt(tokens[p]));
            }
            p++;
        }
        return nums.pop();
    }
}
