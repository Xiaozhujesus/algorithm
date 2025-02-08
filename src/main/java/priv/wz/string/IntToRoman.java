package priv.wz.string;

/**
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * 例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，
 * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给你一个整数，将其转为罗马数字。
 */
public class IntToRoman {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        if (num >= 1000) {
            for (int i = 0, end = num / 1000; i < end; i++) {
                sb.append('M');
            }
            num = num % 1000;
        }
        if (num >= 900) {
            sb.append('C');
            sb.append('M');
            num = num % 900;
        }
        if (num >= 500) {
            sb.append('D');
            num -= 500;
        }
        if (num >= 400) {
            sb.append('C');
            sb.append('D');
            num -= 400;
        }
        if (num >= 100) {
            for (int i = 0, end = num / 100; i < end; i++) {
                sb.append('C');
            }
            num = num % 100;
        }
        if (num >= 90) {
            sb.append('X');
            sb.append('C');
            num -= 90;
        }
        if (num >= 50) {
            sb.append('L');
            num -= 50;
        }
        if (num >= 40) {
            sb.append('X');
            sb.append("L");
            num -= 40;
        }
        if (num >= 10) {
            for (int i = 0, end = num / 10; i < end; i++) {
                sb.append('X');
            }
            num = num % 10;
        }
        if (num >= 9) {
            sb.append('I');
            sb.append('X');
            num -= 9;
        }
        if (num >= 5) {
            sb.append('V');
            num -= 5;
        }
        if (num >= 4) {
            sb.append('I');
            sb.append('V');
            num -= 4;
        }
        if (num != 0) {
            for (int i = 0; i < num; i++) {
                sb.append('I');
            }
        }
        return sb.toString();
    }
}
