package org.practice.dp;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class MaxProductSubArr {
    public int f(int[] nums) {
        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int product = nums[i];
            int max = product;
            for (int j = i - 1; j >= 0; j--) {
                product *= nums[j];
                if (product > max) {
                    max = product;
                }
                if (product == 0) {
                    break;
                }
            }
            if (max > ret) {
                ret = max;
            }
        }
        return ret;
    }


    // dp
    public int g(int[] nums) {
        int[] min = new int[nums.length];
        int[] max = new int[nums.length];
        min[0] = max[0] = nums[0];
        int res = max[0];
        for (int i = 1; i < nums.length; i++) {
            int[] mm = maxAndMin(nums[i], nums[i] * min[i - 1], nums[i] * max[i - 1]);
            max[i] = mm[0];
            min[i] = mm[1];
            if (max[i] > res) {
                res = max[i];
            }
        }
        return max[nums.length - 1];
    }

    private int[] maxAndMin(int a, int b, int c) {
        int[] ret = new int[2];
        int min = a;
        int max = a;
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
