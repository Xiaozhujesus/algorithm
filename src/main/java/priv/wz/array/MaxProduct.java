package org.practice.array;

/**
 * 给定一个数组，求子数组最大乘积
 */
public class MaxProduct {
    public double maxProduct(double[] nums) {
        double[] min = new double[nums.length];
        double[] max = new double[nums.length];
        min[0] = max[0] = nums[0];
        double res = max[0];
        for (int i = 1; i < nums.length; i++) {
            double[] mm = maxAndMin(nums[i], nums[i] * min[i - 1], nums[i] * max[i - 1]);
            max[i] = mm[0];
            min[i] = mm[1];
            if (max[i] > res) {
                res = max[i];
            }
        }
        return res;
    }

    private double[] maxAndMin(double a, double b, double c) {
        double[] ret = new double[2];
        double min = a;
        double max = a;
        if (b > max) {
            max = b;
        } else if (b < min) {
            min = b;
        }
        if (c > max) {
            max = c;
        } else if (c < min) {
            min = c;
        }
        ret[0] = max;
        ret[1] = min;
        return ret;
    }
}
