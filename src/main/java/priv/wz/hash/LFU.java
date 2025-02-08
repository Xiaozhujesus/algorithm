package priv.wz.hash;

import java.util.*;

/**
 * 请你为最不经常使用（LFU）缓存算法设计并实现数据结构。它应该支持以下操作：get 和 put。
 *
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。
 * 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除最久未使用的键。
 * 项的使用次数就是自插入该项以来对其调用 get 和 put 函数的次数之和。
 *
 */
public class LFU {

    static class Entry {
        String key;
        int val;
        int freq;

        public Entry(String key, int val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
        }
    }

    static class LFUCache {
        private Map<String, Entry> cache;
        private Map<Integer, LinkedHashSet<Entry>> freq;
        private int capacity;
        private int min;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>();
            freq = new HashMap<>();
        }

        public void put(String key, int val) {
            Entry cur = cache.get(key);
            if (cur == null) {
                cur = new Entry(key, val, 1);
                if (capacity == cache.size()) {
                    Entry rm = freq.get(min).iterator().next();
                    freq.get(min).remove(rm);
                    cache.remove(rm.key);
                }
                cache.put(key, cur);
                LinkedHashSet<Entry> tmp = freq.get(1);
                if (tmp == null) {
                    tmp = new LinkedHashSet<>();
                    freq.put(1, tmp);
                }
                tmp.add(cur);
                min = 1;
            } else {
                cur.val = val;
                incFreq(cur);
            }
        }

        public int get(int key) {
            Entry cur = cache.get(key);
            if (cur == null) {
                return -1;
            } else {
                incFreq(cur);
                return cur.val;
            }
        }

        private void incFreq(Entry entry) {
            LinkedHashSet<Entry> set = freq.get(entry.freq);
            set.remove(entry);
            if (set.isEmpty()) {
                min = entry.freq + 1;
            }
            entry.freq++;
            LinkedHashSet<Entry> next = freq.get(entry.freq);
            if (next == null) {
                next = new LinkedHashSet<>();
                freq.put(entry.freq, next);
            }
            next.add(entry);
        }
    }
    /**
     * 若opt=1，接下来两个整数x, y，表示set(x, y)
     * 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
     */

    public static void main(String[] args) {
        int[][] ops = {{1, 1, 1}, {1, 2, 2}, {1, 3, 2}, {1, 2, 4}, {1, 3, 5}, {2, 2}, {1, 4, 4}, {2, 1}};
        int capacity = 3;

        LFUCache cache = new LFUCache(capacity);
        List<Integer> res = new ArrayList<>();
//        for (int[] i : ops) {
//            if (i.length == 3) {
//                cache.put(i[1], i[2]);
//            } else {
//                res.add(cache.get(i[1]));
//            }
//        }
        int[] ints = res.stream().mapToInt(i -> i).toArray();
        for (int i : ints) {
            System.out.println(i);
        }
    }
}