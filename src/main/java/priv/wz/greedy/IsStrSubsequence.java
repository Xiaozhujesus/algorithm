package priv.wz.greedy;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列
 * 例如，"ace"是"abcde"的一个子序列，而"aec"不是
 */
public class IsStrSubsequence {
    public boolean solutino(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.equals("")) {
            return true;
        }
        int tindex = 0;
        for (int i = 0; i < s.length(); i++) {
            while (tindex < t.length() && t.charAt(tindex) != s.charAt(i)) {
                tindex++;
            }
            if (tindex == t.length()) {
                return false;
            }
            tindex++;
        }
        return true;

//        int sindex = 0;
//        int slen = s.length();
//        for (int i = 0,tlen = t.length(); i < tlen; i++) {
//            if (s.charAt(sindex) == t.charAt(i)) {
//                if ((++sindex) == slen) {
//                    return true;
//                }
//            }
//        }
//        return false;
    }
}
