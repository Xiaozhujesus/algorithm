package priv.wz.other;

/**
 *
 */
public class SingleNumber {
    /**
     * 给你一个非空整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。
     * 找出那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     */
    public int singleNumber1(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

    /**
     * 给你一个整数数组 nums ，除某个元素仅出现一次外，其余每个元素都恰出现三次。
     * 请你找出并返回那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
     */
    public int singleNumber2(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                sum += (nums[j] >>> i) & 1;
            }
            // 每一位相加一定是 3 的倍数，加上 0 或者 1（只出现一次那个数对应位置的值）
            sum = sum % 3;
            sum = sum << i;
            ans |= sum;
        }
        return ans;
    }
}
