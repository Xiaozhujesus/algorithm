package priv.wz.greedy;

import java.util.*;

/**
 * 您将获得n个活动的开始和结束时间。 假设一个人一次只能从事一项活动，请选择一个人可以从事的最大活动数
 */
public class ActivitySelect {
    public void activitySelect(int[][] activities) {
        Arrays.sort(activities, (o1, o2) -> o1[1] - o2[1]);
        List<int[]> ans = new LinkedList<>();
        /**
         * 第一个活动一定在结果里，因为假设有另一个活动集合A，A的第一个活动b不是按finish排序第一个活动a，那么
         * {A – {b}} U {a}一定也符合
         */
        int curFinish = activities[0][0];
        for (int[] activity : activities) {
            if (activity[0] >= curFinish) {
                curFinish = activity[1];
                ans.add(activity);
            }
        }
    }
}
