package priv.wz.greedy;

import java.io.*;
import java.util.*;

/**
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 * 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges列表（每一个边都是一对标签）
 * 其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
 * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。
 * 在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
 * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
 * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
 */
public class FindMinHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            List<Integer> ans = new ArrayList<>(1);
            ans.add(0);
            return ans;
        }
        // 先找出度为 1 的点，标记 visit[]，
        boolean[] visit = new boolean[n];
        Set<Integer> cur = new HashSet<>(), next = new HashSet<>();
        List<Integer>[] G = new List[n];
        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            G[e[0]].add(e[1]);
            G[e[1]].add(e[0]);
        }
        for (int i = 0; i < n; i++) {
            if (G[i].size() == 1) {
                cur.add(i);
            }
        }
        int deep = 0;
        List<Integer> ans = new ArrayList<>();
        while (!cur.isEmpty()) {
            Iterator<Integer> it = cur.iterator();
            while (it.hasNext()) {
                Integer v = it.next();
                for (Integer otherv : G[v]) {
                    if (!visit[otherv] && !cur.contains(otherv)) {
                        next.add(otherv);
                    }
                }
                visit[v] = true;
            }
            deep++;
            if (next.isEmpty()) {
                ans.addAll(cur);
            }
            cur.clear();
            Set<Integer> tmp = cur;
            cur = next;
            next = tmp;
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> minHeightTrees = new FindMinHeightTrees().findMinHeightTrees(6,
                new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}});
        minHeightTrees.stream().forEach(System.out::println);
    }
}

