package org.practice.array;

/**
 * 寻找两个有序数组的中位数
 * https://zhuanlan.zhihu.com/p/70654378
 */
public class MidNumOfTwoSortedArr {
    public double f(int[] a, int[] b) {
        // a设置为短的
        if (a.length > b.length) {
            int[] tmp = a;
            a = b;
            b = tmp;
        }
        int m = a.length;
        int n = b.length;
        int l = 0;
        int h = m;
        int half = (m + n + 1) / 2;
        while (l <= h) {
            int i = (l + h) / 2;
            int j = half - i;
            if (i < m && b[j - 1] > a[i]) {
                // i 的值太小， 增加它
                l = i + 1;
            } else if (i > 0 && a[i - 1] > b[j]) {
                // i 的值过大， 减小它
                h = i - 1;
            } else {
                // i is perfect
                int leftMax;
                if (i == 0) {
                    leftMax = b[j - 1];
                } else if (j == 0) {
                    leftMax = a[i - 1];
                } else {
                    leftMax = Math.max(a[i - 1], b[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return leftMax;
                }
                int rightMin;
                if (i == m) {
                    rightMin = b[j];
                } else if (j == n) {
                    rightMin = a[i];
                } else {
                    rightMin = Math.min(a[i], b[j]);
                }
                return ((double) (leftMax + rightMin)) / 2;
            }
        }
        // 不会到这
        return -1;
    }
}


/**
 * 将 left_A 和 left_B 放入同一个集合，将 right_A 和 right_B 放入另外一个集合。 分别称他们为 left_part 和 right_part ：
 * <p>
 * left_part          |        right_part
 * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
 * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
 * <p>
 * <p>
 * 如果我们能达成这两个条件：
 * <p>
 * 1) len(left_part) == len(right_part)
 * 2) max(left_part) <= min(right_part)
 * <p>
 * 为了达成这两个条件，我们只需要确保：
 * <p>
 * (1) i + j == m - i + n - j (或者: m - i + n - j + 1) 即让左半边元素数量等于与右半边
 * 对于 n >= m 的情况，我们只需要让 : i = 0 ~ m, j = (m + n + 1)/2 - i
 * (2) B[j-1] <= A[i] 并且 A[i-1] <= B[j]  即让左边最大元素小于右边最小元素
 * <p>
 * ps. 简单起见，我们先假设 A[i-1],B[j-1],A[i],B[j] 总是可用的，即使 i=0/i=m/j=0/j=n 。 后面我会说怎么处理这些边缘情况。
 * <p>
 * pps. 为何 n >= m？ 因为我必须确保 j 是非负的，因为 0 <= i <= m 并且 j = (m + n + 1)/2 - i。 如果 n < m ， 则 j 可能是负值， 这将导致错误的结果。
 * <p>
 * 所以，我们需要做的就是：
 * <p>
 * 在 [0, m] 中找到一个使下面不等式成立的 i :
 * B[j-1] <= A[i] and A[i-1] <= B[j], ( where j = (m + n + 1)/2 - i )
 *
 * <1> 设 imin = 0, imax = m, 然后在这个区间 [imin, imax] 中查找 i
 *
 * <2> 设 i = (imin + imax)/2, j = (m + n + 1)/2 - i
 *
 * <3> 此时，我们满足了 len(left_part)==len(right_part)， 我们会遇到三种情况：
 * <a> B[j-1] <= A[i] and A[i-1] <= B[j]
 * 说明我们找到了我们需要的i，停止搜索。
 * <b> B[j-1] > A[i]
 * 意味着 A[i] 太小， 那么我们必须调整 i 以使 `B[j-1] <= A[i]` 仍然成立。
 * 我们可以增大 i吗?
 * Yes. 因为 i 增大时， j 将减小。
 * 所以 B[j-1] 跟着减小而 A[i] 会增大。`B[j-1] <= A[i]`就可能成立。
 * 我们可以减小 i 吗?
 * No!  因为 i 减小时， j 将增大。
 * 所以 B[j-1] 增大而 A[i] 减小。B[j-1] <= A[i] 永远不可能成立。
 * 所以我们必须增加 i。也就是将搜索范围调整为[i+1, imax]。 所以，设 imin = i+1, 然后回到步骤 <2>.
 * <c> A[i-1] > B[j]
 * 意味着 A[i-1] 太大。我们必须减小 i 以使 `A[i-1]<=B[j]`.
 * 就是说我们要调整搜索范围为 [imin, i-1].
 * 所以， 设 imax = i-1, 然后回到步骤 <2>.
 * <p>
 * <p>
 * 找到符合条件的 i 之后，我们想要的中位数就是：
 * <p>
 * max(A[i-1], B[j-1]) ( m + n 是奇数)
 * 或者 (max(A[i-1], B[j-1]) + min(A[i], B[j]))/2 ( m + n 是偶数)
 * <p>
 * <p>
 * 现在让我们考虑边缘值i = 0，i = m，j = 0，j = n，其中A [i-1]，B [j-1]，A [i]，B [j]可能不存在。 实际上这种情况比你想象的要容易。
 * <p>
 * 我们需要做的是确保 max(left_part) <= min(right_part)。 所以， 如果 i 和 j 不是边缘值(意味着 A[i-1],B[j-1],A[i],B[j] 都存在)， 那么我们必须同时检查 B[j-1] <= A[i] 和 A[i-1] <= B[j]. 但是如果 A[i-1],B[j-1],A[i],B[j] 中某些值不存在， 那么我们可以只检查一个条件（甚至都不检查）。例如， 如果 i=0， 那么 A[i-1] 不存在， 也就意味着我们不用检查 A[i-1] <= B[j]。 所以，我们这样做：
 * <p>
 * 在 [0, m] 中找到一个使下面不等式成立的 i :
 * (j == 0 or i == m or B[j-1] <= A[i]) and
 * (i == 0 or j == n or A[i-1] <= B[j])
 * where j = (m + n + 1)/2 - i
 * <p>
 * <p>
 * 在搜索循环中，我们只会遇到三种情况：
 *
 * <a> (j == 0 or i == m or B[j-1] <= A[i]) and
 * (i == 0 or j = n or A[i-1] <= B[j])
 * 说明 i 的值满足要求，停止循环
 *
 * <b> j > 0 and i < m and B[j - 1] > A[i]
 * 说明 i 的值太小， 增加它.
 *
 * <c> i > 0 and j < n and A[i - 1] > B[j]
 * 说明 i 的值过大， 减小它。
 * <p>
 * 有人指出，i < m ==> j > 0 and i > 0 ==> j < n，所以对于情况<b> 和 <c>， 我们不需要检查j > 0 和j < n是否满足
 */