package priv.wz.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个数组arr，返回arr的最长无的重复子串的长度(无重复指的是所有数字都不相同)。
 */
public class LongestUnRepeatSubArr {
    public int f(String s) {
        if (s == null) {
            return 0;
        }
        char[] arr = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int max = 0;
        for (int start = 0, end = 0; end < arr.length; end++) {
            if (set.contains(arr[end])) {
                while (arr[start] != arr[end]) {
                    set.remove(arr[start]);
                    start++;
                }
                start++;
            } else {
                set.add(arr[end]);
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestUnRepeatSubArr().f("abba"));
    }
}
