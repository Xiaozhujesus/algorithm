package priv.wz.divide.conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * n 位格雷码序列 是一个由 2n 个整数组成的序列，其中：
 * 每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
 * 第一个整数是 0
 * 一个整数在序列中出现 不超过一次
 * 每对 相邻 整数的二进制表示 恰好一位不同 ，且
 * 第一个 和 最后一个 整数的二进制表示 恰好一位不同
 * 给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
 * 输入：n = 2
 * 输出：[0,1,3,2]
 * 解释：
 * [0,1,3,2] 的二进制表示是 [00,01,11,10] 。
 * - 00 和 01 有一位不同
 * - 01 和 11 有一位不同
 * - 11 和 10 有一位不同
 * - 10 和 00 有一位不同
 * [0,2,3,1] 也是一个有效的格雷码序列，其二进制表示是 [00,10,11,01] 。
 * - 00 和 10 有一位不同
 * - 10 和 11 有一位不同
 * - 11 和 01 有一位不同
 * - 01 和 00 有一位不同
 */
public class GrayCode {
    public List<Integer> grayCode(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        Integer[] ans = new Integer[1 << n];
        ans[0] = 0;
        ans[1] = 1;
        int f = 1;
        while (f < n) {
            int prefix = 1 << f, i = prefix - 1, j = prefix;
            while (i >= 0) {
                ans[j] = ans[i];
                ans[j] += prefix;
                i--;
                j++;
            }
            f++;
        }
        return Arrays.asList(ans);
    }
}
