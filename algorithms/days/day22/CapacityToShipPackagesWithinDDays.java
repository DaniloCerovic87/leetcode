package days.day22;

public class CapacityToShipPackagesWithinDDays {

    /*
     * LeetCode 1011 - Capacity To Ship Packages Within D Days
     * Difficulty: Medium
     *
     * A conveyor belt has packages that must be shipped from
     * one port to another within a given number of days.
     *
     * The weight of the i-th package is weights[i].
     *
     * Each day, we load the ship with packages in the order
     * given by weights. We may not load more weight than
     * the maximum weight capacity of the ship.
     *
     * Return the minimum ship capacity that allows all
     * packages to be shipped within the given number of days.
     *
     * Important:
     * - Packages must be shipped in their original order.
     * - A package cannot be split across multiple days.
     * - Multiple packages can be shipped on the same day.
     *
     * Example 1:
     * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
     * Output: 15
     *
     * Explanation:
     * Day 1: [1,2,3,4,5] -> 15
     * Day 2: [6,7]      -> 13
     * Day 3: [8]        -> 8
     * Day 4: [9]        -> 9
     * Day 5: [10]       -> 10
     *
     * Example 2:
     * Input: weights = [3,2,2,4,1,4], days = 3
     * Output: 6
     *
     * Explanation:
     * Day 1: [3,2] -> 5
     * Day 2: [2,4] -> 6
     * Day 3: [1,4] -> 5
     *
     * Example 3:
     * Input: weights = [1,2,3,1,1], days = 4
     * Output: 3
     *
     * Constraints:
     * - 1 <= days <= weights.length <= 50_000
     * - 1 <= weights[i] <= 500
     *
     * Requirements:
     * - Use binary search on the answer.
     * - Do not modify or sort the input array.
     * - Space complexity: O(1)
     */

    public static int shipWithinDays(int[] weights, int days) {
        int minCapacity = 0;
        int maxCapacity = 0;

        for (int w : weights) {
            minCapacity = Math.max(minCapacity, w);
            maxCapacity += w;
        }

        int left = minCapacity;
        int right = maxCapacity;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canShip(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean canShip(int[] weights, int days, int capacity) {
        int filled = 0;
        int daysPassed = 1;

        for (int w : weights) {
            if (filled + w > capacity) {
                daysPassed++;
                filled = 0;
            }

            if (daysPassed > days) {
                return false;
            }

            filled += w;
        }

        return true;
    }

    public static void main(String[] args) {

        // Standard examples
        test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5, 15);
        test(new int[]{3, 2, 2, 4, 1, 4}, 3, 6);
        test(new int[]{1, 2, 3, 1, 1}, 4, 3);

        // Single package
        test(new int[]{10}, 1, 10);
        test(new int[]{1}, 1, 1);

        // All packages must be shipped in one day
        test(new int[]{1, 2, 3, 4, 5}, 1, 15);
        test(new int[]{10, 20, 30}, 1, 60);

        // One package per day
        test(new int[]{1, 2, 3, 4, 5}, 5, 5);
        test(new int[]{10, 1, 1, 1}, 4, 10);

        // Identical package weights
        test(new int[]{5, 5, 5, 5}, 2, 10);
        test(new int[]{5, 5, 5, 5, 5}, 2, 15);

        // Package order matters
        test(new int[]{10, 10, 1, 1}, 2, 12);
        test(new int[]{1, 10, 10, 1}, 2, 11);

        // Uneven package weights
        test(new int[]{1, 1, 100, 1, 1}, 3, 100);

        // Maximum individual package weight
        test(new int[]{500, 500, 500, 500}, 2, 1000);

        System.out.println("All 15 tests passed!");
    }

    private static void test(int[] weights, int days, int expected) {
        int actual = shipWithinDays(weights, days);

        if (actual != expected) {
            throw new AssertionError(
                    "Days: " + days
                            + ", expected: " + expected
                            + ", but got: " + actual
            );
        }

        System.out.println(
                "PASS: days = " + days + ", capacity = " + actual
        );
    }
}