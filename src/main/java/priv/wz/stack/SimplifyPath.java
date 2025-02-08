package priv.wz.stack;

import java.util.Stack;

/**
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..）表示将目录切换到上一级（指向父目录）；
 * 两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。
 * 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 * <p>
 * 请注意，返回的 规范路径 必须遵循下述格式：
 * <p>
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：path = "/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        Stack<String> dirs = new Stack<>();
        int begin = 0, end;
        while (true) {
            while (begin < path.length() && path.charAt(begin) == '/') {
                begin++;
            }
            if (begin == path.length()) {
                break;
            }
            end = begin + 1;
            while (end < path.length() && path.charAt(end) != '/') {
                end++;
            }
            String cur = path.substring(begin, end);
            if (cur.equals("..")) {
                if (!dirs.isEmpty()) {
                    dirs.pop();
                }
            } else if (cur.equals(".")) {

            } else {
                dirs.push(cur);
            }
            begin = end;
        }
        if (dirs.isEmpty()) {
            return "/";
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < dirs.size(); i++) {
            ans.append("/");
            ans.append(dirs.get(i));
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String s = new SimplifyPath().simplifyPath("/a/./b/../../c/");
        System.out.println(s);
    }
}
