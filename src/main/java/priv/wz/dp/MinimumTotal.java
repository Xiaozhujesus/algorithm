package priv.wz.dp;

import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */
public class MinimumTotal {
    public int minimumTotal(List<List<Integer>> triangle) {
        int high = triangle.size();
        for (int i = high - 2; i >= 0; i--) {
            List<Integer> curLevel = triangle.get(i);
            List<Integer> nextLevel = triangle.get(i + 1);
            for (int j = 0, end = i + 1; j < end; j++) {
                curLevel.set(j, curLevel.get(j) + Math.min(nextLevel.get(j), nextLevel.get(j+1)));
            }
        }
        return triangle.get(0).get(0);
    }
}
