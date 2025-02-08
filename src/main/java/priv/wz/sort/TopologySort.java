package priv.wz.sort;

import java.util.*;

/**
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程 bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false。
 * 进一步，用数组返回一种学习顺序，可能有多种，返回一种即可，如果没有，返回空数组。
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * <p>
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成 课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 */
public class TopologySort {
    public boolean canFinish(int n, int[][] prerequisites) {
        if (n < 2) {
            return true;
        }
        //顶点入度
        int[] in = new int[n];
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < prerequisites.length; i++) {
            if (graph[prerequisites[i][1]] == null) {
                graph[prerequisites[i][1]] = new LinkedList<>();
            }
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
            in[prerequisites[i][0]]++;
        }
        // 学习顺序结果数组
        int[] sort = new int[n];
        int index = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            Integer cur = q.poll();
            sort[index++] = cur;
            if (graph[cur] != null && !graph[cur].isEmpty()) {
                for (Integer to : graph[cur]) {
                    in[to]--;
                    if (in[to] == 0) {
                        q.offer(to);
                    }
                }
            }
        }
        if (index != n) {
            return false;
        }
        return true;
    }
}
