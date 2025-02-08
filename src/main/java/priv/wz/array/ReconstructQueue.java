package priv.wz.array;

/**
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * <p>
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue
 * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 * <p>
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 */
public class ReconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        quickSort(people, 0, people.length - 1);
        insertSort(people);
        return people;
    }

    /**
     * h 从高到低，h 相同，k 大的在后面
     */
    private void quickSort(int[][] people, int left, int right) {
        if (left >= right) {
            return;
        }
        int first = left - 1, second = left;
        while (second != right) {
            if (!le(people[second], people[right])) {
                first++;
                swap(people, first, second);
            }
            second++;
        }
        first++;
        swap(people, first, second);
        quickSort(people, left, first - 1);
        quickSort(people, first + 1, right);
    }

    private void insertSort(int[][] people) {
        for (int i = 0; i < people.length; i++) {
            int sub = i - people[i][1];
            if (sub != 0) {
                int j = i;
                int[] cur = people[i];
                while (sub-- != 0) {
                    people[j] = people[j - 1];
                    j--;
                }
                people[j] = cur;
            }
        }
    }

    /**
     * a 是否应排在 b 后面
     */
    private boolean le(int[] a, int[] b) {
        if (a[0] < b[0]) {
            return true;
        }
        if (a[0] == b[0]) {
            return a[1] > b[1];
        }
        return false;
    }

    private void swap(int[][] people, int a, int b) {
        int[] tmp = people[a];
        people[a] = people[b];
        people[b] = tmp;
    }

    public static void main(String[] args) {
        int[][] p = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        new ReconstructQueue().reconstructQueue(p);
    }
}
