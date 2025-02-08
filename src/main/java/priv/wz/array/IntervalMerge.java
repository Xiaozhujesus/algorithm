package priv.wz.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 */
public class IntervalMerge {
    public int[][] merge(int[][] intervals) {
        quickSort(intervals, 0, intervals.length - 1);
        List<int[]> ans = new ArrayList<>();
        int[] cur = intervals[0];
        for (int[] item : intervals) {
            if (item[0] <= cur[1]) {
                if (item[1] > cur[1]) {
                    cur[1] = item[1];
                }
            } else {
                ans.add(cur);
                cur = item;
            }
        }
        ans.add(cur);
        return ans.toArray(new int[][]{});
    }


    private void quickSort(int[][] arrs, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int partition = partition(arrs, begin, end);
        quickSort(arrs, begin, partition - 1);
        quickSort(arrs, partition + 1, end);
    }

    private int partition(int[][] arrs, int begin, int end) {
        int l = begin - 1, r = begin;
        while (r < end) {
            if (arrs[r][0] < arrs[end][0]) {
                l++;
                int[] tmp = arrs[l];
                arrs[l] = arrs[r];
                arrs[r] = tmp;
            }
            r++;
        }
        l++;
        int[] tmp = arrs[l];
        arrs[l] = arrs[r];
        arrs[r] = tmp;
        return l;
    }
}
