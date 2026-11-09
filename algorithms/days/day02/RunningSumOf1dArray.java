package days.day02;

import java.util.Arrays;

public class RunningSumOf1dArray {

    /**
     * LeetCode 1480 - Running Sum of 1d Array (Easy)
     *
     * Given an array nums, return an array runningSum where:
     *
     * runningSum[i] = nums[0] + nums[1] + ... + nums[i]
     *
     * Examples:
     *
     * Input:  [1, 2, 3, 4]
     * Output: [1, 3, 6, 10]
     *
     * Input:  [1, 1, 1, 1, 1]
     * Output: [1, 2, 3, 4, 5]
     *
     * Constraints:
     * - 1 <= nums.length <= 1000
     * - -10^6 <= nums[i] <= 10^6
     *
     * @param nums the input array of integers
     * @return an array containing the running sums
     */
    public static int[] runningSum(int[] nums) {
        int[] prefixSums = new int[nums.length];
        prefixSums[0] = nums[0];

        for(int i = 1; i < nums.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + nums[i];
        }

        return prefixSums;
    }

    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4},
             new int[]{1, 3, 6, 10});

        test(new int[]{1, 1, 1, 1, 1},
             new int[]{1, 2, 3, 4, 5});

        test(new int[]{3, 1, 2, 10, 1},
             new int[]{3, 4, 6, 16, 17});

        test(new int[]{5},
             new int[]{5});

        test(new int[]{-1, 2, -3, 4},
             new int[]{-1, 1, -2, 2});

        test(new int[]{0, 0, 0},
             new int[]{0, 0, 0});
    }

    private static void test(int[] nums, int[] expected) {
        int[] actual = runningSum(nums);

        if (Arrays.equals(expected, actual)) {
            System.out.println("PASS: " + Arrays.toString(nums));
        } else {
            System.out.println(
                "FAIL: " + Arrays.toString(nums)
                + " | Expected: " + Arrays.toString(expected)
                + " | Actual: " + Arrays.toString(actual)
            );
        }
    }
}
