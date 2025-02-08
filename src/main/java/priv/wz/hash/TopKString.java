package priv.wz.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给定String类型的数组strArr，再给定整数k，请严格按照排名顺序打印出次数前k名的字符串。
 * [要求]
 * 如果strArr长度为N，时间复杂度请达到O(NlogK)
 *
 * 输出K行，每行有一个字符串和一个整数（字符串表示）。
 * 你需要按照出现出现次数由大到小输出，若出现次数相同时字符串字典序较小的优先输出
 *
 * 例子：
 * 输入：["1","1","2","3"],2
 * 输出：[["1","2"],["2","1"]]
 */
public class TopKString {
    public String[][] topKstrings (String[] strings, int k) {
        String[][] res = new String[k][];
        if (strings == null || strings.length == 0 || k == 0) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String s : strings) {
            map.put(s, map.getOrDefault(s, 0)+1);
        }
        PriorityQueue<Map.Entry<String, Integer>> q = new PriorityQueue<>((a, b) -> {
            int diff = a.getValue() - b.getValue();
            if (diff != 0) {
                return diff;
            } else {
                return b.getKey().compareTo(a.getKey());
            }
        });
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            q.offer(e);
            if (q.size() > k) {
                q.poll();
            }
        }
        for (int i = k-1; i >= 0; i--) {
            Map.Entry<String, Integer> e = q.poll();
            res[i] = new String[]{e.getKey(), String.valueOf(e.getValue())};
        }
        return res;
    }
}
