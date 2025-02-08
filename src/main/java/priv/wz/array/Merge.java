package priv.wz.array;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素
 */
public class Merge {
    public void solution(int[] num1, int m, int[] num2, int n) {
        if (n == 0) {
            return;
        }
        int i = m - 1, j = n - 1;
        for (int cur = num1.length-1; cur >= 0; cur--) {
            if (i >= 0 && j >= 0) {
                if (num1[i] > num2[j]) {
                    num1[cur] = num1[i];
                    i--;
                } else {
                    num1[cur] = num2[j];
                    j--;
                }
            } else {
                if (i < 0) {
                    while (j >= 0) {
                        num1[cur] = num2[j];
                        j--;
                        cur--;
                    }
                }
                break;
            }
        }
    }
}
