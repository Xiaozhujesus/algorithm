package priv.wz.recursive;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 */
public class Divide {
    public int f(int a, int b) {
        if (a == 0) {
            return 0;
        }
        if (b == 1) {
            return a;
        }
        if (b == -1) {
            if (a > Integer.MIN_VALUE) {
                return -a;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        int sign = 0;
        if (a > 0 && b < 0 || a < 0 && b > 0) {
            sign = 1;
        }
        a = a > 0 ? a : -a;
        b = b > 0 ? b : -b;
        int res = util(a, b);
        if (sign > 0) {
            return -res;
        } else {
            return res;
        }
    }

    private int util(int a, int b) {
        if (a<b) {
            return 0;
        }
        int res = 1;
        int divided = b;
        while(divided+divided < a) {
            res = res + res;
            divided = divided+divided;
        }
        return res+util(a-divided, b);
    }
}


/**
 * 举个例子：11 除以 3 。
 * 首先11比3大，结果至少是1， 然后我让3翻倍，就是6，发现11比3翻倍后还要大，那么结果就至少是2了，那我让这个6再翻倍，得12，11不比12大，
 * 吓死我了，差点让就让刚才的最小解2也翻倍得到4了。但是我知道最终结果肯定在2和4之间。也就是说2再加上某个数，这个数是多少呢？
 * 我让11减去刚才最后一次的结果6，剩下5，我们计算5是3的几倍，也就是除法，看，递归出现了。
 */