package days.day12;

import java.util.Arrays;

/**
 * Day 12 - LeetCode 26: Remove Duplicates from Sorted Array
 *
 * Problem:
 * Given an integer array nums sorted in non-decreasing order,
 * remove the duplicates in-place such that each unique element
 * appears only once.
 *
 * The relative order of the elements must remain the same.
 *
 * Return the number of unique elements (k).
 *
 * Requirements:
 * 1. Modify the original array in-place.
 * 2. Do not use an additional array, List, or Set.
 * 3. The first k elements must contain the unique values
 *    in their original sorted order.
 * 4. Elements beyond index k - 1 do not matter.
 * 5. Return k.
 *
 * Example 1:
 * Input:  nums = [1, 1, 2]
 * Output: k = 2
 *         nums = [1, 2, _]
 *
 * Example 2:
 * Input:  nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
 * Output: k = 5
 *         nums = [0, 1, 2, 3, 4, _, _, _, _, _]
 *
 * Constraints:
 * - 1 <= nums.length <= 30000
 * - -100 <= nums[i] <= 100
 * - nums is sorted in non-decreasing order.
 *
 * Target complexity:
 * Time:  O(n)
 * Space: O(1)
 */
public class RemoveDuplicatesFromSortedArray {

    public static int removeDuplicates(int[] nums) {
        int write = 0;

        for (int read = 1; read < nums.length; read++) {
            if (nums[read] != nums[write]) {
                write++;
                nums[write] = nums[read];
            }
        }

        return write + 1;
    }

    public static void main(String[] args) {

        // Test 1: Multiple duplicates
        test(
                new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4},
                new int[]{0, 1, 2, 3, 4}
        );

        // Test 2: Basic example
        test(
                new int[]{1, 1, 2},
                new int[]{1, 2}
        );

        // Test 3: Single element
        test(
                new int[]{5},
                new int[]{5}
        );

        // Test 4: All duplicates
        test(
                new int[]{2, 2, 2, 2, 2},
                new int[]{2}
        );

        // Test 5: All unique
        test(
                new int[]{1, 2, 3, 4, 5},
                new int[]{1, 2, 3, 4, 5}
        );

        // Test 6: Negative numbers
        test(
                new int[]{-3, -3, -1, -1, 0, 2, 2},
                new int[]{-3, -1, 0, 2}
        );

        // Test 7: Duplicates at the end
        test(
                new int[]{1, 2, 3, 3, 3},
                new int[]{1, 2, 3}
        );
    }

    private static void test(int[] nums, int[] expected) {
        int k = removeDuplicates(nums);

        int[] actual = Arrays.copyOf(nums, k);

        boolean passed = k == expected.length
                && Arrays.equals(actual, expected);

        System.out.println(
                (passed ? "PASS" : "FAIL")
                        + " | Expected: " + Arrays.toString(expected)
                        + " | Actual: " + Arrays.toString(actual)
                        + " | k = " + k
        );
    }
}
