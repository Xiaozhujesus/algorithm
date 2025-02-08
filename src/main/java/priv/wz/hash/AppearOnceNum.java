package priv.wz.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素
 */
public class AppearOnceNum {
    public int find(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for(int i : arr) {
            if(set.contains(i)) {
                set.remove(i);
            } else {
                set.add(i);
            }
        }
        for(int i : set) {
            return i;
        }
        return -1;
    }
    // 可以看下 bit 目录下的高效解法
}
