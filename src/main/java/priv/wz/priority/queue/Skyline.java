package priv.wz.priority.queue;

import java.util.*;

/**
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的天际线 。
 * <p>
 * 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
 * <p>
 * lefti 是第 i 座建筑物左边缘的 x 坐标。
 * righti 是第 i 座建筑物右边缘的 x 坐标。
 * heighti 是第 i 座建筑物的高度。
 * <p>
 * 你可以假设所有的建筑都是完美的长方形，在高度为 0 的绝对平坦的表面上。
 * <p>
 * 天际线应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标排序 。"关键点" 是水平线段的左端点。列表中最后一个点是最
 * 右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 * <p>
 * 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；
 * 三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 */
public class Skyline {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // 存储所有建筑物的上顶点，key 为横坐标，value 为高度，重合可能有多个，负数表示右侧，正数表示左侧
        Map<Integer, List<Integer>> points = new TreeMap<>();
        for (int[] building : buildings) {
            List<Integer> hights = points.computeIfAbsent(building[0], key -> new ArrayList<>());
            hights.add(building[2]);
            hights = points.computeIfAbsent(building[1], key -> new ArrayList<>());
            hights.add(-building[2]);
        }
        int preHight = 0;
        PriorityQueue<Integer> highest = new PriorityQueue<>((a, b) -> b - a);
        highest.add(0);
        List<List<Integer>> ret = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> cur : points.entrySet()) {
            List<Integer> hights = cur.getValue();
            for (Integer h : hights) {
                if (h > 0) {
                    highest.add(h);
                } else {
                    highest.remove(-h);
                }
            }
            Integer curHighest = highest.peek();
            if (preHight != curHighest) {
                ret.add(Arrays.asList(cur.getKey(), curHighest));
                preHight = curHighest;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        List<List<Integer>> skyline = new Skyline().getSkyline(buildings);
    }
}
