package priv.wz.pointer;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数
 */
public class SubarraySum {
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        if (nums == null || nums.length == 0) {
            return ans;
        }
        int l, r, sum;
        int lbound = 0, rbound = nums.length - 1;
        while (lbound <= rbound) {
            l = r = lbound;
            sum = 0;
            while (r <= rbound) {
                sum += nums[r];
                if (sum == k) {
                    ans++;
                }
                r++;
            }
            while (l < rbound) {
                sum -= nums[l];
                if (sum == k) {
                    ans++;
                }
                l++;
            }
            lbound++;
            rbound--;
        }
        return ans;
    }

    public int subarraySum2(int[] nums, int k) {
        int ans = 0;
        if (nums == null) {
            return ans;
        }
        int l = 0, h = 0, sum = 0;
        while (h <= nums.length) {
            while (h < nums.length && sum < k) {
                sum += nums[h];
                h++;
            }
            while (l < h && sum > k) {
                sum -= nums[l];
                l++;
            }
            if (sum == k) {
                if (l != h) {
                    ans++;
                }
                if (h == nums.length) {
                    break;
                }
                sum += nums[h];
                h++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new SubarraySum().subarraySum2(new int[]{-1, -1, 1}, 0));
    }
}
