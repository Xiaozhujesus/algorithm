package priv.wz.bit;

/**
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 */
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int i = x ^ y;
        int ans = 0;
        while (i != 0) {
            ans++;
            i &= i - 1;
        }
        return ans;
    }
}
