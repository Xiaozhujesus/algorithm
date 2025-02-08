package priv.wz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 寻找无序数组的众数，这里众数定义为出现次数超过一半的数
 * <p>
 * 要求时间复杂度O(n)，空间复杂度O(1)
 * <p>
 * 你可以假设众数一定存在
 */
public class FindCommonNum {
    /**
     * 摩尔投票算法：
     * 扔掉一个众数和一个非众数，众数不变
     * 扔掉两个非众数，众数不变
     * <p>
     * 如果题目里众数不一定存在，则最后遍历一遍原数组，统计res是否超过一半
     */
    public int f(int[] arr) {
        int res = 0, cnt = 0;
        for (int num : arr) {
            if (cnt == 0) {
                res = num;
                cnt++;
            } else if (num == res) {
                cnt++;
            } else {
                cnt--;
            }
        }
        return res;
    }

    /**
     * 这里的众数就是出现次数最多的数
     *
     * @param arr
     * @return
     */
    public int g(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int max = 0;
        for (int i : arr) {
            int cur = map.getOrDefault(i, 0);
            int after = cur + 1;
            map.put(i, after);
            if (after > max) {
                res = i;
                max = after;
            }
        }
        return res;
    }
}
