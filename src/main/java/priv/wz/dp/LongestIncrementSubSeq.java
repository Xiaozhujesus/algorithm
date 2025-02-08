package priv.wz.dp;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度，子序列可以不连续
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4
 */
public class LongestIncrementSubSeq {
    //O(N2)
    public int solution(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = 1;
        // dp[i]表示以arr[i]结尾的最长递增子序列的长度
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            int maxlen = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] > maxlen) {
                    maxlen = dp[j];
                }
            }
            dp[i] = maxlen + 1;
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    //贪心加二分O(NlogN)
    public int[] solution2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        // dp[i] 代表所有长度为i的递增序列中末尾的元素最小是多少，根据该定义，反证法很容易证明 dp 一定递增
        // 与 greedy 算法中的 IncreasingTriplet 问题一样
        int[] dp = new int[arr.length];
        // history[i] 表示 arr[i] 在 dp 中的位置，history[0~i]中的最大值，表示子串history[0~i]的最长递增子序列的长度
        int[] history = new int[arr.length];
        // 记录目前为止最长递增序列的长度，也就是 dp 最后一个元素的位置
        int lastP = 0;
        dp[0] = arr[0];
        history[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            // 在 dp 中找第一大于等于 arr[i] 的元素并用 arr[i] 替换
            int low = 0, high = lastP, mid, target = lastP + 1; // 默认没有，arr[i] 在最右侧；
            while (low <= high) {
                mid = (low + high) / 2;
                if (dp[mid] < arr[i]) {
                    low = mid + 1;
                } else {
                    if (mid > 0 && dp[mid - 1] >= arr[i]) {
                        high = mid - 1;
                    } else {
                        target = mid;
                        break;
                    }
                }
            }
            dp[target] = arr[i];
            history[i] = target;
        }
        // return dp.size();

        // 具体的最长上升子序列
        int[] res = new int[lastP + 1];
        for (int i = arr.length - 1, j = lastP; j >= 0; --i) {
            if (history[i] == j) {
                res[j--] = arr[i];
            }
        }
        for (int i = 0; i < arr.length; i++) {

        }
        return res;
    }
}
