package priv.wz.greedy;

/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 */
public class Jump2 {
    int jump(int[] nums) {
        int ans = 0;
        int start = 0;
        int end = 0;
        while (end < nums.length - 1) {
            int maxPos = 0;
            for (int i = start; i <= end; i++) {
                // 能跳到最远的距离
                maxPos = Math.max(maxPos, i + nums[i]);
            }
            start = end + 1;      // 下一次起跳点范围开始的格子
            end = maxPos; // 下一次起跳点范围结束的格子
            ans++;            // 跳跃次数
        }
        return ans;
    }

    //优化
    int jump2(int[] nums) {
        int ans = 0;
        int end = 1;
        int maxPos = 0;
        for (int i = 0; i < nums.length; i++) {
            // 能跳到最远的距离
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == end) {
                ans++;
                end = maxPos;
            }
        }
        return ans;
    }
}
