package days.day01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    /**
     * Given an array of integers nums and an integer target,
     * return the indices of two numbers whose sum equals target.
     *
     * Each input has exactly one valid solution.
     * You cannot use the same element twice.
     *
     * The indices can be returned in any order.
     *
     * Examples:
     * [2, 7, 11, 15], target = 9 -> [0, 1]
     * [3, 2, 4], target = 6      -> [1, 2]
     * [3, 3], target = 6         -> [0, 1]
     *
     * @param nums array of integers
     * @param target required sum
     * @return indices of two elements whose sum equals target
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> valueToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int currentNumber = nums[i];
            Integer complementIndex = valueToIndex.get(target - currentNumber);
            if (complementIndex != null) {
                return new int[]{i, complementIndex};
            }

            valueToIndex.put(currentNumber, i);
        }

        return new int[2];
    }

    public static void main(String[] args) {
        test(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1});
        test(new int[]{3, 2, 4}, 6, new int[]{1, 2});
        test(new int[]{3, 3}, 6, new int[]{0, 1});
        test(new int[]{1, 5, 8, 10}, 15, new int[]{1, 3});
        test(new int[]{-3, 4, 3, 90}, 0, new int[]{0, 2});
        test(new int[]{0, 4, 3, 0}, 0, new int[]{0, 3});
        test(new int[]{-1, -2, -3, -4}, -6, new int[]{1, 3});
        test(new int[]{5, 5}, 10, new int[]{0, 1});
        test(new int[]{10, 20, 30, 40}, 70, new int[]{2, 3});
    }

    private static void test(int[] nums, int target, int[] expected) {
        int[] actual = twoSum(nums, target);

        boolean passed = actual.length == 2 && (Arrays.equals(actual, expected) || actual[0] == expected[1] && actual[1] == expected[0]);

        System.out.println(
                "Input: " + Arrays.toString(nums)
                + " | Target: " + target
                + " | Expected: " + Arrays.toString(expected)
                + " | Actual: " + Arrays.toString(actual)
                + " | " + (passed ? "PASS" : "FAIL")
        );
    }
}