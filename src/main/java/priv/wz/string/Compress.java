package priv.wz.string;

import java.util.Stack;

/**
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 * 从一个空字符串 s 开始。对于 chars 中的每组连续重复字符 ：如果这一组长度为 1 ，则将字符追加到 s 中。否则，需要向 s 追加字符，后跟这一组的长度。
 * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
 * 请在修改完输入数组后 ，返回该数组的新长度。
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 * <p>
 * 示例 1：
 * <p>
 * 输入：chars = ["a","a","b","b","c","c","c"]
 * 输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
 * 解释："aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
 * <p>
 * 示例 2：
 * <p>
 * 输入：chars = ["a"]
 * 输出：返回 1 ，输入数组的前 1 个字符应该是：["a"]
 * 解释：唯一的组是“a”，它保持未压缩，因为它是一个字符。
 * <p>
 * 示例 3：
 * <p>
 * 输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * 输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
 * 解释：由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
 */
public class Compress {
    public int compress(char[] chars) {
        char[] tmp = new char[chars.length + 1];
        System.arraycopy(chars, 0, tmp, 0, chars.length);
        // 加一个不同的字符，用来规约最初数组的最后一个字符
        tmp[tmp.length - 1] = ' ';
        int ans = 0;
        int count = 0, index = 0;
        for (int i = 0; i < tmp.length; i++) {
            if (i == 0) {
                count++;
            } else {
                if (tmp[i] != tmp[i - 1]) {
                    ans++;
                    tmp[index++] = tmp[i - 1];
                    if (count != 1) {
                        Stack<Character> stack = new Stack<>();
                        while (count != 0) {
                            stack.push((char) ('0' + count % 10));
                            count /= 10;
                            ans++;
                        }
                        while (!stack.empty()) {
                            tmp[index++] = stack.pop();
                        }
                    }
                    count = 1;
                } else {
                    count++;
                }
            }
        }
        System.arraycopy(tmp, 0, chars, 0, ans);
        return ans;
    }
}
