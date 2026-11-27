package days.day20;

public class SearchInRotatedSortedArray {

    /*
     * LeetCode 33 - Search in Rotated Sorted Array
     * Difficulty: Medium
     *
     * Given an integer array nums sorted in ascending
     * order with distinct values, the array is possibly
     * rotated at an unknown pivot.
     *
     * Given an integer target, return its index if it
     * exists in the array, or -1 otherwise.
     *
     * Example 1:
     * Input: nums = [4, 5, 6, 7, 0, 1, 2], target = 0
     * Output: 4
     *
     * Example 2:
     * Input: nums = [4, 5, 6, 7, 0, 1, 2], target = 3
     * Output: -1
     *
     * Example 3:
     * Input: nums = [1], target = 0
     * Output: -1
     *
     * Constraints:
     * - 1 <= nums.length <= 5000
     * - -10000 <= nums[i] <= 10000
     * - All values in nums are unique.
     * - nums is sorted and possibly rotated.
     * - -10000 <= target <= 10000
     *
     * Requirements:
     * - Time complexity: O(log n)
     * - Space complexity: O(1)
     * - Do not use built-in search methods.
     */

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        // Target in the left sorted segment
        test(new int[]{4, 5, 6, 7, 0, 1, 2}, 5, 1);

        // Target in the right sorted segment
        test(new int[]{4, 5, 6, 7, 0, 1, 2}, 1, 5);

        // Target at mid
        test(new int[]{4, 5, 6, 7, 0, 1, 2}, 7, 3);

        // Target not present
        test(new int[]{4, 5, 6, 7, 0, 1, 2}, 3, -1);

        // Target at the first index
        test(new int[]{4, 5, 6, 7, 0, 1, 2}, 4, 0);

        // Target at the last index
        test(new int[]{4, 5, 6, 7, 0, 1, 2}, 2, 6);

        // Target is the minimum element
        test(new int[]{4, 5, 6, 7, 0, 1, 2}, 0, 4);

        // Non-rotated array
        test(new int[]{1, 2, 3, 4, 5}, 4, 3);

        // Single element - found
        test(new int[]{1}, 1, 0);

        // Single element - not found
        test(new int[]{1}, 0, -1);

        // Two elements - rotated
        test(new int[]{3, 1}, 1, 1);
        test(new int[]{3, 1}, 3, 0);

        // Two elements - non-rotated
        test(new int[]{1, 3}, 3, 1);

        // Rotation in the left half
        test(new int[]{6, 7, 0, 1, 2, 3, 4}, 7, 1);

        // Negative numbers
        test(new int[]{2, 4, 6, -5, -3, -1, 0}, -3, 4);

        System.out.println("All 15 tests passed!");
    }

    private static void test(int[] nums, int target, int expected) {
        int actual = search(nums, target);

        if (actual != expected) {
            throw new AssertionError(
                    "Target: " + target
                            + ", expected: " + expected
                            + ", but got: " + actual
            );
        }

        System.out.println(
                "PASS: target = " + target
                        + ", index = " + actual
        );
    }
}