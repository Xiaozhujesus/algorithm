package priv.wz.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。
 * 如果存在，返回 true ；否则，返回 false 。
 */
public class ContainsNearbyDuplicate {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        int i = 0, j = 0;
        while (j <= k && j < nums.length) {
            if (set.contains(nums[j])) {
                return true;
            } else {
                set.add(nums[j]);
            }
            j++;
        }
        while (j < nums.length) {
            set.remove(nums[i]);
            i++;
            if (set.contains(nums[j])) {
                return true;
            } else {
                set.add(nums[j]);
            }
            j++;
        }
        return false;
    }
}
