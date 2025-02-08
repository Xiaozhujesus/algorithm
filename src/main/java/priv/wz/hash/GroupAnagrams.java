package priv.wz.hash;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 分组异序词，异序词指相同字母构成，位置不同的词
 * 只包含小写字母
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String tmp = convert(str);
            if (map.containsKey(tmp)) {
                map.get(tmp).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(tmp, list);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        ans.addAll(map.values());
        return ans;
    }

    public String convert(String arg) {
        int[] count = new int[26];
        for (char c : arg.toCharArray()) {
            count[c - 'a']++;
        }
        return Arrays.stream(count).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }
}
