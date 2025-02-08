package priv.wz.hash;

import java.util.*;

/**
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，请你返回一个长度为 2 的列表 answer ，其中：
 * answer[0] 是 nums1 中所有 不 存在于 nums2 中的 不同 整数组成的列表。
 * answer[1] 是 nums2 中所有 不 存在于 nums1 中的 不同 整数组成的列表。
 * 注意：列表中的整数可以按 任意 顺序返回。
 */
public class FindDifference {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> n1 = new HashSet<>(), n2 = new HashSet<>();
        for (int i : nums1) {
            n1.add(i);
        }
        for (int i : nums2) {
            n2.add(i);
        }
        List<List<Integer>> ans = new ArrayList<>(2);
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        for (int i : n1) {
            if (!n2.contains(i)) {
                l1.add(i);
            }
        }
        for (int i : n2) {
            if (!n1.contains(i)) {
                l2.add(i);
            }
        }
        ans.add(l1);
        ans.add(l2);
        return ans;
    }
}
