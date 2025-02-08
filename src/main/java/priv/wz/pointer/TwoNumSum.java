package priv.wz.pointer;

/**
 * 给定一个有序数组，递增排序，数组中是否存在两个数的和等于目标值
 * 这道题与盛水最多的容器一样，仔细体会，都是在搜索过程中剪枝
 */
public class TwoNumSum {
    public boolean exist(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按非递减顺序排列
     * 请你从数组中找出满足相加之和等于目标数 target 的两个数。
     * 如果设这两个数分别是 numbers[index1] 和 numbers[index2]
     * 则 1 <= index1 < index2 <= numbers.length
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     * 你可以假设每个输入只对应唯一的答案 ，而且你不可以重复使用相同的元素。
     */
    public int[] twoSum(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        int[] ans = new int[2];
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                ans[0] = i + 1;
                ans[1] = j + 1;
                break;
            }
        }
        return ans;
    }
}
