package priv.wz.pointer;

/**
 *
 */
public class FindMaxAverage {

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0, max = 0;
        int i = 0, j = 0;
        while (j < k && j < nums.length) {
            sum += nums[j++];
        }
        if (j != k) {
            return 0;
        }
        max = sum;
        while (j < nums.length) {
            sum += (nums[j++] - nums[i++]);
            max = Math.max(max, sum);
        }
        return max * 1.0 / k;
    }

    public static void main(String[] args) {
        System.out.println(new FindMaxAverage().findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
    }
}
