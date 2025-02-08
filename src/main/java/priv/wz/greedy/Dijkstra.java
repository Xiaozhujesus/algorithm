package priv.wz.greedy;

import priv.wz.graph.Graph;

import java.util.PriorityQueue;

/**
 *
 */
public class Dijkstra {

    public int shortestPath(Graph graph, int begin, int end) {
        boolean[] visit = new boolean[graph.v];
        visit[begin] = true;
        int[] distance = new int[graph.v];
        graph.adj[begin].forEach(item -> {
            distance[item.v] = item.weight;
        });
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == 0) {
                distance[i] = Integer.MAX_VALUE;
            }
        }
        distance[begin] = 0;
        while (true) {
            int min = 0, v = begin;
            for (int i = 0; i < distance.length; i++) {
                if (!visit[i] && distance[i] < min) {
                    v = i;
                    min = distance[i];
                }
            }
            visit[v] = true;
            if (v == end) {
                return distance[v];
            }
            for (int i = 0; i < graph.adj[v].size(); i++) {
                Graph.VetexWithWeight cur = graph.adj[v].get(i);
                int tmp = cur.weight + distance[v];
                if (tmp < distance[cur.v]) {
                    distance[cur.v] = tmp;
                }
            }
        }
    }
}
