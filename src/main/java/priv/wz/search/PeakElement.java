package priv.wz.search;

/**
 * 峰值元素是指其值大于左右相邻值的元素，
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 对于第一个元素，若其大于第二个元素可以认为是峰值，对于最后一个元素，
 * 如果其大于倒数第二个元素可以认为是峰值
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 */
public class PeakElement {
    public int peakElement(int[] arr) {
        return util(arr, 0, arr.length - 1);
    }

    /**
     * 技巧是，你可以假设 nums[-1] = nums[n] = -∞，这与原题目等价
     * 因为最左最右都是无限小，因此如果arr[mid] > arr[mid + 1]，
     * 那么mid左侧一定存在一个峰；反之，右侧一定存在
     * 二分查找比较元素大小决定搜索哪侧
     */
    private int util(int[] arr, int low, int high) {
        if (low == high) {
            return low;
        }
        int mid = (low + high) / 2;
        if (arr[mid] > arr[mid + 1]) {
            return util(arr, low, mid);
        } else {
            return util(arr, mid + 1, high);
        }
    }

    // 迭代
    public int peakElement2(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            // low < high， mid 一定小于 high，mid + 1 最多等于 high，因此不会越界
            int mid = (low + high) / 2;
            if (nums[mid] > nums[mid + 1])
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }
}
