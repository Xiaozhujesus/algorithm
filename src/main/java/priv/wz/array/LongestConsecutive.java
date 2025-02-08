package priv.wz.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]，它的长度为 4
 */
public class LongestConsecutive {
    // 内存超限
    int longestConsecutive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int min = arr[0], max = min;
        for (int i : arr) {
            min = Math.min(i, min);
            max = Math.max(i, max);
        }
        int[] exist = new int[max - min + 1];
        for (int i : arr) {
            exist[i - min] = 1;
        }
        int ans = 0;
        int index = 0;
        while (index < exist.length) {
            while (index < exist.length && exist[index] == 0) {
                index++;
            }
            int sub = 0;
            while (index < exist.length && exist[index] == 1) {
                sub++;
                index++;
            }
            ans = Math.max(ans, sub);
        }
        return ans;
    }

    /**
     * 从每个连续子序列的第一个元素往后查，第一个元素 cur 的确定：
     * 看 cur-1 是否存在，不存在则是，否则不是
     */
    public int longestConsecutive2(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
