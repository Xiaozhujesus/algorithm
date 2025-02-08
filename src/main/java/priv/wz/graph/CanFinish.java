package priv.wz.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi]
 * 表示如果要学习课程 ai 则必须先学习课程 bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则返回 false 。
 */
public class CanFinish {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[] finish = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            finish[i] = true;
        }
        for (int i = 0; i < prerequisites.length; i++) {
            finish[prerequisites[i][0]] = false;
        }
        boolean[] learn = new boolean[numCourses];
        getLearn(prerequisites, finish, learn);

        while (canLearn(learn)) {
            learn(finish, learn);
            reset(learn);
            getLearn(prerequisites, finish, learn);
        }
        for (int i = 0; i < finish.length; i++) {
            if (!finish[i]) {
                return false;
            }
        }
        return true;
    }

    private void learn(boolean[] finish, boolean[] learn) {
        for (int i = 0; i < finish.length; i++) {
            if (learn[i]) {
                finish[i] = true;
            }
        }
    }

    private void getLearn(int[][] prerequisites, boolean[] finish, boolean[] learn) {
        for (int i = 0; i < prerequisites.length; i++) {
            if (prerequisites[i][1] >= 0) {
                if (finish[prerequisites[i][1]]) {
                    // 清理依赖关系
                    prerequisites[i][1] = -1;
                    // 可执行的备选
                    learn[prerequisites[i][0]] = true;
                }
            }
        }
        for (int i = 0; i < prerequisites.length; i++) {
            if (prerequisites[i][1] >= 0) {
                if (!finish[prerequisites[i][1]]) {
                    // 仍有阻塞，删除备选
                    learn[prerequisites[i][0]] = false;
                }
            }
        }
    }

    private boolean canLearn(boolean[] learn) {
        for (int i = 0; i < learn.length; i++) {
            if (learn[i]) {
                return true;
            }
        }
        return false;
    }

    private void reset(boolean[] learn) {
        for (int i = 0; i < learn.length; i++) {
            learn[i] = false;
        }
    }


    // BFS
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        // Get the indegree and adjacency of every course.
        for (int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }
        // Get all the courses with the indegree of 0.
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }
        // BFS TopSort.
        while (!queue.isEmpty()) {
            int pre = queue.remove();
            numCourses--;
            for (int cur : adjacency.get(pre)) {
                if (--indegrees[cur] == 0) {
                    queue.add(cur);
                }
            }
        }
        return numCourses == 0;
    }

    // DFS
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        // -1 表示已经检测过，1 表示 DFS 已经走过
        int[] flags = new int[numCourses];
        for (int[] cp : prerequisites) {
            adjacency.get(cp[1]).add(cp[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adjacency, flags, i)) {
                return false;
            }
        }
        return true;
    }

    //提克特斯 688
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        // 走过，说明有环
        if (flags[i] == 1) return false;
        // 检测过并且无环
        if (flags[i] == -1) return true;
        flags[i] = 1;
        for (Integer j : adjacency.get(i)) {
            if (!dfs(adjacency, flags, j)) {
                return false;
            }
        }
        flags[i] = -1;
        return true;
    }
}