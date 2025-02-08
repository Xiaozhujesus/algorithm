package priv.wz.array;

import java.util.*;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素
 */
public class TopKFrequent {
    public int[] f(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for (int n : map.keySet()) {
            pq.add(n);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int i = 0;
        int[] topK = new int[k];
        while (!pq.isEmpty()) {
            topK[i++] = pq.poll();
        }
        return topK;
    }
}
