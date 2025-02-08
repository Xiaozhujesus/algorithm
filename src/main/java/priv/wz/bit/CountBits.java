package priv.wz.bit;

/**
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 */
public class CountBits {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = util(i);
        }
        return ans;
    }

    private int util(int i) {
        int ans = 0;
        while (i != 0) {
            ans++;
            i &= (i - 1);
        }
        return ans;
    }

    /**
     * 上述算法的优化,i&(i-1)将i的二进制表示的最后一个1改为0，同时该数一定小于i
     */
    public int[] countBits2(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }
}
