package priv.wz.dp;

import java.util.Arrays;

/**
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 * <p>
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 * <p>
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 * <p>
 * 示例 1：
 * 输入：n = 12, primes = [2,7,13,19]
 * 输出：32
 * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 * <p>
 * 示例 2：
 * 输入：n = 1, primes = [2,3,5]
 * 输出：1
 * 解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
 * <p>
 * 可以参考 UglyNumber.java
 */
public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }
        int[] pos = new int[primes.length];
        int[] ans = new int[n];
        ans[0] = 1;
        int[] tmp = new int[primes.length];
        Arrays.sort(primes);
        for (int j = 0; j < primes.length; j++) {
            tmp[j] = primes[j] * ans[pos[j]];
        }
        int minIndex = 0;
        for (int i = 1; ; ) {
            tmp[minIndex] = primes[minIndex] * ans[pos[minIndex]];
            minIndex = min(tmp);
            if (tmp[minIndex] != ans[i - 1]) {
                ans[i++] = tmp[minIndex];
                if (i == n) {
                    break;
                }
            }
            pos[minIndex]++;
        }
        return ans[n - 1];
    }

    private int min(int[] tmp) {
        int min = 0;
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] < tmp[min]) {
                min = i;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] primes = {2, 7, 13, 19};
        int i = new SuperUglyNumber().nthSuperUglyNumber(12, primes);
    }
}
