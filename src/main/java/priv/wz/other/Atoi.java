package priv.wz.other;

public class Atoi {

    public int atoi(String s) {
        boolean neg = false;
        if (s == null || s.length() == 0) {
            return 0;
        }
        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        if (i == s.length()) {
            return 0;
        }
        if (s.charAt(i) == '-') {
            neg = true;
            i++;
        } else if (s.charAt(i) == '+') {
            i++;
        }

        if (i == s.length()) {
            return 0;
        }
        while (i < s.length() && s.charAt(i) == '0') {
            i++;
        }
        if (i == s.length()) {
            return 0;
        }
        int begin = i;
        int j = 0;
        int result = 0;
        int maxLength = String.valueOf(Integer.MAX_VALUE).length();

        for (; i < s.length() && Character.isDigit(s.charAt(i)) && j < maxLength - 1; i++, j++) {
            result = result * 10 + (s.charAt(i) - '0');
        }
        if (!(i < s.length() && Character.isDigit(s.charAt(i)))) {
            if (neg) {
                return -result;
            }
            return result;
        }
        if (begin + maxLength < s.length() && Character.isDigit(s.charAt(begin + maxLength))) {
            if (neg) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        String substring = s.substring(begin, begin + maxLength);
        String minWithoutSign = String.valueOf(Integer.MIN_VALUE).substring(1);
        String maxWithoutSign = String.valueOf(Integer.MAX_VALUE);
        if (minWithoutSign.compareTo(substring) <= 0) {
            if (neg) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        } else if (maxWithoutSign.compareTo(substring) == 0) {
            if (neg) {
                return -Integer.MAX_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        } else {
            int tmp = result * 10 + (s.charAt(i) - '0');
            if (neg) {
                return -tmp;
            } else {
                return tmp;
            }
        }
    }

    public static void main(String[] args) {
        Atoi atoi = new Atoi();
        int atoi1 = atoi.atoi(" -115579378e25");
    }
}
