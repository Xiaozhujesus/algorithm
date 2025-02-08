package priv.wz.tree;

import java.util.*;

/**
 * 给定一个二叉树（具有根结点 root），一个目标结点 target，和一个整数值 k 。
 * <p>
 * 返回到目标结点 target 距离为 k 的所有结点的值的列表。 答案可以以任何顺序返回。
 */
public class DistanceK {
    private List<Pair> parents = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, target, k);
        bfs(target, k, ans);
        parents.forEach(cur -> {
            if (cur.distance == k) {
                ans.add(cur.root.val);
            } else {
                bfs(cur.left ? cur.root.right : cur.root.left, k - cur.distance - 1, ans);
            }
        });
        return ans;
    }

    /**
     * 查找 root 中距离 target 的距离小于等于 k 的 parent
     */
    private int dfs(TreeNode root, TreeNode target, int k) {
        if (root == null) {
            return -1;
        }
        if (root == target) {
            return 0;
        }
        int left = dfs(root.left, target, k);
        if (left < 0) {
            int right = dfs(root.right, target, k);
            if (right >= 0) {
                int dis = right + 1;
                if (dis <= k) {
                    parents.add(new Pair(root, right + 1, false));
                }
                return dis;
            }
            return -1;
        } else {
            int dis = left + 1;
            if (dis <= k) {
                parents.add(new Pair(root, dis, true));
            }
            return dis;
        }
    }

    /**
     * 将距离 root 为 k 的节点加入 ans
     */
    private void bfs(TreeNode root, int k, List<Integer> ans) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            k--;
            int n = q.size();
            while (n-- != 0) {
                TreeNode cur = q.poll();
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
                if (k < 0) {
                    ans.add(cur.val);
                }
            }
            if (k < 0) {
                return;
            }
        }
    }

    class Pair {
        TreeNode root;
        int distance;
        boolean left;

        public Pair(TreeNode root, int distance, boolean left) {
            this.root = root;
            this.distance = distance;
            this.left = left;
        }
    }

    // 使用 map 构造 target 的所有 parent
    Map<Integer, TreeNode> parents2 = new HashMap<Integer, TreeNode>();
    List<Integer> ans = new ArrayList<Integer>();

    public List<Integer> distanceK2(TreeNode root, TreeNode target, int k) {
        // 从 root 出发 DFS，记录每个结点的父结点
        findParents(root);

        // 从 target 出发 DFS，寻找所有深度为 k 的结点
        findAns(target, null, 0, k);

        return ans;
    }

    public void findParents(TreeNode node) {
        if (node.left != null) {
            parents2.put(node.left.val, node);
            findParents(node.left);
        }
        if (node.right != null) {
            parents2.put(node.right.val, node);
            findParents(node.right);
        }
    }

    public void findAns(TreeNode node, TreeNode from, int depth, int k) {
        if (node == null) {
            return;
        }
        if (depth == k) {
            ans.add(node.val);
            return;
        }
        if (node.left != from) {
            findAns(node.left, node, depth + 1, k);
        }
        if (node.right != from) {
            findAns(node.right, node, depth + 1, k);
        }
        if (parents2.get(node.val) != from) {
            findAns(parents2.get(node.val), node, depth + 1, k);
        }
    }
}
