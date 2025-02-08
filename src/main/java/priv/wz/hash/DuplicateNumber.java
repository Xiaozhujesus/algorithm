package priv.wz.hash;

/**
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。
 * 请找出数组中任意一个重复的数字，要求时间复杂度 O(N)，空间复杂度 O(1)
 */
public class DuplicateNumber {
    public int duplicate(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            while (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]]) {
                    return numbers[i];
                }
                swap(numbers, i, numbers[i]);
            }
        }
        return -1;
    }
    private void swap(int[] numbers, int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }
}


/**
 * 问题的关键是n个数字，范围在0 ~ n-1，也就是在n以内，因此每个数字的hash值就是它本身，上面的方法本质上就是hashset存储，寻找重复元素
 * 如果n个数字范围不确定，那么只能排序，或者使用hashset进行重复元素查找
 */