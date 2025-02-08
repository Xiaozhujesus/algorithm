package priv.wz.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 输出集合的所有排列，例如输出{1，2，3}的全排列
 */
public class Arrangement {
    /**
     * 这个问题可以看作有 n 个排列成一行的空格，我们需要从左往右依此填入题目给定的 n 个数，每个数只能使用一次。那么很直接的可以想到一种穷举的算法，
     * 即从左往右每一个位置都依此尝试填入一个数，看能不能填完这 n 个空格，在程序中我们可以用「回溯法」来模拟这个过程。
     * <p>
     * 我们定义递归函数 backtrack(first, output) 表示从左往右填到第 first 个位置，当前排列为 output。 那么整个递归函数分为两个情况：
     * <p>
     * 如果 first==n，说明我们已经填完了 n 个位置（注意下标从 0 开始），找到了一个可行的解，我们将 output 放入答案数组中，递归结束。
     * 如果 first<n，我们要考虑这第 first 个位置我们要填哪个数。根据题目要求我们肯定不能填已经填过的数，
     * 因此很容易想到的一个处理手段是我们定义一个标记数组 vis[] 来标记已经填过的数，那么在填第 first 个数的时候我们遍历题目给定的 n 个数，
     * 如果这个数没有被标记过，我们就尝试填入，并将其标记，继续尝试填下一个位置，即调用函数 backtrack(first + 1, output)。
     * 搜索回溯的时候要撤销这一个位置填的数以及标记，并继续尝试其他没被标记过的数。
     * 使用标记数组来处理填过的数是一个很直观的思路，但是可不可以去掉这个标记数组呢？毕竟标记数组也增加了我们算法的空间复杂度。
     * <p>
     * 答案是可以的，我们可以将题目给定的 n 个数的数组 nums[] 划分成左右两个部分，左边的表示已经填过的数，右边表示待填的数，我们在递归搜索的时候只要动态维护这个数组即可。
     * <p>
     * 具体来说，假设我们已经填到第 first 个位置，那么 nums[] 数组中 [0,first−1] 是已填过的数的集合，[first,n−1] 是待填的数的集合。我们肯定是尝试使用 [first,n−1] 里的数去填第
     * first 个数，假设待填的数的下标为 i ，那么填完以后我们将第 i 个数和第 first 个数交换，即能使得在填第 first+1个数的时候 nums[] 数组的[0,first] 部分为已填过的数，
     * [first+1,n−1] 为待填的数，回溯的时候交换回来即能完成撤销操作。
     * <p>
     * 举个简单的例子，假设我们有 [2, 5, 8, 9, 10] 这 5 个数要填入，已经填到第 3 个位置，已经填了 [8,9] 两个数，那么这个数组目前为 [8, 9 | 2, 5, 10] 这样的状态，
     * 分隔符区分了左右两个部分。假设这个位置我们要填 10 这个数，为了维护数组，我们将 2 和 10 交换，即能使得数组继续保持分隔符左边的数已经填过，右边的待填 [8, 9, 10 | 2, 5] 。
     * <p>
     * 当然善于思考的读者肯定已经发现这样生成的全排列并不是按字典序存储在答案数组中的，如果题目要求按字典序输出，那么请还是用标记数组或者其他方法。
     */

    public void arrange(char[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        util(arr, 0);
        System.out.println(new String(arr));
    }

    /**
     * 回溯，树形搜素
     *
     * @param arr
     * @param index
     */
    private void util(char[] arr, int index) {
        if (index == arr.length - 1) {
            System.out.println(new String(arr));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            util(arr, index + 1);
            swap(arr, index, i);
        }
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /**
     * 另一种思路：将排列看做数字表达，那么全排列一共有n！种，从最小的数开始，循环输出次小的，关键是找到比当前值大的紧邻当前值的下一个数，算法如下：
     * 紧邻的比当前值大的下一个值，那么要满足紧邻的条件，应该从最低位开始操作，从最低位开始，找到第一个索引i，
     * 满足arr[i]小于arr[i+1]到arr[arr.length-1]中的某个值，然后将其中第一个比arr[i]大的值与之然后交换，
     * 然后再将arr[i+1]到arr[arr.length-1]翻转，因为从上面的查找过程可以看出找arr[i]的过程，arr[i+1]到arr[arr.length-1]
     * 一定递减，满足紧邻的要求需要将其翻转
     * 例如是1243，1243->1342->1324，因此1324就是紧邻的比1243大的下一个值
     */
    public void arrange2(char[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int count = 1;
        for (int i = 1; i <= arr.length; i++) {
            count *= i;
        }
        Arrays.sort(arr);
        for (int i = 0; i < count; i++) {
            System.out.println(new String(arr));
            int j;
            for (j = arr.length - 1; j > 0; j--) {
                if (arr[j] > arr[j - 1]) {
                    break;
                }
            }
            if (j != 0) {
                // 二分查找arr[j]到arr[arr.length-1]中第一个比arr[j-1]大的位置
                int l = j, h = arr.length - 1, mid;
                while (l < h) {
                    mid = (l + h) / 2;
                    if (arr[mid] > arr[j - 1]) {
                        // 避免死循环
                        if (l == mid) {
                            break;
                        } else {
                            l = mid;
                        }
                    } else {
                        h = mid - 1;
                    }
                }
                swap(arr, j - 1, arr[h] > arr[j - 1] ? h : l);
                reverse(arr, j, arr.length - 1);
            }
        }
    }

    private void reverse(char[] arr, int from, int to) {
        while (from < to) {
            char tmp = arr[from];
            arr[from] = arr[to];
            arr[to] = tmp;
            from++;
            to--;
        }
    }

    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return list;
        }
        boolean[] vst = new boolean[str.length()];
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        char[] tmp = new char[str.length()];
        help(arr, vst, tmp, 0, list);
        return list;
    }

    public void help(char[] arr, boolean[] vst, char[] tmp, int index, ArrayList<String> list) {
        if (index == arr.length) {
            list.add(new String(tmp));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!vst[i] && !(i > 0 && arr[i] == arr[i - 1] && !vst[i - 1])) {
                tmp[i] = arr[i];
                vst[i] = true;
                help(arr, vst, tmp, index + 1, list);
                vst[i] = false;
            }
        }
    }

    /**
     * arr中可能包含重复元素，输出所有不重复的全排列
     */
    public void arrange3(char[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        boolean[] visit = new boolean[arr.length];
        Arrays.sort(arr);
        char[] res = new char[arr.length];
        util3(res, arr, visit, 0);
    }

    private void util3(char[] ans, char[] arr, boolean[] visit, int index) {
        if (index == arr.length) {
            System.out.println(new String(ans));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            /**
             * 这里最后一个条件非常重要，仔细体会，arr[i] == arr[i - 1] && !visit[i - 1] 这个条件将相邻的相同元素压缩成一个元素
             * 在填充 index 位置的元素时，只会使用连续相同元素的第一个，不会用到第二个及之后的值
             */
            if (!visit[i] && !((i > 0 && arr[i] == arr[i - 1] && !visit[i - 1]))) {
                ans[index] = arr[i];
                visit[i] = true;
                util3(ans, arr, visit, index + 1);
                visit[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        new Arrangement().arrange3("12223".toCharArray());
    }
    /**
     * 1,2,2,3,4,5求全排列，要求，4不在index3上，3和5不相邻
     * 需要将上面的arrange3修改一下
     */
}

