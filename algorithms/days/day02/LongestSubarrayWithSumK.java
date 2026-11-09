package days.day02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestSubarrayWithSumK {

    /**
     * Longest Subarray With Sum K (Medium)
     *
     * Given an integer array nums and an integer k, return the
     * length of the longest contiguous subarray whose sum equals k.
     *
     * A subarray is a non-empty contiguous sequence of elements.
     *
     * The array may contain positive numbers, negative numbers,
     * and zeros.
     *
     * If no subarray has a sum equal to k, return 0.
     *
     * Examples:
     *
     * Input: nums = [1, 2, 3, 1, 1, 1], k = 3
     * Output: 3
     *
     * Input: nums = [1, -1, 5, -2, 3], k = 3
     * Output: 4
     *
     * Input: nums = [-2, -1, 2, 1], k = 1
     * Output: 2
     *
     * Target complexity:
     * Time: O(n)
     * Space: O(n)
     *
     * @param nums the input array
     * @param k the target sum
     * @return the length of the longest contiguous subarray
     *         whose sum equals k, or 0 if none exists
     */
    public static int longestSubarray(int[] nums, int k) {
        int result = 0;
        int prefixSum = 0;
        Map<Integer, Integer> prefixSumsIndexes = new HashMap<>();
        prefixSumsIndexes.put(0, -1);

        for(int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if(prefixSumsIndexes.containsKey(prefixSum - k)) {
                result = Math.max(result, i - prefixSumsIndexes.get(prefixSum - k));
            }

            prefixSumsIndexes.putIfAbsent(prefixSum, i);        }

        return result;
    }

    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 1, 1, 1}, 3, 3);
        test(new int[]{1, -1, 5, -2, 3}, 3, 4);
        test(new int[]{-2, -1, 2, 1}, 1, 2);
        test(new int[]{1, 2, 3}, 10, 0);
        test(new int[]{3}, 3, 1);
        test(new int[]{3}, 5, 0);
        test(new int[]{0, 0, 0}, 0, 3);
        test(new int[]{1, -1, 1, -1}, 0, 4);
        test(new int[]{2, 0, 0, 3}, 2, 3);
        test(new int[]{5, -2, -3, 1, 4}, 0, 4);
    }

    private static void test(int[] nums, int k, int expected) {
        int actual = longestSubarray(nums, k);

        System.out.println(
            (actual == expected ? "PASS" : "FAIL")
                + " | nums=" + Arrays.toString(nums)
                + ", k=" + k
                + " | Expected: " + expected
                + " | Actual: " + actual
        );
    }
}
