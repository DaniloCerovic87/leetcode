package days.day15;

public class SearchInsertPosition {

    /**
     * LeetCode 35 - Search Insert Position (Easy)
     *
     * Given a sorted array of distinct integers and a target,
     * return the index if the target is found.
     *
     * If the target is not found, return the index where it
     * would be inserted to maintain the sorted order.
     *
     * The algorithm must run in O(log n) time.
     *
     * Examples:
     * nums = [1, 3, 5, 6], target = 5 -> 2
     * nums = [1, 3, 5, 6], target = 2 -> 1
     * nums = [1, 3, 5, 6], target = 7 -> 4
     *
     * Requirements:
     * - Time Complexity: O(log n)
     * - Auxiliary Space Complexity: O(1)
     *
     * @param nums sorted array of distinct integers
     * @param target value to find or insert
     * @return index of target or its insertion position
     */
    public static int searchInsert(int[] nums, int target) {
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

    private static void test(
            String name,
            int[] nums,
            int target,
            int expected) {

        int actual = searchInsert(nums, target);
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

        test("Target exists",
                new int[]{1, 3, 5, 6},
                5, 2);

        test("Insert in the middle",
                new int[]{1, 3, 5, 6},
                2, 1);

        test("Insert at the end",
                new int[]{1, 3, 5, 6},
                7, 4);

        test("Insert at the beginning",
                new int[]{1, 3, 5, 6},
                0, 0);

        test("First element",
                new int[]{1, 3, 5, 6},
                1, 0);

        test("Last element",
                new int[]{1, 3, 5, 6},
                6, 3);

        test("Insert between last two",
                new int[]{1, 3, 5, 6},
                4, 2);

        test("Single element found",
                new int[]{5},
                5, 0);

        test("Single element smaller target",
                new int[]{5},
                2, 0);

        test("Single element larger target",
                new int[]{5},
                8, 1);

        test("Negative numbers",
                new int[]{-10, -5, 0, 5, 10},
                -3, 2);

        test("Two elements",
                new int[]{2, 8},
                5, 1);

        System.out.println("All 12 tests passed!");
    }
}
