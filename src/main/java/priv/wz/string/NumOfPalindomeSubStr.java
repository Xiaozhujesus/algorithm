package priv.wz.string;

/**
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 */
public class NumOfPalindomeSubStr {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans += util(s, i);
        }
        return ans;
    }

    /**
     * 以 mid 位置为中心的回文串数量
     * 这是一种剪枝的搜素方法，当出现不匹配的字母时，不需要继续扩展，从而不需要列出所有子串然后判断是否回文
     */
    private int util(String s, int mid) {
        int ans = 0;
        int i = mid, j = mid;
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            ans++;
            i--;
            j++;
        }
        i = mid;
        j = mid + 1;
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            ans++;
            i--;
            j++;
        }
        return ans;
    }
}
