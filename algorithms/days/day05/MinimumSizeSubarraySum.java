package days.day05;

public class MinimumSizeSubarraySum {

    /**
     * LeetCode 209 - Minimum Size Subarray Sum (Medium)
     * <p>
     * Given an array of positive integers nums and a positive
     * integer target, return the minimal length of a contiguous
     * subarray whose sum is greater than or equal to target.
     * <p>
     * If no such subarray exists, return 0.
     * <p>
     * Example:
     * target = 7
     * nums = [2, 3, 1, 2, 4, 3]
     * Output: 2
     * <p>
     * Explanation:
     * The subarray [4, 3] has the minimal length of 2.
     *
     * @param target the minimum required subarray sum
     * @param nums   an array of positive integers
     * @return the minimum length of a qualifying subarray,
     * or 0 if no such subarray exists
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int minCount = Integer.MAX_VALUE;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target && left <= right) {
                minCount = Math.min(minCount, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        if (minCount == Integer.MAX_VALUE) {
            return 0;
        }

        return minCount;
    }

    public static void main(String[] args) {
        test(7, new int[]{2, 3, 1, 2, 4, 3}, 2);
        test(4, new int[]{1, 4, 4}, 1);
        test(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}, 0);
        test(15, new int[]{1, 2, 3, 4, 5}, 5);
        test(6, new int[]{1, 2, 3}, 3);
        test(3, new int[]{3}, 1);
        test(5, new int[]{1}, 0);
        test(5, new int[]{5, 1, 1}, 1);
    }

    private static void test(int target, int[] nums, int expected) {
        int actual = minSubArrayLen(target, nums);

        System.out.printf(
                "Expected: %d, Actual: %d -> %s%n",
                expected,
                actual,
                expected == actual ? "PASS" : "FAIL"
        );
    }
}
