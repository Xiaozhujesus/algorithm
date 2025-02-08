package priv.wz.string;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix (String[] strs) {
        if (strs == null || strs.length == 0 || strs[0].length() == 0) {
            return "";
        }
        int i = 0;
        StringBuilder res = new StringBuilder();
        char c = strs[0].charAt(0);
        while (true) {
            if (i < strs[0].length()) {
                c = strs[0].charAt(i);
            }
            for (String cur : strs) {
                if (!(i < cur.length() && cur.charAt(i) == c)) {
                    return res.toString();
                }
            }
            res.append(c);
            i++;
        }
    }
}
