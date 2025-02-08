package priv.wz.other;

public class ZConvert {
    public String convert(String s, int numRows) {
        if (s == null || numRows == 1 || s.length() <= numRows) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            int index = row;
            int sum = 2 * numRows - 2;
            int step;
            if (row == 0 || row == numRows - 1) {
                step = sum;
            } else {
                step = sum - 2 * index;
            }
            while (index < s.length()) {
                sb.append(s.charAt(index));
                if (row == 0 || row == numRows - 1) {
                    index += step;
                } else {
                    index += step;
                    step = sum - step;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String paypalishiring = new ZConvert().convert("PAYPALISHIRING", 4);
        System.out.println(paypalishiring);
    }
}
