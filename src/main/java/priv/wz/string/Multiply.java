package priv.wz.string;

import java.util.Collections;

/**
 * 给定两个以字符串形式表示的非负整数num1和num2，返回num1和num2的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 */
public class Multiply {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            String mul = mul(num1, num2.charAt(i));
            ans = add(ans, mul + String.join("", Collections.nCopies(num2.length() - 1 - i, "0")));
        }
        return ans;
    }

    private String add(String s1, String s2) {
        StringBuilder ans = new StringBuilder();
        int i = s1.length() - 1, j = s2.length() - 1, carry = 0;
        while (i >= 0 && j >= 0) {
            int tmp = (s1.charAt(i) - '0') + (s2.charAt(j) - '0') + carry;
            carry = tmp / 10;
            ans.append(tmp % 10);
            i--;
            j--;
        }
        while (i >= 0) {
            int tmp = s1.charAt(i) - '0' + carry;
            carry = tmp / 10;
            ans.append(tmp % 10);
            i--;
        }
        while (j >= 0) {
            int tmp = s2.charAt(j) - '0' + carry;
            carry = tmp / 10;
            ans.append(tmp % 10);
            j--;
        }
        if (carry > 0) {
            ans.append(carry);
        }
        return ans.reverse().toString();
    }

    private String mul(String s, char c) {
        StringBuilder ans = new StringBuilder();
        int ic = c - '0';
        int carry = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int tmp = (s.charAt(i) - '0') * ic + carry;
            carry = tmp / 10;
            ans.append(tmp % 10);
        }
        if (carry > 0) {
            ans.append(carry);
        }
        return ans.reverse().toString();
    }

    static class Tmp {
        public synchronized void f1(String name) {
            System.out.println(name + "f1 begin");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            System.out.println(name + "f1 end");
        }

        public synchronized void f2(String name) {
            System.out.println(name+"f2 begin");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            System.out.println(name + "f2 end");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Tmp tmp = new Tmp();
        Thread t1 = new Thread(() -> {
            tmp.f1("t1");

            tmp.f2("t1");
        });

        Thread t2 = new Thread(() -> {
            tmp.f2("t2");

            tmp.f1("t2");
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("finish");
    }
}
