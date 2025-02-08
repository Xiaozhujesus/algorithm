package priv.wz.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * <p>
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * 输出：2
 * 解释：
 * 两个元组如下：
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 * <p>
 * 示例 2：
 * 输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * 输出：1
 */
public class FourNumSum2 {
    private List<int[]> notZero = new ArrayList<>(4);
    private int zeroArrs;

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        if (nums1 == null || nums1.length == 0) {
            return 0;
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Arrays.sort(nums3);
        Arrays.sort(nums4);
        int ret = 0;
        int len = nums1.length;

        preProcess(nums1);
        preProcess(nums2);
        preProcess(nums3);
        preProcess(nums4);

        // 处理 notZero 列表中的数组，循环层数变化
        f1:
        for (int i = 0; i < len; i++) {
            int sum1 = nums1[i];
            f2:
            for (int j = 0; j < len; j++) {
                int sum2 = sum1 + nums2[j];
                f3:
                for (int k = 0; k < len; k++) {
                    int sum3 = sum2 + nums3[k];
                    f4:
                    for (int l = 0; l < len; l++) {
                        int sum4 = sum3 + nums4[l];
                        if (sum4 == 0) {
                            ret++;
                        } else if (sum4 > 0) {
                            if (l > 0) {
                                break;
                            } else {
                                // l == 0,向上找第一个索引不为 0 的行，从其上2行开始循环
                                if (k > 0) {
                                    break f3;
                                }
                                // l == 0 && k == 0
                                if (j > 0) {
                                    break f2;
                                }
                                // l == 0 && k == 0 && j == 0
                                break f1;
                            }
                        }
                    }
                }
            }
        }
        int multi = 1;
        while (zeroArrs-- > 0) {
            multi *= len;
        }
        return ret * multi;
    }

    private boolean firstLastZero(int[] nums) {
        return nums[0] == 0 && nums[nums.length - 1] == 0;
    }

    private void preProcess(int[] nums) {
        if (firstLastZero(nums)) {
            zeroArrs++;
        } else {
            notZero.add(nums);
        }
    }

    public static void main(String[] args) {
        int[] n1 = {1, 2};
        int[] n2 = {-2, -1};
        int[] n3 = {-1, 2};
        int[] n4 = {0, 2};

        System.out.println(new FourNumSum2().fourSumCount(n1, n2, n3, n4));
    }
}
