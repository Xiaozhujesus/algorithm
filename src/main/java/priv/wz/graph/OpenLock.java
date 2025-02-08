package priv.wz.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 */
public class OpenLock {
    /**
     * 四维空间最短路径，与棋盘上两点最短路径一样，相当于求图中两点最短路径，只不过相邻两点距离一样，都是 1，由于距离一样可以用 BFS
     * 若相邻两点距离不同那么就是 Dijkstra 算法
     */
    public int openLock(String[] deadends, String target) {
        int ans = 0;
        Set<String> ds = Arrays.stream(deadends).collect(Collectors.toSet());
        if (ds.contains("0000")) {
            return -1;
        }
        Set<String> visited = new HashSet<>();
        visited.add("0000");
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        while (!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            while (size-- != 0) {
                String cur = queue.poll();
                if (cur.equals(target)) {
                    return ans;
                }
                for (String s : around(cur)) {
                    if (!ds.contains(s) && !visited.contains(s)) {
                        visited.add(s);
                        queue.offer(s);
                    }
                }
            }
        }
        return -1;
    }

    private char numPrev(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    private char numSucc(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }

    // 枚举 status 通过一次旋转得到的数字
    private List<String> around(String status) {
        List<String> ret = new ArrayList<String>();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char num = array[i];
            array[i] = numPrev(num);
            ret.add(new String(array));
            array[i] = numSucc(num);
            ret.add(new String(array));
            array[i] = num;
        }
        return ret;
    }
}
