package priv.wz.backtrack;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * <p>
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 */
public class WordBreak {
    public boolean f(String s, List<String> list) {
        if (s == null || s.length() == 0 || list == null || list.size() == 0) {
            return false;
        }
        String[] dic = list.toArray(new String[0]);
        Arrays.sort(dic);
        return util(s, dic);
    }

    /**
     * 递归处理，判断s可否分割成dic中的单词
     *
     * @param s
     * @param dic
     * @return
     */
    private boolean util(String s, String[] dic) {
        if (s.length() == 0) {
            return true;
        }
        int index = findFirstGreater(s, dic);
        //尝试所有可能情况
        for (int i = index - 1; i >= 0; i--) {
            if (s.startsWith(dic[i])) {
                if (util(s.substring(dic[i].length()), dic)) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    /**
     * 在有序dic里找到第一个比s大的字符串的索引
     *
     * @param s
     * @param dic
     * @return
     */
    private int findFirstGreater(String s, String[] dic) {
        int l = 0;
        int r = dic.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            int diff = dic[mid].compareTo(s);
            if (diff > 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        int i;
        for (i = l; i < dic.length; i++) {
            if (dic[i].compareTo(s) <= 0) {
                i++;
            } else {
                break;
            }
        }
        return i;
    }
}
