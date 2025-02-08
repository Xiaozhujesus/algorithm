package priv.wz.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。
 * 你不能重新排序或删除 s 中的任何数字。你可以按任何顺序返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 */
public class RestoreIpAddresses {
    private List<String> ans = new ArrayList<>();
    private String s;
    private Stack<String> subs = new Stack<>();

    public List<String> restoreIpAddresses(String s) {
        this.s = s;
        walk(0, s.length(), 4);
        return ans;
    }

    /**
     * begin 到 end 左闭右开区间要 segment 个段
     * @param begin
     * @param end
     * @param segments
     */
    private void walk(int begin, int end, int segments) {
        if (segments == 0) {
            if (begin == end) {
                ans.add(String.join(".", subs));
            }
            return;
        }
        if (segments < 0) {
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (begin + i <= end) {
                String curSub = s.substring(begin, begin + i);
                if (valid(curSub)) {
                    subs.push(curSub);
                    walk(begin + i, end, segments - 1);
                    subs.pop();
                }
            }
        }
    }

    private boolean valid(String sub) {
        if (sub.isEmpty()) {
            return false;
        }
        if (sub.equals("0")) {
            return true;
        }
        if (sub.length() > 1 && sub.charAt(0) == '0') {
            return false;
        }
        if (sub.length() > 3) {
            return false;
        } else if (sub.length() < 3) {
            return true;
        } else {
            return "255".compareTo(sub) >= 0;
        }
    }

    public static void main(String[] args) {
        new RestoreIpAddresses().restoreIpAddresses("25525511135").forEach(System.out::println);
    }
}
