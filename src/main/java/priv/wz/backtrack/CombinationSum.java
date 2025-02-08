package priv.wz.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出一组候选数C和一个目标数T，找出候选数中和等于T的所有组合。
 * C 中的每个数字在一个组合中只能使用一次。
 * 注意：
 * 题目中所有的数字（包括目标数T ）都是正整数
 * 组合中的数字, 要按非递增排序
 * 结果中不能包含重复的组合
 * 例如：给定的候选数集是[100,10,20,70,60,10,50]，目标数是80
 * 解集是：
 * [10, 70]
 * [10, 20, 50]
 * [20, 60]
 * [10, 10, 60]
 *
 * 在 search 目录下也有一道题，与本题稍微有点不同
 */
public class CombinationSum {
    private List<List<Integer>> ans = new ArrayList<>();

    public List combinationSum(int[] num, int target) {
        if (num == null || num.length == 0) {
            return ans;
        }
        Arrays.sort(num);
        List<Integer> tmp = new ArrayList<>();
        util(num, 0, target, tmp);
        return ans;
    }

    private void util(int[] arr, int start, int target, List<Integer> l) {
        if (target == 0) {
            List<Integer> tmp = new ArrayList<>(l.size());
            tmp.addAll(l);
            ans.add(tmp);
            return;
        }
        for (int i = start; i < arr.length; i++) {
            // 去重，考虑没有下面这句，会是什么样子
            if (i > start && arr[i] == arr[i - 1]) {
                continue;
            }
            if (arr[i] <= target) {
                l.add(arr[i]);
                util(arr, i + 1, target - arr[i], l);
                l.remove(l.size() - 1);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> res = new CombinationSum().combinationSum(new int[]{100, 10, 20, 70, 60, 10, 50}, 80);
//        List<List<Integer>> res = new CombinationSum().combinationSum(new int[]{10, 10, 10}, 30);
//        for (List<Integer> i : res) {
//            for (Integer j : i) {
//                System.out.print(j + ",");
//            }
//            System.out.println("----");
//        }
        System.out.println("");
    }
}
