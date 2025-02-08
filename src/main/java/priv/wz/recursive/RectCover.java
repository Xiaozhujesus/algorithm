package priv.wz.recursive;

/**
 * 我们可以用 2*1 的小矩形横着或者竖着去覆盖更大的矩形。请问用 n 个 2*1 的小矩形无重叠地覆盖一个 2*n 的大矩形，总共有多少种方法？
 * 与青蛙跳和斐波那契数列一样
 */
public class RectCover {
    public int rectCover(int n) {
        if (n < 3) {
            return n;
        }
        int r1 = 1, r2 = 2;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = r1 + r2;
            r1 = r2;
            r2 = result;
        }
        return result;
    }
}
