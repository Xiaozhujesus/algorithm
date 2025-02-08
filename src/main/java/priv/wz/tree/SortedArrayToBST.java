package priv.wz.tree;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 */
public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return build(nums, 0, nums.length);
    }

    private TreeNode build(int[] arr, int low, int high) {
        if (low == high) {
            return null;
        }
        if (low == high + 1) {
            return new TreeNode(arr[low]);
        }
        int mid = (low + high) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = build(arr, low, mid);
        root.right = build(arr, mid + 1, high);
        return root;
    }
}
