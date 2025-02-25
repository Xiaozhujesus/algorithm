package priv.wz.pointer;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水，或者说面积最大
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 */
public class MaxArea {
    public int maxArea(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        int max = 0;
        while (i < j) {
            int cur = (j - i) * Math.min(arr[i], arr[j]);
            max = Math.max(max, cur);
            //贪心
            if (arr[i] > arr[j]) {
                j--;
            } else {
                i++;
            }
        }
        return max;
    }
}

/**
 * 朴素的想法是搜索所有可能的情况，比如固定左端，然后右端一直往右，直到最后；之后左端左移一个单位，右端再一直往右。。。
 * 在上述搜索的过程中你会发现：如果左侧固定点比所有右侧都小，那么最右侧作为容器右端盛水最多，因为无论右侧多大，长方形的高都是左侧的高，这种情况
 * 下当然是长越长，面积越大，也就是最右侧的作为容器右端；
 * 因此我们可以按如下策略搜素：固定长的端，将短的向长端移动进行搜素，过程中记录最大值即可，相当于搜素过程剪枝。
 * 长的向短的移动只能减小面积，因为长必定减 1，宽可能不变也可能比原来更短；只有将短的向长的移动才有可能增加面积，
 * 当且仅当下一个线段比当前短边长
 */
