package priv.wz.tree;

/**
 * 有两位极客玩家参与了一场「二叉树着色」的游戏。游戏中，给出二叉树的根节点 root，树上总共有 n 个节点
 * 且 n 为奇数，其中每个节点上的值从 1 到 n 各不相同。
 * 游戏从「一号」玩家开始（「一号」玩家为红色，「二号」玩家为蓝色），最开始时，
 * 「一号」玩家从 [1, n]中取一个值 x（1 <= x <= n）；
 * 「二号」玩家也从[1, n]中取一个值 y（1 <= y <= n）且 y != x。
 * 「一号」玩家给值为x的节点染上红色，而「二号」玩家给值为y的节点染上蓝色。
 * 之后两位玩家轮流进行操作，每一回合，玩家选择一个他之前涂好颜色的节点，将所选节点一个未着色的邻节点（即左右子节点、或父节点）进行染色。
 * 如果当前玩家无法找到这样的节点来染色时，他的回合就会被跳过。
 * 若两个玩家都没有可以染色的节点时，游戏结束。着色节点最多的那位玩家获得胜利。
 * 现在，假设你是「二号」玩家，根据所给出的输入，若存在一个 y 值可以确保你赢得这场游戏，则返回true；若无法获胜，就请返回 false。
 */
public class BtreeGameWinningMove {
    private int left;
    private int right;


    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        nodeNums(root, x);
        int up = n - left - right - 1;
        n /= 2;
        // 分别比较 x 节点左子树、右子树、parent 子树集合
        return left > n || right > n || up > n;
    }

    // 找到以 root 为根的树有多少个节点，若包含以节点 x 为根的子树，记录其节点数
    private int nodeNums(TreeNode root, int x) {
        if (root == null) {
            return 0;
        }
        int left = nodeNums(root.left, x);
        int right = nodeNums(root.right, x);
        if (root.val == x) {
            this.left = left;
            this.right = right;
        }
        return 1 + left + right;
    }

}
