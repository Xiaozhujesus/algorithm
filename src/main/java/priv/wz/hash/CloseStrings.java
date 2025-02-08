package priv.wz.hash;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
 * 操作 1：交换任意两个 现有 字符。
 * 例如，abcde -> aecdb
 * 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
 * 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
 * 你可以根据需要对任意一个字符串多次使用这两种操作。
 * <p>
 * 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。
 */
public class CloseStrings {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        Map<Character, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        for (int i = 0; i < word1.length(); i++) {
            char c = word1.charAt(i);
            int count = map1.getOrDefault(c, 0);
            map1.put(c, count + 1);
            c = word2.charAt(i);
            count = map2.getOrDefault(c, 0);
            map2.put(c, count + 1);
        }
        Set<Character> key = new HashSet<>();
        List<Integer> val = new ArrayList<>();
        for (Map.Entry<Character, Integer> e : map1.entrySet()) {
            key.add(e.getKey());
            val.add(e.getValue());
        }
        for (Map.Entry<Character, Integer> e : map2.entrySet()) {
            if (!key.contains(e.getKey())) {
                return false;
            } else {
                key.remove(e.getKey());
            }
            if (!val.contains(e.getValue())) {
                return false;
            } else {
                val.remove(e.getValue());
            }
        }

        return key.isEmpty() && val.isEmpty();
    }

    // 仅包含小写字母
    public boolean closeStrings1(String word1, String word2) {
        int[] count1 = new int[26], count2 = new int[26];
        for (char c : word1.toCharArray()) {
            count1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            count2[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (count1[i] > 0 && count2[i] == 0 || count1[i] == 0 && count2[i] > 0) {
                return false;
            }
        }
        Arrays.sort(count1);
        Arrays.sort(count2);
        return Arrays.equals(count1, count2);
    }
}
