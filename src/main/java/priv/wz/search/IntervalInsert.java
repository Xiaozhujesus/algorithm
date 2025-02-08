package priv.wz.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 */
public class IntervalInsert {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // 添加所有在新区间之前的区间
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        // 合并重叠区间
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // 添加剩余的区间
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        // 将 List<int[]> 转换为 int[][]
        return result.toArray(new int[result.size()][]);
    }
}
