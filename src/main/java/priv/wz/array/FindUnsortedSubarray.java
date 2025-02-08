package priv.wz.array;

/**
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 请你找出符合题意的 最短 子数组，并输出它的长度
 */
public class FindUnsortedSubarray {
    public int findUnsortedSubarray(int[] nums) {
        int i = 0, j = 1;
        int l = 0, r = 0;
        while (j < nums.length) {
            if (nums[i] <= nums[j]) {
                i++;
                j++;
            } else {
                l = i;
                break;
            }
        }
        // 整个数组升序
        if (j == nums.length) {
            return 0;
        }
        j = nums.length - 1;
        i = j - 1;
        while (i >= l) {
            if (nums[i] <= nums[j]) {
                i--;
                j--;
            } else {
                r = j;
                break;
            }
        }
        // 此时 l 和 r 区间需要重排序
        int min = nums[l], max = min;
        i = l;
        j = r;
        while (i <= j) {
            if (nums[i] < min) {
                min = nums[i];
            } else if (nums[i] > max) {
                max = nums[i];
            }
            i++;
        }
        while (r < nums.length && nums[r] < max) {
            r++;
        }
        while (l >= 0 && nums[l] > min) {
            l--;
        }
        return r - l - 1;
    }

    public static void main(String[] args) {
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray(new int[]{2, 1}));
    }
}
