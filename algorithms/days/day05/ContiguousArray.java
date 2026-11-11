package days.day05;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {

    /**
     * LeetCode 525 - Contiguous Array (Medium)
     * <p>
     * Given a binary array nums, return the maximum length
     * of a contiguous subarray with an equal number of
     * zeros and ones.
     * <p>
     * Examples:
     * nums = [0, 1]          -> 2
     * nums = [0, 1, 0]       -> 2
     * nums = [0, 1, 0, 1]    -> 4
     *
     * @param nums an array containing only 0s and 1s
     * @return the maximum length of a contiguous subarray
     * containing an equal number of zeros and ones
     */
    public static int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        int maxLength = 0;
        int prefixSum = 0;
        Map<Integer, Integer> prefixSumIndexes = new HashMap<>();
        prefixSumIndexes.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (prefixSumIndexes.containsKey(prefixSum)) {
                maxLength = Math.max(maxLength, i - prefixSumIndexes.get(prefixSum));
            }

            if (!prefixSumIndexes.containsKey(prefixSum)) {
                prefixSumIndexes.put(prefixSum, i);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        test(new int[]{0, 1}, 2);
        test(new int[]{0, 1, 0}, 2);
        test(new int[]{0, 1, 0, 1}, 4);
        test(new int[]{0, 0, 1, 0, 1, 1, 0}, 6);
        test(new int[]{1, 1, 1, 1}, 0);
        test(new int[]{0, 0, 0, 0}, 0);
        test(new int[]{1, 0, 0, 1, 0, 1}, 6);
        test(new int[]{0, 1, 1, 0, 1, 1, 1, 0}, 4);
        test(new int[]{0, 1}, 2);
    }

    private static void test(int[] nums, int expected) {
        int actual = findMaxLength(nums);

        System.out.printf(
                "Expected: %d, Actual: %d -> %s%n",
                expected,
                actual,
                expected == actual ? "PASS" : "FAIL"
        );
    }
}
