package org.practice.recursive;

/**
 * 最长公共子序列，子序列可以不连续
 */
public class LCS {
    /**
     * Meaning of the input parameters
     *
     *     a = a0 a1 a2 .... a(i-1)
     *     b = b0 b1 b2 .... b(j-1)
     * @param i
     * @param j
     * @param a
     * @param b
     * @return
     */
    public int lcs(int i, int j, String a, String b) {
        if (i < 0 || j < 0) {
            return 0;
        }
        if (a.charAt(i) == b.charAt(j)) {
            return 1 + lcs(i-1, j-1, a, b);
        }
        int sol1, sol2;
        sol1 = lcs(i-1, j, a, b);
        sol2 = lcs(i, j-1, a, b);
        return sol1 > sol1 ? sol1 : sol2;
    }
}

//很明显上面有重复子问题，所以应该用dp解决