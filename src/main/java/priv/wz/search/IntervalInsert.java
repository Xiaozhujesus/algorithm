package priv.wz.search;

/**
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
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
        int low = 0, high = flat.length - 1, mid, leftIndex = -1, rightIndex = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (flat[mid] == newInterval[0]) {
                leftIndex = mid;
                break;
            } else if (flat[mid] < newInterval[0]) {
                if (mid + 1 < flat.length && flat[mid + 1] <= newInterval[0]) {
                    low = mid + 1;
                } else {
                    leftIndex = mid;
                    break;
                }
            } else {
                mid = high - 1;
            }
        }

        low = 0;
        high = flat.length - 1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (flat[mid] == newInterval[1]) {
                rightIndex = mid;
                break;
            } else if (flat[mid] < newInterval[1]) {
                if (mid + 1 < flat.length && flat[mid + 1] <= newInterval[1]) {
                    low = mid + 1;
                } else {
                    rightIndex = mid;
                    break;
                }
            } else {
                mid = high - 1;
            }
        }
        //
        return null;
    }
}
