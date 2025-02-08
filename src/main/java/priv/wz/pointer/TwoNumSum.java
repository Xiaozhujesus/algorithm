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
}
