package priv.wz.permute.combine;

/**
 * 字符串的所有排列
 */
public class StringPermutation {
    // 这里的方法只适合没有重复元素的情况
    public void start(String input) {
        permutation(input.toCharArray(), 0, input.length() - 1);
    }

    private void permutation(char[] input, int l, int r) {
        if (l == r) {
            System.out.println(String.valueOf(input));
        }
        for (int i = l; i <= r; i++) {
            swap(input, l, i);
            permutation(input, l + 1, r);
            swap(input, l, i);
        }
    }

    private void swap(char[] input, int i, int j) {
        char tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }

    public static void main(String[] args) {
        new StringPermutation().start("aa");
    }
}
