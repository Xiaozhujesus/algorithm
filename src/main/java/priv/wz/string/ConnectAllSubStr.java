package priv.wz.string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。找出所有 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * <p>
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar"。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * <p>
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 */
public class ConnectAllSubStr {
    public int[] f(String s, String[] arr) {
        if (s == null || arr == null || s.length() == 0 || arr.length == 0) {
            return new int[0];
        }
        int wordLen = arr[0].length();
        int windowNum = arr.length;
        int windowLen = wordLen * windowNum;
        if (windowLen > s.length()) {
            return new int[0];
        }
        List<Integer> list = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        //左开右闭
        int start = 0;
        while (start + windowLen <= s.length()) {
            int nextStart = match(s, start, wordLen, windowNum, map);
            if (nextStart == start) {
                list.add(start);
                start += wordLen;
            } else {
                start = nextStart;
            }
        }
        if (list.size() == 0) {
            return new int[0];
        } else {
            int size = list.size();
            int[] ret = new int[size];
            for (int p = 0; p < size; p++) {
                ret[p] = list.get(p);
            }
            return ret;
        }
    }

    /**
     * 感觉有点像kmp算法
     *
     * @param s         字符串
     * @param start     开始匹配的起始位置
     * @param wordLen   单词的长度
     * @param windowNum 窗口有多少元素
     * @return 在start开始，windowLen的窗口内，从后往前，arr可以匹配的最长子串的起始位置，如果完全匹配那么返回start
     * 如果不匹配，那么返回start+windowLen，上层根据该函数返回值对窗口进行移动
     */
    private int match(String s, int start, int wordLen, int windowNum, Map map) {
        String[] tmp = new String[windowNum];
        for (int i = 0; i < windowNum; i++) {
            tmp[i] = s.substring(start + i * wordLen, start + (i + 1) * wordLen);
        }
        Map<String, Integer> tmpm = new HashMap<>();
        //深拷贝
        tmpm.putAll(map);
        for (int i = windowNum - 1; i >= 0; i--) {
            if (tmpm.containsKey(tmp[i])) {
                int count = tmpm.get(tmp[i]);
                if (count > 0) {
                    tmpm.put(tmp[i], count - 1);
                } else {
                    return start + (i + 1) * wordLen;
                }
            } else {
                return start + (i + 1) * wordLen;
            }
        }
        return start;
    }
}
