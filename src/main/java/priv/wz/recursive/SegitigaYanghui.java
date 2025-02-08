package org.practice.recursive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行
 * 在杨辉三角中，每个数是它左上方和右上方的数的和
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class SegitigaYanghui {
    //迭代
    public List<List<Integer>> f(int high) {
        if (high == 0) {
            return Collections.EMPTY_LIST;
        }
        List<List<Integer>> ret = new LinkedList<>();
        List<Integer> last = new ArrayList<>();
        last.add(1);
        int hi = 2;
        while (hi++ <= high) {
            List<Integer> tmp = new ArrayList<>(hi);
            tmp.add(1);
            for (int i = 0, j = 1; i < hi-2; i++, j++) {
                tmp.add(last.get(i)+last.get(j));
            }
            tmp.add(1);
            ret.add(last);
            last = tmp;
        }
        ret.add(last);
        return ret;
    }

    //递归
    public List<List<Integer>> g(int high) {
        if (high == 0) {
            return Collections.EMPTY_LIST;
        }
        if (high == 1) {
            List<List<Integer>> ret = new LinkedList<>();
            List<Integer> tmp = new ArrayList<>(1);
            tmp.add(1);
            ret.add(tmp);
            return ret;
        }
        List<List<Integer>> ret = g(high-1);
        List<Integer> last = ret.get(high-2);
        List<Integer> tmp = new ArrayList<>(high);
        tmp.add(1);
        for (int i = 0, j = 1; i < high-2; i++, j++) {
            tmp.add(last.get(i)+last.get(j));
        }
        tmp.add(1);
        ret.add(tmp);
        return ret;
    }
}
