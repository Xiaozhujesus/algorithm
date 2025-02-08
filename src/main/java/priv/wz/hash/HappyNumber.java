package priv.wz.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1
 * 也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数
 */
public class HappyNumber {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (!set.contains(n)) {
            set.add(n);
            int next = 0;
            while (n != 0) {
                int tmp = n % 10;
                next += tmp * tmp;
                n = n / 10;
            }
            if (next == 1) {
                return true;
            } else {
                n = next;
            }
        }
        return false;
    }
}
