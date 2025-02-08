package priv.wz.dp;

/**
 * 数组最大子段和，连续子段，只要连续通常都可以用滑动窗口
 */
public class MaxSubArraySum {
    public int solution(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = arr[0];
        int preSubArraySum = max;
        int current;
        for (int i = 1; i < arr.length; i++) {
            /**
             * preSubArraySum表示以arr[i-1]结尾的最大子段和，那么以arr[i]结尾的最大子段和为
             * max(current, arr[i]),current = preSubArraySum + arr[i]
             *
             * 我们需要另一个变量记录目前为止最大子段和
             */
            current = preSubArraySum + arr[i];
            preSubArraySum = Math.max(current, arr[i]);
            max = Math.max(max, preSubArraySum);
        }
        return max;
    }
}
