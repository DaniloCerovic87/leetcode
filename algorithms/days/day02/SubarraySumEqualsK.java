package days.day02;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    /**
     * LeetCode 560 - Subarray Sum Equals K (Medium)
     *
     * Given an integer array nums and an integer k, return the
     * total number of contiguous subarrays whose sum equals k.
     *
     * A subarray is a non-empty contiguous sequence of elements
     * within an array.
     *
     * Examples:
     *
     * Input:  nums = [1, 1, 1], k = 2
     * Output: 2
     *
     * Input:  nums = [1, 2, 3], k = 3
     * Output: 2
     *
     * Constraints:
     * - 1 <= nums.length <= 20,000
     * - -1000 <= nums[i] <= 1000
     * - -10^7 <= k <= 10^7
     *
     * @param nums the input array of integers
     * @param k the target sum
     * @return the number of contiguous subarrays whose sum equals k
     */
    public static int subarraySum(int[] nums, int k) {
        int prefixSum = 0;
        int count = 0;

        Map<Integer, Integer> prefixSumCounts = new HashMap<>();
        prefixSumCounts.put(0, 1);

        for (int num : nums) {
            prefixSum += num;

            count += prefixSumCounts.getOrDefault(prefixSum - k, 0);

            prefixSumCounts.put(
                    prefixSum,
                    prefixSumCounts.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }

    public static void main(String[] args) {
        test(new int[]{1, 1, 1}, 2, 2);
        test(new int[]{1, 2, 3}, 3, 2);
        test(new int[]{1}, 1, 1);
        test(new int[]{1}, 2, 0);
        test(new int[]{0, 0, 0}, 0, 6);
        test(new int[]{1, -1, 0}, 0, 3);
        test(new int[]{-1, -1, 1}, 0, 1);
        test(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7, 4);
        test(new int[]{2, -2, 2, -2}, 0, 4);
        test(new int[]{5, -2, -3, 1, 4}, 0, 2);
    }

    private static void test(int[] nums, int k, int expected) {
        int actual = subarraySum(nums, k);

        if (actual == expected) {
            System.out.println(
                "PASS: nums=" + java.util.Arrays.toString(nums)
                + ", k=" + k
            );
        } else {
            System.out.println(
                "FAIL: nums=" + java.util.Arrays.toString(nums)
                + ", k=" + k
                + " | Expected: " + expected
                + " | Actual: " + actual
            );
        }
    }
}
