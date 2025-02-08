package priv.wz.dp;

import java.util.LinkedList;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少
 * <p>
 * 与硬币问题一样
 */
public class NumSquares {

    // dp[i] 表示组成正整数 i 的完全平方数的最少个数
    private int[] dp;

    public int init(int n) {
        dp = new int[n + 1];
        dp[1] = 1;
        return util(n);
    }

    private int util(int n) {
        if (n == 0) {
            return 0;
        }
        if (dp[n] == 0) {
            int max = (int) Math.floor(Math.sqrt(n));
            int least = Integer.MAX_VALUE;
            for (int i = max; i > 0; i--) {
                int tmp = util(n - i * i);
                if (tmp < least) {
                    least = tmp;
                }
            }
            dp[n] = least + 1;
        }
        return dp[n];
    }


    /**
     * 树的广度优先遍历，每一层增加一个完全平方数（Node.count字段），比如第1层是0个，
     * 第2层所有节点是1个，第3层是2个。。。；因此广度优先搜素最先找到的是一定是个数最少的
     * visited数组用来剪枝，比如 5 的可能搜素有 1 + 2*2，对应 Node{val=1,count=1};
     * 也有可能是 1 + 1*1 + 1*1 + 1*1 + 1*1，对应 Node{val=1,count=4};树的BFS先出现的 count 一定小
     * 因此后面出现的不需要入队搜素
     */
    public int tree(int n) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(new Node(n, 0));
        //
        boolean[] visited = new boolean[n + 1];
        visited[n] = true;
        while (!queue.isEmpty()) {
            Node cur = queue.removeFirst();
            for (int i = 1; ; i++) {
                int left = cur.val - i * i;
                if (left < 0) {
                    break;
                }
                if (left == 0) {
                    return cur.count + 1;
                }
                if (!visited[left]) {
                    queue.add(new Node(left, cur.count + 1));
                    visited[left] = true;
                }
            }
        }
        //到不了这
        return -1;
    }

    class Node {
        // 剩余值
        int val;
        // 目前完全平方数的个数
        int count;

        Node(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }
}
