package priv.wz.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定两个数组，编写一个函数来计算它们的交集
 * nums1 = [1,2,2,1], nums2 = [2,2]
 * 结果[2,2]
 */
public class Intersect {

    public int[] intersect(int[] a, int[] b) {
        Map<Integer, Occur> map = new HashMap<>();
        for (int i : a) {
            if (map.containsKey(i)) {
                map.get(i).first++;
            } else {
                map.put(i, new Occur());
            }
        }
        for (int i : b) {
            if (map.containsKey(i)) {
                Occur occur = map.get(i);
                if (occur.first > occur.second) {
                    occur.second++;
                }
            }
        }
        List<Integer> ret = new ArrayList<>();
        for (Map.Entry<Integer, Occur> entry : map.entrySet()) {
            int occur = entry.getValue().second;
            while (occur-- > 0) {
                ret.add(entry.getKey());
            }
        }
        int[] r = new int[ret.size()];
        int index = 0;
        for (Integer i : ret) {
            r[index++] = i;
        }
        return r;
    }

    class Occur {
        int first;
        int second;

        public Occur() {
            first = 1;
        }
    }

    public int[] f(int[] a, int[] b) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : a) {
            map.merge(i, 1, Integer::sum);
        }
        List<Integer> l = new ArrayList<>();
        for (int i : b) {
            if (map.containsKey(i)) {
                l.add(i);
                Integer freq = map.get(i);
                if (--freq == 0) {
                    map.remove(i);
                } else {
                    map.put(i, freq);
                }
            }
        }
        return l.stream().mapToInt(Integer::intValue).toArray();
    }
}
