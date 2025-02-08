package priv.wz.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串 。返回 s 所有可能的分割方案。
 */
public class Partition {
    int[][] dp;
    List<List<String>> ans = new ArrayList<>();
    List<String> cur = new ArrayList<>();
    int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        dp = new int[n][n];

        dfs(s, 0);
        return ans;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (isPalindrome(s, i, j) == 1) {
                cur.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                cur.remove(cur.size() - 1);
            }
        }
    }

    // 记忆化搜索中，f[i][j] = 0 表示未搜索，1 表示是回文串，-1 表示不是回文串
    public int isPalindrome(String s, int i, int j) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        if (i >= j) {
            dp[i][j] = 1;
        } else if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = isPalindrome(s, i + 1, j - 1);
        } else {
            dp[i][j] = -1;
        }
        return dp[i][j];
    }
}
