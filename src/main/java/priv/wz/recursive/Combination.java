package priv.wz.recursive;

/**
 * 输出集合的所有组合，不只是n个元素的组合
 */
public class Combination {
    //递归
    public void combine(char[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        util(arr, new String(), 0);
    }

    private void util(char[] arr, String str, int from) {
        if (from == arr.length) {
            if (str.length() != 0) {
                System.out.println(str);
            }
            return;
        }
        // 类似树的延展，也是搜素
        util(arr, str, from + 1);
        util(arr, str + arr[from], from + 1);
    }


    /**
     * 迭代
     * 将每个元素看做二进制数的一个 bit，那么所有的组合就是 1~2^n 之间所有的数
     * bit 位为 0 表示元素不在集合中，为 1 表示在；这其实也是在进行搜索
     */
    public void combine2(char[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int count = (int) Math.pow(2, arr.length);
        StringBuilder sb = new StringBuilder();
        int index;
        for (int i = 1; i < count; i++) {
            sb.delete(0, sb.length());
            index = arr.length - 1;
            int cur = i;
            while (cur != 0) {
                if ((cur & 1) == 1) {
                    sb.append(arr[index]);
                }
                cur = cur >> 1;
                index--;
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        new Combination().combine(new char[]{'a','a', 'b', 'c'});
    }
}
