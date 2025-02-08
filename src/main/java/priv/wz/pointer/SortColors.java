package priv.wz.pointer;

/**
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。必须在不使用库的sort函数的情况下解决这个问题。
 *
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 *
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 */
public class SortColors {
    // 计数排序
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int red = 0, white = 0, blue = 0;
        for (int i : nums) {
            switch (i) {
                case 0:
                    red++;
                    break;
                case 1:
                    white++;
                    break;
                case 2:
                    blue++;
                    break;
            }
        }
        int i = 0;
        while (red-- > 0) {
            nums[i++] = 0;
        }
        while (white-- > 0) {
            nums[i++] = 1;
        }
        while (blue-- > 0) {
            nums[i++] = 2;
        }
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        // redIndex 及之前都是0，blueIndex 及之后都是2，有点类似快排的分区函数
        int redIndex = -1, blueIndex = nums.length, i = 0;

        while (i < blueIndex) {
            while (nums[i] != 1) {
                if (nums[i] == 0) {
                    ++redIndex;
                    if (redIndex == i) {
                        break;
                    }
                    swap(nums, redIndex, i);
                } else {
                    if (i != blueIndex) {
                        swap(nums, --blueIndex, i);
                    } else {
                        break;
                    }
                }
            }
            i++;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] tmp = {2,2};
        new SortColors().sort(tmp);
    }
}
