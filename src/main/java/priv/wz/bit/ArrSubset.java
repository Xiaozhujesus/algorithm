package org.practice.bit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 拿res中已经存在的元素和新的组合，然后重新放入res中，先给res中放入一个空元素，然后通过空元素和S中第一个元素结合放入res中，以此类推，
 * 如：[1,2,3]，最开始是空集，那么我们现在要处理1，就在空集上加1，为[1]，结果中位[]和[1]，下面处理2，在之前的子集基础上，每个都加个2，
 * 可以分别得到[2]，[1, 2]，那么现在所有的子集合为[], [1], [2], [1, 2]，同理处理3的情况可得[3], [1, 3], [2, 3], [1, 2, 3],
 * 再加上之前的子集就是所有的子集合了
 */
public class ArrSubset {
    public ArrayList<ArrayList<Integer>> subsets(int[] s) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (s == null || s.length == 0) {
            return list;
        }
        ArrayList<ArrayList<Integer>> acc = new ArrayList<>((int) Math.pow(2, s.length));
        acc.add(new ArrayList<Integer>());
        Arrays.sort(s);
        for (int i = 0; i < s.length; i++) {
            for (int j = 0, end = acc.size(); j < end; j++) {
                ArrayList<Integer> tmp = (ArrayList<Integer>) acc.get(j).clone();
                tmp.add(s[i]);
                acc.add(tmp);
            }
        }
        return acc;
    }


    // 位运算
    public ArrayList<ArrayList<Integer>> subsets2(int[] s) {
        int n = s.length;
        int max = 1 << n;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>(n);
        for (int i = 0; i < max; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            int idx = 0;
            int j = i;
            while (j > 0) {
                //判断最后一位是否为1，若为1则将对应数加入到当前组合中
                if ((j & 1) == 1) {
                    tmp.add(s[idx]);
                }
                idx++;
                //判断了这一位是否为1后要右移
                j = j >> 1;
            }
            //判断完了一种组合，加入到结果集中
            result.add(tmp);
        }
        return result;
    }
}
