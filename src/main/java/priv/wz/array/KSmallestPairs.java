package priv.wz.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个以非递减顺序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 */
public class KSmallestPairs {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>(k);
        if (k < 1) {
            return ans;
        }

        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new KSmallestPairs().kSmallestPairs(new int[]{1, 2, 4, 5, 6}, new int[]{3, 5, 7, 9}, 3);
    }
}
