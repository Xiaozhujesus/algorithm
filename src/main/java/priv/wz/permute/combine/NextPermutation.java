package priv.wz.permute.combine;

/**
 * 整数数组的一个 排列就是将其所有成员以序列或线性顺序排列。
 * <p>
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据
 * 其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
 * 如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * <p>
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 */
public class NextPermutation {
    /**
     * 从后往前找第一个下降点，比如位置 i，那么从 i+1 ~ last 找
     * 第一个比 nums[i] 大的数，与 i 互换后，将 i+1 ~ last 按升序排列
     * 如果有多个比 nums[i] 大，找最右侧的那个
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }

        int i = nums.length - 1;
        if (nums[i + 1] < nums[i]) {
            swap(nums, i, i + 1);
            return;
        }
        i -= 2;
        while (i >= 0 && nums[i] > nums[i - 1]) {
            i--;
        }
        if (i < 0) {
            int j = nums.length - 1;
            i = 0;
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        } else {
            int index = bsearch(nums, i + 1, nums.length - 1, nums[i]);
            swap(nums, i, index);
            int j = nums.length - 1;
            i++;
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private int bsearch(int[] arr, int low, int high, int target) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > target) {
                if (arr[mid + 1] <= target) {
                    return mid;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // 不会到这
        return -1;
    }
}
