package priv.wz.other;

/**
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 * <p>
 * 例如：
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 */
public class Excel {
    /**
     * 特殊的是没有 0
     */
    public String convertToTitle(int columnNumber) {
        StringBuilder ans = new StringBuilder();
        char zero = 'A' - 1;
        while (columnNumber != 0) {
            int tmp = columnNumber % 26;
            if (tmp == 0) {
                ans.append('Z');
                columnNumber -= 1;
                columnNumber /= 26;
            } else {
                ans.append((char) (zero + tmp));
                columnNumber /= 26;
            }
        }
        return ans.reverse().toString();
    }



    public static void main(String[] args) {
    }

}
