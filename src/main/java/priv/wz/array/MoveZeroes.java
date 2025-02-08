package priv.wz.array;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
 * 必须在原数组上操作，不能拷贝额外的数组
 * 尽量减少操作次数
 */
public class MoveZeroes {
    void moveZeroes(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int first = 0, second = 0;
        while (second < arr.length) {
            if (arr[second] != 0) {
                arr[first++] = arr[second];
            }
            second++;
        }
        while (first < arr.length) {
            arr[first++] = 0;
        }
    }
}
