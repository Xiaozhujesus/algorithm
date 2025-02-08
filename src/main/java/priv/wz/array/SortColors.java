package priv.wz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 */
public class SortColors {
    //计数排序
    public void solution(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        Map map = new HashMap<Integer, Integer>();
        for (int i : arr) {
            map.put(i, (Integer) map.getOrDefault(i, 0) + 1);
        }
        int i = 0;
        int num;
        if (map.get(0) != null) {
            num = (Integer) map.get(0);
            while (num-- != 0) {
                arr[i++] = 0;
            }
        }
        if (map.get(1) != null) {
            num = (Integer) map.get(1);
            while (num-- != 0) {
                arr[i++] = 1;
            }
        }
        if (map.get(2) != null) {
            num = (Integer) map.get(2);
            while (num-- != 0) {
                arr[i++] = 2;
            }
        }
    }


    //3指针法，与快排序的分区函数一样
    public void f(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int red = 0;
        int blue = arr.length - 1;
        int cur = 0;
        while (cur <= blue) {
            while (arr[cur] == 2 && cur <= blue) {
                swap(arr, cur, blue);
                blue--;
            }
            while (arr[cur] == 0 && cur >= red) {
                swap(arr, cur, red);
                red++;
            }
            cur++;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1,1, 2};
        new SortColors().f(arr);
        for (int i : arr) {
            System.out.println(i);
        }

    }
}
