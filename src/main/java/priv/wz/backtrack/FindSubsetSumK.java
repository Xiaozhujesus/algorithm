package priv.wz.backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非负元素组成的集合，判断集合中是否存在元素和为k的子集，集合中不存在重复元素
 *
 * 一维迷宫问题
 */
public class FindSubsetSumK {
    private List<int[]> ret = new LinkedList<>();
    private int[] arr;
    private int[] mark;

    public void f(int[] arr, int k) {
        this.arr = arr;
        this.mark = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            mark[i] = 0;
        }
        find(0, k);

    }
    //找出所有可能结果
    private void findall(int start, int left) {
        if (start == arr.length) {
            return;
        }
        if (left == 0) {
            ret.add(Arrays.copyOf(mark, mark.length));
            return;
        }

        //迭代本轮所有情况，这里就2种，mark[start]为0或者为1
        //第一种
        find(start+1, left);
        //第二种
        //判断是否合法
        if (arr[start] <= left) {
            //添加结果
            mark[start] = 1;
            left -= arr[start];
            //递归下一轮
            find(start+1, left);
            //回溯
            mark[start] = 0;
        }
    }

    //只找出一种结果即可，递归函数添加一个返回值即可
    private boolean find(int start, int left) {
        if (start == arr.length) {
            return false;
        }
        if (left == 0) {
            ret.add(Arrays.copyOf(mark, mark.length));
            return true;
        }

        if (find(start+1, left)) {
            return true;
        }
        if (arr[start] <= left) {
            mark[start] = 1;
            left -= arr[start];
            boolean tmp = find(start+1, left);
            mark[start] = 0;
            return tmp;
        }
        return false;
    }

    public static void main(String[] args) {
        FindSubsetSumK ff = new FindSubsetSumK();
        ff.f(new int[]{15, 22, 1, 52, 32, 9, 16, 8}, 53);
        if (ff.ret.size() != 0) {
            for (int[] a : ff.ret) {
                for (int i : a) {
                    System.out.print(i);
                    System.out.print(",");
                }
                System.out.println();
            }
        }
    }
}
