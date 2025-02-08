package priv.wz.string;

public class KMP {
    public int strStr(String ss, String pp) {
        int[] next = next(pp);
        int i = 0, j = 0;
        while (true) {
            while (i < ss.length() && j < pp.length() && ss.charAt(i) == pp.charAt(j)) {
                i++;
                j++;
            }
            if (j == pp.length()) {
                return i - j;
            }
            if (i == ss.length()) {
                return -1;
            }
            int index = next[j - 1];
            while (index != 0 && pp.charAt(index) != ss.charAt(i)) {
                index = next[index - 1];
            }
            j = index;
        }
    }

    /**
     * next[i] 表示 needle[0..i] 子串（不包含整个字符串）的最长匹配前后缀的长度，举个例子：
     * needle 是 aaaab，那么 next 为 [0,1,2,3,0]，next[3] 是 3 表示子串 aaaa 的最长匹配前后缀
     * 长度为 3，也就是前面的 aaa 和后面的 aaa 匹配
     */
    private int[] next(String pp) {
        char[] arr = pp.toCharArray();
        int[] next = new int[pp.length()];
        for (int i = 1; i < next.length; i++) {
            if (arr[i] == arr[next[i - 1]]) {
                next[i] = next[i - 1] + 1;
            } else {

            }
        }
        return next;
    }
}
