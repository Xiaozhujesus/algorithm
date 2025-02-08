package priv.wz.recursive;

/**
 * 求斐波那契数列的第 n 项，n <= 39
 */
public class Fibonacci {

    // 迭代
    public int fibonacci(int n) {
        if (n <= 1)
            return n;
        int pre2 = 0, pre1 = 1;
        int cur = 0;
        for (int i = 2; i <= n; i++) {
            cur = pre2 + pre1;
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }

    // 递归，有重复子问题，因此可以 DP，其实上面的迭代就是优化了的 DP，类似将 m*n 的表优化为 1*n 的表
    public int fibonacci1(int n) {
        if (n < 2) {
            return n;
        }
        return fibonacci1(n - 2) + fibonacci1(n - 1);
    }
}
