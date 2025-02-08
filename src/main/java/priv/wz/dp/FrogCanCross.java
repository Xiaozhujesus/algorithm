package priv.wz.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 一只青蛙想要过河。 假定河流被等分为x个单元格，并且在每一个单元格内都有可能放有一石子（也有可能没有）
 * 青蛙可以跳上石头，但是不可以跳入水中
 * 给定石子的位置列表（用单元格序号升序表示），请判定青蛙能否成功过河（即能否在最后一步跳至最后一个石子上）
 * 开始时，青蛙默认已站在第一个石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格1跳至单元格2）
 * 如果青蛙上一步跳跃了k个单位，那么它接下来的跳跃距离只能选择为k - 1、k或k + 1个单位
 * 另请注意，青蛙只能向前方（终点的方向）跳跃
 * <p>
 * 请注意：
 * <p>
 * 石子的数量 ≥ 2 且< 1100；
 * 每一个石子的位置序号都是一个非负整数，且其 < 231；
 * 第一个石子的位置永远是0
 */
public class FrogCanCross {
    // 按这种跳法，跳到每个单元格所需的步数都是确定的，而且是有规律的
    public boolean frogCanCross(int[] arr) {
        int preStone = 0;
        int step = 0, sum = 0;
        for (int i = 1; i < arr.length; i++) {
            int curStone = arr[i];
            while (sum < curStone) {
                step++;
                sum += step;
            }
            if (sum == curStone) {
                if (curStone - preStone > step) {
                    return false;
                }
            } else {
                // sum > curStone
                if (curStone - preStone > step - 1) {
                    return false;
                }
            }
            preStone = curStone;
        }
        return true;
    }

    /**
     * ==================
     */
    public boolean frogCanCross2(int[] arr) {
        if (arr == null) {
            return false;
        }
        if (arr[0] != 0) {
            return false;
        }
        if (arr.length == 1) {
            return true;
        }
        if (arr[1] != 1) {
            return false;
        }

        int[] tmp = new int[]{-1, 0, 1};
        List<Integer>[] dp = new ArrayList[arr.length];
        dp[1] = new ArrayList();
        dp[1].add(1);
        for (int i = 0; i < arr.length; i++) {
            if (dp[arr[i]] == null) {
                continue;
            }
            for (int preStep : dp[i]) {
                for (int j : tmp) {
                    int curStep = preStep + j;
                    int nextStone = arr[i] + curStep;
                    if (contain(arr, i, nextStone)) {
                        if (dp[nextStone] == null) {
                            dp[nextStone] = new ArrayList<>();
                        }
                        dp[nextStone].add(curStep);
                    }
                }
            }
        }
        if (dp[arr[arr.length - 1]] == null) {
            return false;
        }
        return true;
    }

    boolean contain(int[] arr, int begin, int target) {
        for (int i = begin; i < arr.length; i++) {
            if (arr[i] == target) {
                return true;
            } else if (arr[i] < target) {
                return false;
            }
        }
        return false;
    }

    /**
     * ==================
     */
    Entry[][] dp;

    public boolean solution(int[] arr) {
        dp = new Entry[arr.length][arr.length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                dp[i][j] = new Entry();
            }
        }
        return f(arr, 0, 0);
    }

    /**
     * 计算目前在arr的index位置，是否可以跳到最后，上一次是走了step步到达index的
     */
    public boolean f(int[] arr, int index, int step) {
        if (dp[index][step].valid) {
            return dp[index][step].value;
        }
        if (arr.length - 1 == index) {
            dp[index][step].valid = true;
            dp[index][step].value = true;
            return true;
        }
        int next = arr[index] + step;
        int i = index + 1;
        int first = -1;
        int second = -1;
        int third = -1;

        while (i < arr.length) {
            if (arr[i] == next - 1) {
                first = i;
            } else if (arr[i] == next) {
                second = i;
            } else if (arr[i] == next + 1) {
                third = i;
            } else if (arr[i] > next + 1) {
                break;
            }
            i++;
        }
        if (first != -1) {
            if (f(arr, first, step - 1)) {
                dp[index][step].valid = true;
                dp[index][step].value = true;
                return true;
            }
        }
        if (second != -1) {
            if (f(arr, second, step)) {
                dp[index][step].valid = true;
                dp[index][step].value = true;
                return true;
            }
        }
        if (third != -1) {
            if (f(arr, third, step + 1)) {
                dp[index][step].valid = true;
                dp[index][step].value = true;
                return true;
            }
        }
        dp[index][step].valid = true;
        dp[index][step].value = false;
        return false;
    }

    class Entry {
        boolean valid;
        boolean value;
    }
}
