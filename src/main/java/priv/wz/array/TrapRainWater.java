package priv.wz.array;

/**
 * 一维数组形式接雨水
 */
public class TrapRainWater {
    public int trapRainWater(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[1] = height[0];
        rightMax[height.length - 2] = height[height.length - 1];

        int preMax = height[0];
        for (int i = 1; i < height.length; i++) {
            preMax = Math.max(height[i - 1], preMax);
            leftMax[i] = preMax;
        }
        preMax = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            preMax = Math.max(height[i + 1], preMax);
            rightMax[i] = preMax;
        }
        int sum = 0;
        /**
         * 某点能接的雨水只由其左侧和右侧的最高点决定
         */
        for (int i = 1; i < height.length - 1; i++) {
            int cur = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (cur > 0) {
                sum += cur;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new TrapRainWater().trapRainWater(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
