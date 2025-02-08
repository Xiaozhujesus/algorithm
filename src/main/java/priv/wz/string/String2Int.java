package priv.wz.string;

/**
 * 实现函数 atoi 。函数的功能为将字符串转化为整数
 */
public class String2Int {
    public int atoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int i = 0;
        while (str.charAt(i) == ' ') {
            i++;
        }
        int sign = 1;
        if (str.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        if (str.charAt(i) == '+') {
            i++;
        }
        int val = 0;
        for (int end = str.length(); i < end; i++) {
            // 合法性检查
            char cur = str.charAt(i);
            if (cur > '9' || cur < '0') {
                break;
            }
            // 溢出检查
            if (val > Integer.MAX_VALUE / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            } else if (val == Integer.MAX_VALUE / 10) {
                if (sign == 1 && Integer.MAX_VALUE % 10 <= cur - '0') {
                    return Integer.MAX_VALUE;
                } else if (sign == -1 && -(Integer.MIN_VALUE % 10) <= cur - '0') {
                    return Integer.MIN_VALUE;
                }
            }
            val = val * 10 + cur - '0';
        }
        return val * sign;
    }
}
