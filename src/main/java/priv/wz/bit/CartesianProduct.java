package org.practice.bit;

/**
 * 给定n个字符数组，求n个数组的笛卡尔积；例如：
 * [a,b]和[c]，结果为[a,c]和[b,c]
 */
public class CartesianProduct {
    public char[][] f(char[][] arr) {
        int end = 1;
        // 一共有end种排列
        for (int i = 0; i < arr.length; i++) {
            end *= arr[i].length;
        }
        char[][] res = new char[end][];
        for (int i = 0; i < end; i++) {
            char[] tmp = new char[arr.length];
            // 求一个数字各个位的数字，每一位的进制不同，例如，个位是3进制，十位是5进制，那么十进制的8就是22，因为2*3+2 == 8
            // 这里每一位的进制为数组的长度，例如给定{'a','b','c'},{'d'},{'e','f'}，那么进制就是3，1，2
            int cur = i;
            for (int j = 0; j < arr.length; j++) {
                int p = arr[j].length;
                int left = cur % p;
                tmp[j] = arr[j][left];
                cur = (cur - left) / p;
            }
            res[i] = tmp;
        }
        return res;
    }

    public static void main(String[] args) {
        char[][] res = new CartesianProduct().f(new char[][]{{'a', 'b', 'c'}, {'d'}, {'e'}});
        for (int i = 0; i < res.length; i++) {
            int end = res[i].length;
            for (int j = 0; j < end; j++) {
                System.out.print(res[i][j]);
            }
            System.out.println();
        }
    }
}
