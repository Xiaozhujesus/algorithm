package priv.wz.dp;

/**
 * 给你一个整数 n，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10^n
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：91
 * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：1
 */
public class CountNumbersWithUniqueDigits {

    // dp 优化，可以用数组记录n及小于位数字有多少种不同， dp[n+1] = dp[n] + （n+1位有多少不同）
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        // 1位有0~9共10个
        int ans = 10;
        // 最后一位可用数字个数，超过1位，第一位只能为 1 ~ 9，不能为 0
        int available = 9;
        // 除了最后一位，前面位的排列数
        int arrange = 9;

        // 从只有 1 位开始计数，然后考虑 2 位、3 位
        // 如果 n 超过 10 位一定出现重复数字，因此 available > 0
        while (n > 1 && available > 0) {
            arrange *= available;
            ans += arrange;
            n--;
            available--;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new CountNumbersWithUniqueDigits().countNumbersWithUniqueDigits(4));
    }
}
// 比如 n = 4，其实就是 9*9*8*7 + 9*9*8 + 9*9 + 10
