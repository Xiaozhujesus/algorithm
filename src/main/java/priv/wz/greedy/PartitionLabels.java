package priv.wz.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * 例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * 返回一个表示每个字符串片段的长度的列表。
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        // 每个字符最后的位置
        Map<Character, Integer> lastPos = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastPos.put(s.charAt(i), i);
        }
        int begin = 0, end = 0, cur = 0;
        while (begin < s.length()) {
            while (cur <= end) {
                // 有点类似 jump 那道题
                end = Math.max(end, lastPos.get(s.charAt(cur)));
                cur++;
            }
            ans.add(cur - begin);
            begin = end = cur;
        }
        return ans;
    }

    // 遍历一遍记录每个字符的begin和end，合并区间也可以，没有合并的单独为 1
}
