package priv.wz.string;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * <p>
 * 输入: "race a car"
 * 输出: false
 */
public class IsPalindrome {
    public boolean f(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    c = Character.toLowerCase(c);
                }
                sb.append(c);
            } else if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        String tmp = sb.toString();
        int i = 0;
        int j = tmp.length() - 1;
        while (i < j) {
            if (tmp.charAt(i) == tmp.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 整数回文判断
     */
    public boolean intIsPalindrome(int a) {
        if (a < 0) {
            return false;
        }
        int before = a;
        int after = 0;
        while (a != 0) {
            after = after * 10 + a % 10;
            a = a / 10;
        }
        return before == after;
    }
}
