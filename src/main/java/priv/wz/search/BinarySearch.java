package priv.wz.search;

public class BinarySearch {

    /**
     * 搜索第一个值等于给定值的元素，没有返回-1
     *
     * @param arr
     * @param target
     * @return
     */
    public int searchFirstE(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0, high = arr.length - 1, mid;
        while (low <= high) {
            mid = (low + high) >> 1;
            if (arr[mid] < target) {
                low = mid + 1;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                if (mid == 0 || arr[mid - 1] != target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 搜索最后一个值等于给定值的元素，没有返回-1
     *
     * @param arr
     * @param target
     * @return
     */
    public int searchLastE(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0, high = arr.length - 1, mid;
        while (low <= high) {
            mid = (low + high) >> 1;
            if (arr[mid] < target) {
                low = mid + 1;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] != target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素，没有返回-1
     *
     * @param arr
     * @param target
     * @return
     */
    public int searchFirstGE(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0, high = arr.length - 1, mid;
        while (low <= high) {
            mid = (low + high) >> 1;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                if (mid == 0 || arr[mid - 1] < target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     *
     * @param arr
     * @param target
     * @return
     */
    public int searchLastLE(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0, high = arr.length - 1, mid;
        while (low <= high) {
            mid = (low + high) >> 1;
            if (arr[mid] > target) {
                high = mid - 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] > target) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
