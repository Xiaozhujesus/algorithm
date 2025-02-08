package priv.wz.search;

/**
 * 整数数组 nums 按升序排列，数组中的值互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转，使数组变为
 * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你旋转后的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class RotateArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1, mid;
        int first = nums[0], last = nums[nums.length - 1];
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > last) {
                if (target < nums[mid] && target >= first) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= last) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找旋转数组最小值
     */
    public int findMin(int[] arr) {
        int low = 0, high = arr.length - 1, mid;
        int last = arr[arr.length - 1];
        while (low < high) {
            mid = (low + high) / 2;
            if (arr[mid] > last) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return arr[low];
    }


    /**
     * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为
     * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
     * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
     * 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
     * 你必须尽可能减少整个操作步骤。
     */
    public boolean search2(int[] nums, int target) {
         return false;
    }
}
