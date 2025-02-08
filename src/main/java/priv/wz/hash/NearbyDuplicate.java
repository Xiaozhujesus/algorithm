package priv.wz.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值不超过 k
 */
public class NearbyDuplicate {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length == 0 || nums.length == 1) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        int len = Math.min(nums.length, k);
        for (int i = 0; i < len; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
            } else {
                return true;
            }
        }
        int firstindex = 0;
        int secondindex = len;
        while (secondindex != nums.length) {
            if (set.contains(nums[secondindex])) {
                return true;
            } else {
                set.add(nums[secondindex]);
                set.remove(nums[firstindex]);
                firstindex++;
                secondindex++;
            }
        }
        return false;
    }
}
