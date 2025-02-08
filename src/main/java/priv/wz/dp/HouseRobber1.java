package priv.wz.dp;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class HouseRobber1 {

    public int rob(int[] nums) {
        return util(nums, 0, nums.length - 1);
    }

    public int util(int[] nums, int from, int to) {
//        int[] dp = new int[nums.length + 1];
        int pre2 = 0, pre1 = nums[from], cur = nums[from];
        for (int i = from + 1; i <= to; i++) {
            cur = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }


    /**
     * 首先，设 0 到 n 的最大值 max[n]，最后两个房子必定有一个房子要偷，否则 max[n] = max[n-2]，
     * 但 max[n-2] + nums[n] 一定大于 max[n-2]，因此 max[n] 只包含两种情况:
     * 1、不包含 nums[n-1] 包含 nums[n]，此时 max[n] = max[n-2] + nums[n]，注意不一定包含 nums[n-2]
     * 2、包含 nums[n-1] 不包含 nums[n]，此时 max[n] = max[n-1]
     * 因此 max[n] = max(max[n-2] + nums[n], max[n-1])，至此，题目求解完毕
     *
     * 继续推理：
     * 若后者大即 max[n] = max[n-1]，那么说明 max[n] 不包含 nums[n] 但包含 nums[n-1]，由于二者相等因此 max[n-1] 也一定包含 nums[n-1]
     * 若前者大即 max[n] = max[n-2] + nums[n]，则 max[n] 一定包含 nums[n]，但 max[n-1] 不一定包含 nums[n-1]
     *
     * 设 max[n] 的最佳偷盗方式为 f1，若 max[n] 不包含 num[n]，那么一定包含 num[n-1]，且 max[n-1] 的最佳偷盗方式与 max[n] 相同，
     * 否则若 max[n-1] 存在不同于 max[n] 且大于 max[n] 的方式 f2，那么 max[n] 直接用该方式 f2 即可比目前的 f1 大，与前提矛盾
     *  max[n] = max(max[n-2] + nums[n], max[n-1])，按这种方式计算，不会漏掉任何一种情况：唯一感觉可能被漏掉的就是下面这种情况：
     *  若 max[n-1] 不包含 num[n-1]，根据上面的推论，max[n-1] 一定包含 num[n-2] 且 max[n-2] == max[n-1]
     *  这会在计算 max[n-2] + nums[n] 时被考虑到
     *  从另一个角度看，若 max[n-1] 不包含 num[n-1]，假设对应的最佳偷盗方式为 f2，那么按上述方式计算 max[n] 时，
     *  max[n] 一定不会包含 num[n-1]，否则假设 max[n] 包含 num[n-1] 的最佳偷盗方式为 f1，则 manx[n-1] 直接使用 f1 即可比 f2 大
     *  与 f1 是最佳矛盾了
     */
    public int rob2(int[] nums) {
        // 比当前位置之前两个位置能偷的最大值，比当前位置之前一个位置能偷的最大值，以及当前位置能偷的最大值
        int pre2 = 0, pre1 = nums[0], cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }
}
