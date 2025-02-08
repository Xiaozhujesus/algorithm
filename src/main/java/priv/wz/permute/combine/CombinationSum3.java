package priv.wz.permute.combine;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * 只使用数字1到9每个数字 最多使用一次返回所有可能的有效组合的列表 。
 * 该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 * <p>
 * 示例 1:
 * <p>
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 解释:
 * 1 + 2 + 4 = 7
 * 没有其他符合的组合了。
 */
public class CombinationSum3 {
    List<Integer> cur;
    List<List<Integer>> ans;
    int curSum;

    public List<List<Integer>> combinationSum3(int k, int n) {
        cur = new ArrayList<>();
        ans = new ArrayList<>();
        dfs(1, k, n);
        return ans;
    }

    /**
     *
     * @param index 当前值，分两种情况：包含和不包含
     * @param k
     * @param sum
     */
    void dfs(int index, int k, int sum) {
        if (index > 9 || cur.size() == k || curSum + index > sum) {
            return;
        }
        // 包含
        if (curSum + index == sum && cur.size() + 1 == k) {
            cur.add(index);
            ans.add(new ArrayList<>(cur));
            cur.remove(cur.size() - 1);
            return;
        }
        cur.add(index);
        curSum += index;
        dfs(index + 1, k, sum);
        cur.remove(cur.size() - 1);
        curSum -= index;
        // 不包含
        dfs(index + 1, k, sum);
    }
}
