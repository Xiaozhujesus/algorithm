package priv.wz.bit;

/**
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。
 * 输入：n = 11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 */
public class FindNthDigit {
    public int findNthDigit(int n) {
        int i = 1, sum = 0, next = sum + 9 * ((int) Math.pow(10, i - 1)) * i;
        while (next < n) {
            sum = next;
            i++;
            next = sum + 9 * ((int) Math.pow(10, i - 1)) * i;
        }
        int count = (n - sum) / i;
        int j = (n - sum) % i;
        int cur = ((int) Math.pow(10, i - 1)) + count;
        return (cur / ((int) Math.pow(10, i - j))) % 10;
    }
}
