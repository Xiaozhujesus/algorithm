package org.practice.array;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * <p>
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * <p>
 * 说明: 
 * <p>
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例 1:
 * <p>
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * <p>
 * 输出: 3
 * <p>
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 */

public class CompleteCircuit {
    public int f(int[] gas, int[] cost) {
        int start = 0, acc = 0, total = 0;
        for (int i = 0; i < gas.length; i++) {
            acc += (gas[i] - cost[i]);
            if (acc < 0) {
                total += acc;
                acc = 0;
                start = i + 1;
            }
        }
        if (acc + total >= 0) {
            return start;
        } else {
            return -1;
        }
    }
}

/**
 * 比如，A,B,C,D,E,F,G 几个站， 以 B 站为起点， travel 到 F 站时， 汽油不够。 那么我们就可以确定分别以 B, C, D, E 几个站为起点，
 * 都到达不了 F 站。 因为既然 B 站能到达 C, D, E 几个站， 那么到达的时候，汽油量一定是大于等于 0 的。这时候再从 C, D, E 出发，
 * 汽油量实际上大于等于直接从这几个站出发的汽油量。因此，既然从 B 站到不了 F 站出发， 那从 C, D, E 出发也到不了 F 站。因此，
 * 我们可以直接跳过 C, D, E 这几个站，而直接以 F 站为起点
 *
 * gas-cost为积累量，积累量为正说明可以到达，为负说明不可以
 * 相当于在一个积累量的数组里从一个点开始找连续序列和为正的序列，则转换成在序列里找很多和为正的子序列且总和要大于0
 */