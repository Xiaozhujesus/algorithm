package priv.wz.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按任何顺序返回答案。
 */
public class Combine {
    private boolean[] visit;
    private int k;
    private List<List<Integer>> ans;
    private List<Integer> cur;

    public List<List<Integer>> combine(int n, int k) {
        if (n * k == 0) {
            return Collections.EMPTY_LIST;
        }
        visit = new boolean[n];
        this.k = k;
        ans = new ArrayList<>();
        cur = new ArrayList<>(k);
        bt(k, 0);
        return ans;
    }

    void bt(int wanted, int index) {
        if (wanted == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        if (index == k) {
            return;
        }
    }
}
