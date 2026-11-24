package days.day18;

public class CountOccurrences {
    /*
     * Day 18 - Binary Search
     * Task 1: CountOccurrences
     *
     * Problem: Return the number of occurrences of target in a sorted array. Return 0 if absent.
     * Input is sorted in non-decreasing order and may contain duplicates.
     * Handle empty arrays and targets outside the array range.
     * Use binary search without built-in search methods.
     * Time: O(log n), Space: O(1).
     */

    public static int countOccurrences(int[] nums, int target) {
        int upperBound = upperBound(nums, target);
        if (upperBound == 0) {
            return 0;
        }
        return upperBound - lowerBound(nums, target);
    }

    private static int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static int upperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        test(new int[]{1, 2, 2, 2, 4, 5}, 2, 3);
        test(new int[]{1, 2, 4, 5}, 3, 0);
        test(new int[]{2, 2, 2, 2}, 2, 4);
        test(new int[]{2, 2, 2, 2}, 3, 0);
        test(new int[]{1, 3, 5, 7}, 5, 1);
        test(new int[]{1, 2, 4, 5}, 0, 0);
        test(new int[]{1, 2, 4, 5}, 6, 0);
        test(new int[]{1, 1, 1, 2, 3}, 1, 3);
        test(new int[]{1, 2, 3, 3, 3}, 3, 3);
        test(new int[]{5}, 5, 1);
        test(new int[]{5}, 8, 0);
        test(new int[]{}, 3, 0);
        test(new int[]{-5, -2, -2, 0, 3}, -2, 2);
        test(new int[]{-3, 0, 0, 0, 4}, 0, 3);
    }

    private static void test(int[] nums, int target, int expected) {
        int actual = countOccurrences(nums, target);
        if (actual != expected) {
            throw new AssertionError("Expected " + expected + ", got " + actual);
        }
        System.out.println("PASS");
    }
}
