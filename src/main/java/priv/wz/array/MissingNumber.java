package priv.wz.array;

/**
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * <p>
 * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现
 */
public class MissingNumber {
    public int f(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int len = arr.length;
        int sum = (1 + len) * len / 2;
        for (int i = 0; i < len; i++) {
            sum -= arr[i];
        }
        return sum;
    }
}
