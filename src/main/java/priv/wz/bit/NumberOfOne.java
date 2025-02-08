package priv.wz.bit;

/**
 * 输入一个整数，输出该数二进制表示中 1 的个数
 */
public class NumberOfOne {

    public int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }
}
