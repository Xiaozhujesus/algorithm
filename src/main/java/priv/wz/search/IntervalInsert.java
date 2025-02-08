package priv.wz.search;

/**
 * 给你一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 */
public class IntervalInsert {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        int[] flat = new int[intervals.length * 2];
        for (int i = 0; i < flat.length; i++) {
            flat[i] = i % 2 == 0 ? intervals[i / 2][0] : intervals[i / 2][1];
        }

        // leftIndex 作为离newInterval[0]最近的第一个小于等于 newInterval[0] 的位置
        // rightIndex 作为离newInterval[1]最近的第一个小于等于 newInterval[1] 的位置
        int leftIndex = lessEqual(flat, newInterval[0]);
        int rightIndex = lessEqual(flat, newInterval[1]);
        // 双数在两个间隔中间，单数在间隔内
        if (leftIndex == flat.length - 1) {
            if (flat[flat.length - 1] == newInterval[0]) {
                // 连在最后并且合并
                int[][] ans = new int[intervals.length][2];
                for (int i = 0; i < intervals.length - 1; i++) {
                    System.arraycopy(intervals[i], 0, ans[i], 0, 2);
                }
                int[] last = new int[]{intervals[intervals.length - 1][0], newInterval[1]};
                ans[ans.length - 1] = last;
                return ans;
            } else {
                // 连在最后不合并
                int[][] ans = new int[intervals.length + 1][2];
                for (int i = 0; i < intervals.length; i++) {
                    System.arraycopy(intervals[i], 0, ans[i], 0, 2);
                }
                ans[intervals.length] = newInterval;
                return ans;
            }
        }
        if (rightIndex == 0) {
            
        }
        if (rightIndex == -1) {

        }
        return null;
    }

    /**
     * 返回 arr 中第一个小于等于 target 的元素的 index
     *
     * @param arr    递增
     * @param target
     * @return
     */
    private int lessEqual(int[] arr, int target) {
        if (target < arr[0]) {
            return -1;
        }
        if (target >= arr[arr.length - 1]) {
            return arr.length - 1;
        }
        int low = 0, high = arr.length - 1, mid;
        while (low <= high) {
            mid = (low + high) >> 1;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                // mid + 1 == arr.length 同时 arr[mid] < target 的情况就是 target 大于最大的，已经在上面处理过
                if (mid + 1 < arr.length) {
                    if (arr[mid + 1] > target) {
                        return mid;
                    } else if (arr[mid + 1] == target) {
                        return mid + 1;
                    } else {
                        low = mid + 1;
                    }
                }
            } else {
                high = mid - 1;
            }
        }
        // 不可能到这
        return -2;
    }
}
