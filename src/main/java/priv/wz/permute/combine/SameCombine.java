package priv.wz.permute.combine;

import java.util.HashMap;
import java.util.Map;

/**
 * 判断两个排练是否是同一个组合，比如
 * [1,2] [2,1] 是相同的组合
 */
public class SameCombine {

    public boolean same(int[] a, int[] b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.length != b.length) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int cur : a) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        for (int cur : b) {
            if (!map.containsKey(cur)) {
                return false;
            }
            int c = map.get(cur);
            c--;
            if (c < 0) {
                return false;
            }
            map.put(cur, c);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new SameCombine().same(new int[]{1, 2, 2}, new int[]{2, 1, 2}));
    }
}
