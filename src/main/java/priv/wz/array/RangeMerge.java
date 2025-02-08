package org.practice.array;

import java.util.*;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class RangeMerge {
    public int[][] solution(int[][] range) {
        if (range == null || range.length == 0) {
            return new int[0][2];
        }
        List<int[]> l = new LinkedList<>();
        Arrays.sort(range, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int start = range[0][0];
        int end = range[0][1];
        for (int i = 1; i < range.length; i++) {
            if (end < range[i][0]) {
                l.add(new int[]{start, end});
                start = range[i][0];
                end = range[i][1];
            } else if (range[i][1] > end) {
                end = range[i][1];
            }
        }
        l.add(new int[]{start, end});
        int[][] ret = new int[l.size()][2];
        for (int i = 0; i < l.size(); i++) {
            ret[i] = l.get(i);
        }
        return ret;
    }

    // List形式
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return res;
        }
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval i : intervals) {
            if (end < i.start) {
                res.add(new Interval(start, end));
                start = i.start;
                end = i.end;
            } else if (i.end > end) {
                end = i.end;
            }
        }
        res.add(new Interval(start, end));
        return res;
    }

    class Interval {
        int start;
        int end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    //归并排序
    private List<int[]> mergeSort(List<int[]> range) {
        if (range.size() == 1) {
            return range;
        }
        int mid = range.size() / 2;
        List<int[]> arr1 = mergeSort(range.subList(0, mid));
        List<int[]> arr2 = mergeSort(range.subList(mid, range.size()));
        return merge(arr1, arr2);
    }

    private List<int[]> merge(List<int[]> arr1, List<int[]> arr2) {
        List<int[]> ret = new ArrayList<>();
        int len1 = arr1.size();
        int len2 = arr2.size();
        int begin = arr1.get(0)[0] < arr2.get(0)[0] ? arr1.get(0)[0] : arr2.get(0)[0];
        int end = arr1.get(0)[0] < arr2.get(0)[0] ? arr1.get(0)[1] : arr2.get(0)[1];
        int i = 0, j = 0;
        while (i < len1 && j < len2) {
            if (arr1.get(i)[0] < arr2.get(j)[0]) {
                if (arr1.get(i)[0] > end) {
                    ret.add(new int[]{begin, end});
                    begin = arr1.get(i)[0];
                    end = arr1.get(i)[1];
                } else {
                    if (arr1.get(i)[1] > end) {
                        end = arr1.get(i)[1];
                    }
                }
                i++;
            } else {
                if (arr2.get(j)[0] > end) {
                    ret.add(new int[]{begin, end});
                    begin = arr2.get(j)[0];
                    end = arr2.get(j)[1];
                } else {
                    if (arr2.get(j)[1] > end) {
                        end = arr2.get(j)[1];
                    }
                }
                j++;
            }
        }
        while (i < len1) {
            if (arr1.get(i)[0] > end) {
                ret.add(new int[]{begin, end});
                begin = arr1.get(i)[0];
                end = arr1.get(i)[1];
            } else {
                if (arr1.get(i)[1] > end) {
                    end = arr1.get(i)[1];
                }
            }
            i++;
        }
        while (j < len2) {
            if (arr2.get(j)[0] > end) {
                ret.add(new int[]{begin, end});
                begin = arr2.get(j)[0];
                end = arr2.get(j)[1];
            } else {
                if (arr2.get(j)[1] > end) {
                    end = arr2.get(j)[1];
                }
            }
            j++;
        }
        return ret;
    }
}
