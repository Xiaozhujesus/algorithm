package priv.wz.dp;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */
public class TrapWater {
    /**
     * 某点可以接的雨水量由其左侧和右侧的最高点中较小者决定
     */
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int[] leftMaxHeight = new int[height.length];
        for (int i = 1; i < height.length; i++) {
            leftMaxHeight[i] = Math.max(height[i - 1], leftMaxHeight[i - 1]);
        }
        int[] rightMaxRight = new int[height.length];
        for (int i = height.length - 2; i >= 0; i--) {
            rightMaxRight[i] = Math.max(height[i + 1], rightMaxRight[i + 1]);
        }
        int ans = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(leftMaxHeight[i], rightMaxRight[i]);
            if (min > height[i]) {
                ans += (min - height[i]);
            }
        }
        return ans;
    }

    /**
     * 上面算法的优化，首先某点可以接的雨水量由其左侧和右侧的最高点中较小者决定，这点不变
     * 从上面求某点左侧和右侧最高点的过程可以看出，只要一个变量记录扫描过点的左侧的最大高度；右侧同样的道理
     * 下面是关键：
     * 在求某点可以接水的量时候，如果我们知道该点左侧的最大高度，而右侧只要发现一个点（不一定是真正的最大高度），
     * 其高度不低于左侧最高点的高度，假设为h,那么该点接水量一定由已知的左侧最高点决定，理由如下：
     * 1、因为如果该点右侧最高点高度大于等于 h,那么该点接水量一定由已知的左侧最高点决定；
     * 2、因为已经发现该点右侧有一个点高度为h，所以该点右侧最高点高度至少为 h，不可能小于 h
     * 左右是对称的，右侧同理；因此：
     * 1、当左侧最大高度大于右侧最大高度，可以得出左侧某点的接水量；
     * 2、当左侧最大高度小于右侧最大高度，可以得出右侧某点接水量
     * 3、当左右侧最大高度相等时，左右都可以得出最大接水量
     */
    public int trap2(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int leftMaxHeight = 0, rightMaxHeight = 0;
        int left = 0, right = height.length - 1;
        int ans = 0;
        while (left <= right) {
            if (leftMaxHeight <= rightMaxHeight) {
                if (leftMaxHeight > height[left]) {
                    ans += leftMaxHeight - height[left];
                } else {
                    leftMaxHeight = height[left];
                }
                left++;
            } else {
                if (rightMaxHeight > height[right]) {
                    ans += rightMaxHeight - height[right];
                } else {
                    rightMaxHeight = height[right];
                }
                right--;
            }
        }
        return ans;
    }

}
