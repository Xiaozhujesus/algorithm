package priv.wz.dp;

/**
 * 泰波那契序列 Tn 定义如下：
 * <p>
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * <p>
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 */
public class Tribonacci {
    public int tribonacci(int n) {
        if (n < 3) {
            return n == 0 ? 0 : 1;
        }
        int a1 = 0, a2 = 1, a3 = 1, a4 = 0;
        for (int i = 3; i <= n; i++) {
            a4 = a1 + a2 + a3;
            a1 = a2;
            a2 = a3;
            a3 = a4;
        }
        return a4;
    }
}
