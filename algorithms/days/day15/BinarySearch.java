package days.day15;

public class BinarySearch {

    /**
     * LeetCode 704 - Binary Search (Easy)
     * <p>
     * Given an array of integers nums sorted in ascending
     * order and an integer target, return the index of
     * target if it exists, or -1 otherwise.
     * <p>
     * All integers in nums are unique.
     * <p>
     * Requirements:
     * - Time Complexity: O(log n)
     * - Auxiliary Space Complexity: O(1)
     * <p>
     * Example:
     * Input: nums = [-1, 0, 3, 5, 9, 12], target = 9
     * Output: 4
     *
     * @param nums   sorted array of unique integers
     * @param target value to search for
     * @return index of target, or -1 if not found
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    private static void test(
            String name,
            int[] nums,
            int target,
            int expected) {

        int actual = search(nums, target);
        boolean passed = actual == expected;

        System.out.println(
                (passed ? "PASS" : "FAIL") + " - " + name
        );

        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            throw new AssertionError("Test failed: " + name);
        }
    }

    public static void main(String[] args) {

        test("Standard example",
                new int[]{-1, 0, 3, 5, 9, 12},
                9, 4);

        test("Target not found",
                new int[]{-1, 0, 3, 5, 9, 12},
                2, -1);

        test("First element",
                new int[]{1, 3, 5, 7, 9},
                1, 0);

        test("Last element",
                new int[]{1, 3, 5, 7, 9},
                9, 4);

        test("Middle element",
                new int[]{1, 3, 5, 7, 9},
                5, 2);

        test("Single element found",
                new int[]{7},
                7, 0);

        test("Single element not found",
                new int[]{7},
                3, -1);

        test("Target smaller than all",
                new int[]{10, 20, 30, 40},
                5, -1);

        test("Target larger than all",
                new int[]{10, 20, 30, 40},
                50, -1);

        test("Two elements",
                new int[]{2, 4},
                4, 1);

        System.out.println("All 10 tests passed!");
    }
}
