package priv.wz.pointer;

/**
 * 在不使用额外的内存空间的条件下判断一个整数是否是回文数字
 * 提示：
 * 负整数可以是回文吗？（比如-1）
 * 如果你在考虑将数字转化为字符串的话，请注意一下不能使用额外空间的限制
 * 你可以将整数翻转。但是，如果你做过题目“反转数字”，你会知道将整数翻转可能会出现溢出的情况，你怎么处理这个问题？
 */
public class IsPalindrome {
    boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int digits = 0, y = x;
        while (y != 0) {
            ++digits;
            y /= 10;
        }
        for (int i = 0; i < digits / 2; ++i) {
            int a = (int)Math.pow(10, i), b = (int)Math.pow(10, digits - 1 - i);
            if (x / a % 10 != x / b % 10) {
                return false;
            }
        }
        return true;
    }
}
