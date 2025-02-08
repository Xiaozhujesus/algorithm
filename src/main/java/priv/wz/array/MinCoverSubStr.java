package priv.wz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 */
public class MinCoverSubStr {

    private Map<Character, Integer> target = new HashMap<>();
    private Map<Character, Integer> count = new HashMap<>();
    private int ansL = 0;
    private int ansH = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(new MinCoverSubStr().minWindow("ADOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        for (char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        int low = 0, high = 0;
        while (high < s.length()) {
            char c = s.charAt(high);
            if (!target.containsKey(c)) {
                high++;
                continue;
            }
            count.put(c, count.getOrDefault(c, 0) + 1);
            // 移动窗口左侧
            if (cover()) {
                while (cover()) {
                    c = s.charAt(low);
                    while (!count.containsKey(c)) {
                        low++;
                        c = s.charAt(low);
                    }
                    count.put(c, count.get(c) - 1);
                    low++;
                }
                // 更新答案
                if (high - (low - 1) < ansH - ansL) {
                    ansL = low - 1;
                    ansH = high;
                }
            }
            high++;
        }
        if (ansH == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(ansL, ansH + 1);
    }

    /**
     * 当前 count 是否覆盖 target
     *
     * @return
     */
    boolean cover() {
        for (Map.Entry<Character, Integer> i : target.entrySet()) {
            if (i.getValue() > count.getOrDefault(i.getKey(), 0)) {
                return false;
            }
        }
        return true;
    }
}
