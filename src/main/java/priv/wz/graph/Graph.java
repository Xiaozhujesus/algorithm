package priv.wz.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 图的存储，有向图无向图都可以，带权不带权都可以，不带权只需将 weight 设置为-1即可
 */
public class Graph {
    public int v;
    public List<VetexWithWeight>[] adj;

    public Graph(int v) {
        this.v = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int i, int j, int weight) {
        adj[i].add(new VetexWithWeight(j, weight));
    }

    public static class VetexWithWeight {

        public int v;
        public int weight;

        public VetexWithWeight(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
}

