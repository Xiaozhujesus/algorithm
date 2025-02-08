package priv.wz.greedy;

import java.util.*;

/**
 * 您将获得n个活动的开始和结束时间。 假设一个人一次只能从事一项活动，请选择一个人可以从事的最大活动数
 */
public class ActivitySelect {
    public void f(int[] start, int[] finish) {
        List<Pair> list = new ArrayList<>(start.length);
        for (int i = 0; i < start.length; i++) {
            list.add(new Pair(i, start[i],finish[i]));
        }
        Collections.sort(list, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.finish - o2.finish;
            }
        });
        List<Integer> ret = new LinkedList<>();
        /**
         * 第一个活动一定在结果里，因为假设有另一个活动集合A，A的第一个活动b不是按finish排序第一个活动a，那么
         * {A – {b}} U {a}一定也符合
         */
        int curFinish = list.get(0).finish;
        ret.add(list.get(0).index);
        for (Pair p : list) {
            if (p.start >= curFinish) {
                curFinish = p.finish;
                ret.add(p.index);
            }
        }
        for (Integer i : ret) {
            System.out.print(i+", ");
        }
    }

    class Pair {
        int index;
        int start;
        int finish;
        Pair(int index, int start, int finish) {
            this.index = index;
            this.start = start;
            this.finish = finish;
        }
    }
}
