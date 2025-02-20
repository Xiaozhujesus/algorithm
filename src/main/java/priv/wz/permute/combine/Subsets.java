package priv.wz.permute.combine;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        for (int i = 0; i < Math.pow(2, nums.length); i++) {
            List<Integer> cur = new ArrayList<>();
            int tmp = i;
            int j = 0;
            while (tmp != 0) {
                if ((tmp & 1) == 1) {
                    cur.add(nums[j]);
                }
                tmp = tmp >> 1;
                j++;
            }
            ans.add(cur);
        }
        return ans;
    }
}
