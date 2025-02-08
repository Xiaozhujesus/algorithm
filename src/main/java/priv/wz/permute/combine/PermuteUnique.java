package priv.wz.permute.combine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序返回所有不重复的全排列。
 */
public class PermuteUnique {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        return util(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    // 递归，arr 有序，返回 arr 的全排列
    private List<List<Integer>> util(List<Integer> arr) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr.size() == 1) {
            ans.add(arr);
            return ans;
        }
        for (int i = 0; i < arr.size(); i++) {
            // 这里其实就是通用排列中所说的：排列的关键是要保证每次循环位置 i 的元素不同，这样结果才能不同
            if (i == 0 || !arr.get(i).equals(arr.get(i - 1))) {
                int first = arr.get(i);
                List<Integer> tmp = new ArrayList<>(arr);
                tmp.remove(i);
                List<List<Integer>> sub = util(tmp);
                for (List<Integer> item : sub) {
                    item.add(0, first);
                }
                ans.addAll(sub);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new PermuteUnique().permuteUnique(new int[]{1, 2, 2, 2}));
    }


    /**
     * 给定一个不含重复数字的数组 nums ，返回其所有可能的全排列
     */
    // visit 数组相当于通用排列中记录每个元素剩余多少，若允许数组中包含重复元素，还需要先对数组排序
    private boolean[] visit;
    private int[] item;
    private int[] cur;
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        if (nums == null || nums.length == 0) {
            return ans;
        }
        this.item = nums;
        this.cur = new int[nums.length];
        this.visit = new boolean[nums.length];
        backtrace(0);
        return ans;
    }

    private void backtrace(int i) {
        if (i == item.length) {
            ans.add(Arrays.stream(cur).boxed().collect(Collectors.toList()));
            return;
        }
        for (int j = 0; j < item.length; j++) {
            if (!visit[j]) {
                cur[i] = item[j];
                visit[j] = true;
                backtrace(i + 1);
                visit[j] = false;
            }
        }
    }

    /**
     * 数组中元素不同时，swap 也可以，相当于数组本身就是 visit 数组，加上当前处理位置 index 就可以保证不重复
     */
    public List<List<Integer>> permute2(int[] nums) {
        util(nums, 0);
        return ans;
    }

    private void util(int[] nums, int index) {
        if (index == nums.length) {
            ans.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            util(nums, index + 1);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}