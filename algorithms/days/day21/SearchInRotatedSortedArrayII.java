package days.day21;

public class SearchInRotatedSortedArrayII {

    /*
     * LeetCode 81 - Search in Rotated Sorted Array II
     * Difficulty: Medium
     *
     * Given an integer array nums sorted in non-decreasing
     * order, the array is possibly rotated at an unknown pivot.
     *
     * Unlike LeetCode 33, nums may contain duplicate values.
     *
     * Given an integer target, return true if target exists
     * in nums, or false otherwise.
     *
     * Example 1:
     * Input: nums = [2, 5, 6, 0, 0, 1, 2], target = 0
     * Output: true
     *
     * Example 2:
     * Input: nums = [2, 5, 6, 0, 0, 1, 2], target = 3
     * Output: false
     *
     * Example 3:
     * Input: nums = [1, 0, 1, 1, 1], target = 0
     * Output: true
     *
     * Constraints:
     * - 1 <= nums.length <= 5000
     * - -10000 <= nums[i] <= 10000
     * - nums is sorted in non-decreasing order
     *   and possibly rotated.
     * - Duplicate values are allowed.
     * - -10000 <= target <= 10000
     *
     * Requirements:
     * - Minimize the number of operations.
     * - Worst-case time complexity: O(n)
     * - Space complexity: O(1)
     * - Do not use built-in search methods.
     */

    public static boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return true;
            }

            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
                continue;
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
        return false;
    }

    public static void main(String[] args) {

        // Basic rotated arrays
        test(new int[]{2, 5, 6, 0, 0, 1, 2}, 0, true);
        test(new int[]{2, 5, 6, 0, 0, 1, 2}, 3, false);
        test(new int[]{2, 5, 6, 0, 0, 1, 2}, 6, true);

        // Ambiguous sorted halves
        test(new int[]{1, 0, 1, 1, 1}, 0, true);
        test(new int[]{1, 1, 1, 0, 1}, 0, true);
        test(new int[]{1, 1, 0, 1, 1}, 0, true);

        // All elements are equal
        test(new int[]{3, 3, 3, 3, 3}, 3, true);
        test(new int[]{3, 3, 3, 3, 3}, 5, false);

        // Single element
        test(new int[]{1}, 1, true);
        test(new int[]{1}, 0, false);

        // Two elements
        test(new int[]{1, 1}, 1, true);
        test(new int[]{1, 1}, 2, false);
        test(new int[]{2, 1}, 1, true);
        test(new int[]{2, 1}, 2, true);

        // Non-rotated arrays with duplicates
        test(new int[]{1, 1, 2, 2, 3, 3}, 2, true);
        test(new int[]{1, 1, 2, 2, 3, 3}, 4, false);

        // Many duplicates
        test(new int[]{3, 3, 3, 3, 4, 5, 3, 3, 3, 3, 3, 3, 3},
                5, true);

        test(new int[]{3, 3, 3, 3, 4, 5, 3, 3, 3, 3, 3, 3, 3},
                6, false);

        // Negative values
        test(new int[]{2, 2, 3, -3, -2, -2, 0}, -2, true);
        test(new int[]{2, 2, 3, -3, -2, -2, 0}, -1, false);

        System.out.println("All 20 tests passed!");
    }

    private static void test(int[] nums, int target, boolean expected) {
        boolean actual = search(nums, target);

        if (actual != expected) {
            throw new AssertionError(
                    "Target: " + target
                            + ", expected: " + expected
                            + ", but got: " + actual
            );
        }

        System.out.println(
                "PASS: target = " + target + ", result = " + actual
        );
    }
}