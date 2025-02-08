package priv.wz.recursive;

/**
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法
 * 跟Fibonacci一样
 */
public class Jump {
    // 迭代
    public int JumpFloor(int n) {
        if (n < 3)
            return n;
        int pre2 = 1, pre1 = 2;
        int cur = 0;
        for (int i = 2; i < n; i++) {
            cur = pre2 + pre1;
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }

    // 递归，有重复子问题，因此可以 DP，上面就是优化的 DP
    public int jump(int target) {
        if (target < 3) {
            return target;
        }
        return jump(target - 2) + jump(target - 1);
    }
}
