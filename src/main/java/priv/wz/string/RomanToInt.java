package priv.wz.string;

/**
 * 罗马数字转整数
 * <p>
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900
 * <p>
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内
 */

public class RomanToInt {
    public int f(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int sum = 0;
        int p = 0;
        while (p < s.length()) {
            if (s.charAt(p) == 'I') {
                if (p + 1 < s.length()) {
                    if (s.charAt(p + 1) == 'V') {
                        sum += 4;
                        p += 2;
                    } else if (s.charAt(p + 1) == 'X') {
                        sum += 9;
                        p += 2;
                    } else {
                        sum += 1;
                        p++;
                    }
                } else {
                    sum += 1;
                    p++;
                }
            } else if (s.charAt(p) == 'V') {
                sum += 5;
                p++;
            } else if (s.charAt(p) == 'X') {
                if (p + 1 < s.length()) {
                    if (s.charAt(p + 1) == 'L') {
                        sum += 40;
                        p += 2;
                    } else if (s.charAt(p + 1) == 'C') {
                        sum += 90;
                        p += 2;
                    } else {
                        sum += 10;
                        p++;
                    }
                } else {
                    sum += 10;
                    p++;
                }
            } else if (s.charAt(p) == 'L') {
                sum += 50;
                p++;
            } else if (s.charAt(p) == 'C') {
                if (p + 1 < s.length()) {
                    if (s.charAt(p + 1) == 'D') {
                        sum += 400;
                        p += 2;
                    } else if (s.charAt(p + 1) == 'M') {
                        sum += 900;
                        p += 2;
                    } else {
                        sum += 100;
                        p++;
                    }
                } else {
                    sum += 100;
                    p++;
                }
            } else if (s.charAt(p) == 'D') {
                sum += 500;
                p++;
            } else if (s.charAt(p) == 'M') {
                sum += 1000;
                p++;
            }
        }
        return sum;
    }
}
