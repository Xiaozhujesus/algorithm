package priv.wz.other;

/**
 * 给定一个大小为 n 的数组nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 摩尔投票法
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int ans = nums[0];
        int vote = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == ans) {
                vote++;
            } else {
                vote--;
                if (vote == 0) {
                    i++;
                    ans = nums[i];
                    vote++;
                }
            }
        }
        return ans;
    }
}
