package priv.wz.bit;

/**
 * 一个整型数组里除了1个数字之外，其他的数字都出现了两次。请写程序找出只出现一次的数字。如果有两个只出现一次的数字呢？如何找出？
 */
public class AppearOnceNum {
    // 1 个
    public int one(int[] arr) {
        int ans = 0;
        for (int cur : arr) {
            ans ^= cur;
        }
        return ans;
    }

    // 2 个
    public int[] two(int[] arr) {
        int num1 = 0;
        int num2 = 0;
        int ret = 0;
        for (int k : arr) {
            ret ^= k;
        }
        ret &= (-ret);
        for (int k : arr) {
            if ((k & ret) == 0) {
                num1 ^= k;
            } else {
                num2 ^= k;
            }
        }
        return new int[]{num1, num2};
    }
}


/**
 * 想利用一个的解决方案，关键是将两个不同的数区分开
 * 让数组中的每一个数异或一下，最后会得到一个结果ret，就是两个出现一次的数字的异或结果这个结果肯定是由两个不同数字异或而来
 * 我们找ret二进制中第一个为1的位置i，因为1一定是由0,1异或而来，因此要求的两个数中，
 * 一定有一个数的二进制中的第i个位置为1， 一个为0.这样就可以区分两个数了
 * <p>
 * 如何找到位置i？取反加一后与原数按位与即可，可用i = ret ^ (-ret)，这两个数与该数与的结果一定不同，其他数相同的两个数一定会分到相同的组里
 * 因为计算机用补码存取二进制数，而负数的补码为反码+1，举个例子
 * 假如ret = 1110 ， -ret = 0010 , 所以 i = 0010
 * 所以，再异或一遍即可得到答案。
 */