package days.day03;

import java.util.Arrays;

public class MaximumAverageSubarrayI {

    /**
     * LeetCode 643 - Maximum Average Subarray I (Easy)
     *
     * Given an integer array nums consisting of n elements
     * and an integer k, find a contiguous subarray whose
     * length is exactly k and whose average value is maximum.
     *
     * Return the maximum average value.
     *
     * Constraints:
     * - 1 <= k <= nums.length <= 100,000
     * - -10,000 <= nums[i] <= 10,000
     *
     * Examples:
     *
     * Input: nums = [1, 12, -5, -6, 50, 3], k = 4
     * Output: 12.75
     *
     * Input: nums = [5], k = 1
     * Output: 5.0
     *
     * @param nums the input integer array
     * @param k the required subarray length
     * @return the maximum average of any contiguous
     *         subarray of length k
     */
    public static double findMaxAverage(int[] nums, int k) {
        int currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }

        int maxSum = currentSum;

        for (int i = 0; i < nums.length - k; i++) {
            currentSum = currentSum - nums[i] + nums[i + k];
            maxSum = Math.max(maxSum, currentSum);
        }

        return (double) maxSum / k;
    }

    public static void main(String[] args) {
        test(new int[]{1, 12, -5, -6, 50, 3}, 4, 12.75);
        test(new int[]{5}, 1, 5.0);
        test(new int[]{1, 2, 3, 4, 5}, 2, 4.5);
        test(new int[]{-1, -2, -3, -4}, 2, -1.5);
        test(new int[]{0, 0, 0, 0}, 3, 0.0);
        test(new int[]{10, -5, 20, -5, 10}, 3, 8.333333333333334);
        test(new int[]{-10, 5, 5, -10, 20}, 2, 5.0);
        test(new int[]{1, 2, 3}, 3, 2.0);
        test(new int[]{100, -100, 100, -100}, 1, 100.0);
    }

    private static void test(int[] nums, int k, double expected) {
        double actual = findMaxAverage(nums, k);
        boolean passed = Math.abs(actual - expected) < 1e-5;

        System.out.println(
            (passed ? "PASS" : "FAIL")
                + " | nums=" + Arrays.toString(nums)
                + ", k=" + k
                + " | Expected: " + expected
                + " | Actual: " + actual
        );
    }
}
