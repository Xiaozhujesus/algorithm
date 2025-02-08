package priv.wz.priority.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 */
public class MaximumSwap {
    public int maximumSwap(int num) {
        int index = 0;
        int tmp = num;
        while (tmp != 0) {
            tmp /= 10;
            index++;
        }
        int[] dig = new int[index];
        tmp = num;
        while (tmp != 0) {
            dig[--index] = tmp % 10;
            tmp /= 10;
        }

        PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair a, Pair b) {
                if (a.value == b.value) {
                    return a.index - b.index;
                }
                return b.value - a.value;
            }
        });
        for (int i = 0; i < dig.length; i++) {
            queue.offer(new Pair(dig[i], i));
        }
        int a = -1, b = -1;
        for (int i = 0; i < dig.length; i++) {
            Pair cur = queue.poll();
            if (dig[i] != cur.value) {
                a = i;
                Pair pre = cur;
                while (!queue.isEmpty()) {
                    cur = queue.poll();
                    if (cur.value != pre.value) {
                        break;
                    } else {
                        pre = cur;
                    }
                }
                b = pre.index;
                break;
            }
        }
        if (a == -1) {
            return num;
        }
        tmp = dig[a];
        dig[a] = dig[b];
        dig[b] = tmp;
        tmp = 0;
        for (int i = 0; i < dig.length; i++) {
            tmp = tmp * 10 + dig[i];
        }
        return tmp;
    }

    class Pair {
        int value;
        int index;

        public Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSwap().maximumSwap(98368));
    }
}
