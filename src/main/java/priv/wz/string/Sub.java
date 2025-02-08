package priv.wz.string;

/**
 * 两个字符串表示的非负整数相减
 * num1 和num2 都只会包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库
 */
public class Sub {
    public String sub(String n1, String n2) {
        StringBuilder ans = new StringBuilder();
        int carry = 0, i = n1.length() - 1, j = n2.length() - 1;
        String reverse = "";
        if (i < j || (i == j && n1.compareTo(n2) < 0)) {
            String tmp = n1;
            n1 = n2;
            n2 = tmp;
            reverse = "-";
        }
        for (; i >= 0 && j >= 0; i--, j--) {
            int tmp = n1.charAt(i) - n2.charAt(i) + carry;
            if (tmp < 0) {
                carry = -1;
                tmp += 10;
            } else {
                carry = 0;
            }
            ans.append(tmp);
        }
        if (i == j) {
            int k = 0;
            while (k < ans.length() && ans.charAt(k) == '0') {
                k++;
            }
            if (k == ans.length()) {
                return "0";
            } else {
                return reverse + ans.substring(k);
            }
        } else {
            while (i >= 0) {
                int tmp = n1.charAt(i) - '0' + carry;
                if (tmp < 0) {
                    carry = -1;
                    tmp += 10;
                } else {
                    carry = 0;
                }
                ans.append(tmp);
                i--;
            }
            int k = 0;
            while (ans.charAt(k) == '0') {
                k++;
            }
            return reverse + ans.substring(k);
        }
    }
}
