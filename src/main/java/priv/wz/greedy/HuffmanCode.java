package priv.wz.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 哈夫曼编码
 */
public class HuffmanCode {
    public void f(char[] c, int[] freq) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(c.length, new Comparator<HuffmanNode>() {
            @Override
            public int compare(HuffmanNode o1, HuffmanNode o2) {
                return o2.freq - o1.freq;
            }
        });
        for (int i = 0; i < c.length; i++) {
            pq.add(new HuffmanNode(c[i], freq[i]));
        }
        while (pq.size() > 1) {
            HuffmanNode x = pq.poll();
            HuffmanNode y = pq.poll();
            HuffmanNode tmp = new HuffmanNode('-', x.freq + y.freq);
            tmp.left = x;
            tmp.right = y;
            pq.add(tmp);
        }
        printHuffmanCode(pq.peek(), "");
    }

    private void printHuffmanCode(HuffmanNode root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ":" + s);
            return;
        }

        // if we go to left then add "0" to the code.
        // if we go to the right add"1" to the code.

        // recursive calls for left and
        // right sub-tree of the generated tree.
        printHuffmanCode(root.left, s + "0");
        printHuffmanCode(root.right, s + "1");
    }

    class HuffmanNode {
        char c;
        int freq;
        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }

    public static void main(String[] args) {
        new HuffmanCode().f(new char[]{'a', 'b', 'c'}, new int[]{1, 2, 3});
    }
}
