package priv.wz.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 */
public class Zconvert {
    public String convert(String s, int numRows) {
        if (s == null) {
            return null;
        }
        if (numRows == 1) {
            return s;
        }
        Queue<Character>[] qs = new Queue[numRows];
        for (int i = 0; i < numRows; i++) {
            qs[i] = new LinkedList();
        }
        for (int i = 0; i < s.length(); i++) {
            int cur = i;
            cur = cur % (2 * numRows - 2);
            if (cur >= numRows) {
                cur = 2 * numRows - 2 - cur;
            }
            qs[cur].add(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            while (!qs[i].isEmpty()) {
                sb.append(qs[i].remove());
            }
        }
        return sb.toString();
    }
}
