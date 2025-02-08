package priv.wz.recursive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行
 * 在杨辉三角中，每个数是它左上方和右上方的数的和
 */
public class SegitigaYanghui2 {
    //迭代
    public List<Integer> f(int high) {
        if (high == 0) {
            return Collections.EMPTY_LIST;
        }
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
            last = tmp;
        }
        return last;
    }

    //递归
    public List<Integer> g(int high) {
        if (high == 0) {
            return Collections.EMPTY_LIST;
        }
        if (high == 1) {
            List<Integer> tmp = new ArrayList<>(1);
            tmp.add(1);
            return tmp;
        }
        List<Integer> last = g(high-1);
        List<Integer> tmp = new ArrayList<>(high);
        tmp.add(1);
        for (int i = 0, j = 1; i < high-2; i++, j++) {
            tmp.add(last.get(i)+last.get(j));
        }
        tmp.add(1);
        return tmp;
    }

    //你可以优化你的算法到 O(k) 空间复杂度吗
    public List<Integer> y(int high) {
        if (high == 0) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> ret = new ArrayList<>(high);
        ret.add(1);
        int hi = 2;
        int ptr, sum, last;
        while (hi <= high) {
            ptr = 1;
            last = 1;
            while (ptr < hi-1) {
                sum = last + ret.get(ptr);
                last = ret.get(ptr);
                ret.set(ptr, sum);
                ptr++;
            }
            ret.add(1);
            hi++;
        }
        return ret;
    }
}
