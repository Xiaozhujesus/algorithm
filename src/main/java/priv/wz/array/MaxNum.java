package priv.wz.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 *
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 */
public class MaxNum {
    public String maxNum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        List<String> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(String.valueOf(nums[i]));
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return -comp(s1, s2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
        }

        String ret = sb.toString();
        // "0000"返回一个"0"
        if (ret.charAt(0) == '0') {
            return "0";
        }
        return ret;
    }

    private int comp(String s1, String s2) {
        if (s1.length() > s2.length() && s1.startsWith(s2)) {
            return comp(s1.substring(s2.length()), s2);
        }
        if (s1.length() < s2.length() && s2.startsWith(s1)) {
            return comp(s1, s2.substring(s1.length()));
        }
        return s1.compareTo(s2);
    }
}
