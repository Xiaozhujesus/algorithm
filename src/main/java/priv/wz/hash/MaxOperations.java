package priv.wz.hash;

import java.util.*;

/**
 * 给你一个整数数组 nums 和一个整数 k 。
 * 每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
 * 返回你可以对数组执行的最大操作数。
 */
public class MaxOperations {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        int ans = 0;
        for (int i : nums) {
            Integer cur = count.getOrDefault(k - i, 0);
            if (cur != 0) {
                ans++;
                count.put(k - i, cur - 1);
            } else {
                count.put(i, count.getOrDefault(i, 0) + 1);
            }
        }
        return ans;
    }
}
