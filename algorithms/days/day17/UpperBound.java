package days.day17;

public class UpperBound {

    /*
     * Day 17 - Task 2: Upper Bound
     *
     * Given a sorted array nums and an integer target, return the
     * index of the first element strictly greater than target.
     * If no such element exists, return nums.length.
     *
     * Time: O(log n); Auxiliary space: O(1).
     */
    public static int upperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        test(new int[]{1, 2, 2, 2, 4, 5}, 2, 4);
        test(new int[]{1, 2, 4, 5}, 3, 2);
        test(new int[]{1, 2, 4, 5}, 0, 0);
        test(new int[]{1, 2, 4, 5}, 6, 4);
        test(new int[]{2, 2, 2, 2}, 2, 4);
        test(new int[]{2, 2, 2, 2}, 1, 0);
        test(new int[]{1, 3, 5, 7}, 5, 3);
        test(new int[]{1, 3, 5, 7}, 4, 2);
        test(new int[]{5}, 5, 1);
        test(new int[]{5}, 3, 0);
        test(new int[]{5}, 8, 1);
        test(new int[]{}, 3, 0);
    }

    private static void test(int[] nums, int target, int expected) {
        int actual = upperBound(nums, target);
        if (actual != expected) {
            throw new AssertionError("Expected " + expected + ", got " + actual);
        }
        System.out.println("PASS");
    }
}
