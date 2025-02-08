package org.practice.bit;

/**
 * 实现函数 int sqrt(int x).
 * 计算并返回x的平方根
 */
public class Sqrt {
    public int sqrt(int x) {
        if (x <= 0) {
            return 0;
        }
        long low = 1;
        long high = x;
        while (low < high) {
            long middle = (low + high) / 2;
            if (middle * middle <= x && (middle + 1) * (middle + 1) > x) {
                return (int)middle;
            }
            if (middle * middle > x) {
                high = middle;
            } else {
                low = middle;
            }
        }
        return (int)low;
    }
}
