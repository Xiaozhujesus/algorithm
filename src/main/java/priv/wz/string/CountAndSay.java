package priv.wz.string;

/**
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 * 注意：整数序列中的每一项将表示为一个字符串。
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 第一项是数字 1
 * 描述前一项，这个数是 1 即 “一个 1 ”，记作 11
 * 描述前一项，这个数是 11 即 “两个 1 ” ，记作 21
 * 描述前一项，这个数是 21 即 “一个 2 一个 1 ” ，记作 1211
 * 描述前一项，这个数是 1211 即 “一个 1 一个 2 两个 1 ” ，记作 111221
 */
public class CountAndSay {

    public String f(int n) {
        String cur = "1";
        while (--n > 0) {
            String tmp = util(cur);
            cur = tmp;
        }
        return cur;
    }
    private String util(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        char cur = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length();i++) {
            if (s.charAt(i) == cur) {
                count++;
            } else {
                stringBuilder.append(count).append(cur);
                cur = s.charAt(i);
                count = 1;
            }
        }
        stringBuilder.append(count).append(cur);
        return stringBuilder.toString();
    }
}
