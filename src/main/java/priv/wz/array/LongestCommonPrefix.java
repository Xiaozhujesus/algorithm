package priv.wz.array;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            return "";
        }
        int curPos = 0;
        char pre;
        while (true) {
            if (curPos == strs[0].length()) {
                return strs[0].substring(0, curPos);
            }
            pre = strs[0].charAt(curPos);
            for (int i = 1; i < strs.length; i++) {
                if (curPos == strs[i].length() || strs[i].charAt(curPos) != pre) {
                    return strs[0].substring(0, curPos);
                }
            }
            curPos++;
        }
    }

    public static void main(String[] args) {
        String[] ss = {"flower", "flow", "flight"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(ss));
    }
}
