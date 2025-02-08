package org.practice.bit;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 输入: 123
 * 输出: 321
 *
 * 输入: -123
 * 输出: -321
 *
 * 输入: 120
 * 输出: 21
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0
 */
public class ReverseNum {
    public int f(int i) {
        if (i == 0) {
            return 0;
        }
        boolean negative = false;
        if (i < 0) {
            negative = true;
            i = -i;
        }
        int ret = 0;
        while (i != 0) {
            int last = i % 10;
            i = i/10;
            if (ret != 0 && Integer.MAX_VALUE / ret < 10) {
                return 0;
            }
            ret = ret * 10 + last;
        }
        if (ret < 0) {
            return 0;
        } else {
            if (negative) {
                return -ret;
            } else {
                return ret;
            }
        }

    }
}
