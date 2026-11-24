package days.day18;

public class CeilInSortedArray {
    /*
     * Day 18 - Binary Search
     * Task 3: CeilInSortedArray
     *
     * Problem: Return the first index of the smallest element greater than or equal to target. Return -1 if absent.
     * Input is sorted in non-decreasing order and may contain duplicates.
     * Handle empty arrays and targets outside the array range.
     * Use binary search without built-in search methods.
     * Time: O(log n), Space: O(1).
     */

    public static int ceil(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left == nums.length ? -1 : left;
    }

    public static void main(String[] args) {
        test(new int[]{1, 3, 5, 7, 9}, 6, 3);
        test(new int[]{1, 3, 5, 7, 9}, 5, 2);
        test(new int[]{1, 3, 5, 7, 9}, 10, -1);
        test(new int[]{1, 3, 5, 7, 9}, 0, 0);
        test(new int[]{1, 2, 2, 2, 5}, 2, 1);
        test(new int[]{2, 2, 2, 2}, 2, 0);
        test(new int[]{2, 2, 2, 2}, 1, 0);
        test(new int[]{2, 2, 2, 2}, 3, -1);
        test(new int[]{5}, 5, 0);
        test(new int[]{5}, 3, 0);
        test(new int[]{5}, 8, -1);
        test(new int[]{}, 3, -1);
        test(new int[]{-5, -3, -1, 2}, -2, 2);
        test(new int[]{-5, -3, -1, 2}, -6, 0);
        test(new int[]{1, 3, 5, 7}, 6, 3);
    }

    private static void test(int[] nums, int target, int expected) {
        int actual = ceil(nums, target);
        if (actual != expected) {
            throw new AssertionError("Expected " + expected + ", got " + actual);
        }
        System.out.println("PASS");
    }
}
