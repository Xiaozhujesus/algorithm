package priv.wz.array;

/**
 * 给定一个无序整型数组arr，找到数组中未出现的最小正整数。要求时间复杂度为O(N)空间复杂度为O(1)。
 * <p>
 * arr=[-1,2,3,4]。返回1。
 * <p>
 * arr=[1,2,3,4]。返回5。
 */
public class MinMissNum {
    public int f(int[] arr) {
        int n = arr.length;
        int[] mark = new int[n];
        for (int i = 0; i < n; i++) {
            // 对应位置上标记1
            if (arr[i] > 0 && arr[i] <= n) {
                mark[arr[i] - 1] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (mark[i] == 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    // 对上面的改进，空间复杂度不满足，只能在原来的数组上操作了
    public int g(int[] arr, int n) {
        int l = 0;
        int r = n;

        // l之前都是处理过而且在正确位置上的
        while (l < r) {

            if (arr[l] == l + 1) {//在理想的位置
                l++;
            } else if (arr[l] > r  // arr[l]当作与arr[l]>n一样处理，原因见下面注释
                    || arr[l] <= l //arr[l]已经处理过了，在正确的位置上；或者是负数
                    || arr[l] == arr[arr[l] - 1]) { //arr[l]未处理过（在l和r之间的位置），但已经在正确的位置上
                arr[l] = arr[--r]; //上面3种情况都是相当于将arr[l]的数据丢掉，然后从后面取一个元素，从后往前处理，当r减小到与l一样时候，l
                // 的位置还没有找到填充的值，这个位置就是结果；至于为啥当arr[l]在r和n之间时候，不将arr[l]
                // swap到正确位置而是直接与arr[l]>n一起处理，是因为没有必要，当循环条件l==r时候已经结束，找到答案，r后面是啥根本不关心，
                // 我们只是要找第一个没有填充的位置（当数组都处理过一遍的时候，l==r时候已经处理一遍了）
            } else {//合法但是没有在理想的位置上
                swap(arr, l, arr[l] - 1);
            }
        }

        return l + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
