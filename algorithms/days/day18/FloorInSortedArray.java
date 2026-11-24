package days.day18;

public class FloorInSortedArray {
    /*
     * Day 18 - Binary Search
     * Task 2: FloorInSortedArray
     *
     * Problem: Return the last index of the largest element less than or equal to target. Return -1 if absent.
     * Input is sorted in non-decreasing order and may contain duplicates.
     * Handle empty arrays and targets outside the array range.
     * Use binary search without built-in search methods.
     * Time: O(log n), Space: O(1).
     */

    public static int floor(int[] nums, int target) {
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
        return left == 0 ? -1 : left - 1;
    }

    public static void main(String[] args) {
        test(new int[]{1, 3, 5, 7, 9}, 6, 2);
        test(new int[]{1, 3, 5, 7, 9}, 5, 2);
        test(new int[]{1, 3, 5, 7, 9}, 10, 4);
        test(new int[]{1, 3, 5, 7, 9}, 0, -1);
        test(new int[]{1, 2, 2, 2, 5}, 2, 3);
        test(new int[]{2, 2, 2, 2}, 2, 3);
        test(new int[]{2, 2, 2, 2}, 1, -1);
        test(new int[]{2, 2, 2, 2}, 3, 3);
        test(new int[]{5}, 5, 0);
        test(new int[]{5}, 3, -1);
        test(new int[]{5}, 8, 0);
        test(new int[]{}, 3, -1);
        test(new int[]{-5, -3, -1, 2}, -2, 1);
        test(new int[]{-5, -3, -1, 2}, -6, -1);
        test(new int[]{1, 3, 5, 7}, 6, 2);
    }

    private static void test(int[] nums, int target, int expected) {
        int actual = floor(nums, target);
        if (actual != expected) {
            throw new AssertionError("Expected " + expected + ", got " + actual);
        }
        System.out.println("PASS");
    }
}
