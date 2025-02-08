package priv.wz.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现不止一次。
 * <p>
 * 示例 1：
 * 输入：s = "IceCreAm"
 * 输出："AceCreIm"
 * 解释：
 * s 中的元音是 ['I', 'e', 'e', 'A']。反转这些元音，s 变为 "AceCreIm".
 */
public class ReverseVowels {
    public String reverseVowels(String s) {
        char[] charArray = s.toCharArray();
        int i = 0, j = charArray.length - 1;
        String vowel = "aeiouAEIOU";
        while (i < j) {
            while (i < j && vowel.indexOf(charArray[i]) < 0) {
                i++;
            }
            while (i < j && "aeiouAEIOU".indexOf(charArray[j]) < 0) {
                j--;
            }
            if (i < j) {
                char tmp = charArray[i];
                charArray[i] = charArray[j];
                charArray[j] = tmp;
                i++;
                j--;
            }
        }
        return new String(charArray);
    }

    public static void main(String[] args) {
        System.out.println(new ReverseVowels().reverseVowels("IceCreAm"));
    }
}
