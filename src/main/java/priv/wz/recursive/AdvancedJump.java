package priv.wz.recursive;

import java.util.Arrays;

/**
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级... 它也可以跳上 n 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法
 */
public class AdvancedJump {
    public int JumpFloor(int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)
                dp[i] += dp[j];
        return dp[n];
    }


    /**
     * backtrace(n-1) = 1 + backtrace(n-2) + backtrace(n-3) + ... + backtrace(1)
     * backtrace(n) = 1 + backtrace(n-1) + backtrace(n-2) + ... + backtrace(1)
     * 所以，backtrace(n) = 2*backtrace(n-1)
     */
    public int JumpFloorII(int n) {
        return (int) Math.pow(2, n - 1);
    }
}
