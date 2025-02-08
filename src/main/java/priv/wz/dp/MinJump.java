package priv.wz.dp;

/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，
 * 你可以跳转到任意 nums[i + j] 处:
 * 1、0 <= j <= nums[i]
 * 2、i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。如果不能跳到，则返回 -1
 */
public class MinJump {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = nums.length - 2; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            if (nums[i] != 0) {
                for (int j = 1; j <= nums[i]; j++) {
                    if (i + j < nums.length && dp[i + j] < dp[i]) {
                        dp[i] = dp[i + j];
                    }
                }
                if (dp[i] != Integer.MAX_VALUE) {
                    dp[i] = dp[i] + 1;
                }
            }
        }
        if (dp[0] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new MinJump().jump(new int[]{2, 3, 1, 1, 4}));
    }
}
