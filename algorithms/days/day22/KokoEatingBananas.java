package days.day22;

public class KokoEatingBananas {

    /*
     * LeetCode 875 - Koko Eating Bananas
     * Difficulty: Medium
     *
     * Koko loves to eat bananas. There are n piles of bananas,
     * where piles[i] represents the number of bananas
     * in the i-th pile.
     *
     * Koko has h hours to eat all the bananas.
     *
     * She can decide her eating speed k (bananas per hour).
     * Each hour, she chooses one pile and eats up to k bananas
     * from that pile.
     *
     * If the pile contains fewer than k bananas, she eats
     * all of them and does not eat from another pile
     * during that hour.
     *
     * Return the minimum integer k such that Koko can eat
     * all the bananas within h hours.
     *
     * Example 1:
     * Input: piles = [3, 6, 7, 11], h = 8
     * Output: 4
     *
     * Example 2:
     * Input: piles = [30, 11, 23, 4, 20], h = 5
     * Output: 30
     *
     * Example 3:
     * Input: piles = [30, 11, 23, 4, 20], h = 6
     * Output: 23
     *
     * Constraints:
     * - 1 <= piles.length <= 10^4
     * - piles.length <= h <= 10^9
     * - 1 <= piles[i] <= 10^9
     *
     * Requirements:
     * - Use binary search on the answer.
     * - Time complexity: O(n * log(max(piles)))
     * - Space complexity: O(1)
     */

    public static int minEatingSpeed(int[] piles, int h) {
        int max = 0;

        for (int p : piles) {
            max = Math.max(p, max);
        }

        int left = 1;
        int right = max;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canEat(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean canEat(int[] piles, int h, int k) {
        long totalHours = 0;

        for (int p : piles) {
            totalHours += p / k + (p % k != 0 ? 1 : 0);

            if (totalHours > h) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        // Standard examples
        test(new int[]{3, 6, 7, 11}, 8, 4);
        test(new int[]{30, 11, 23, 4, 20}, 5, 30);
        test(new int[]{30, 11, 23, 4, 20}, 6, 23);

        // Single pile
        test(new int[]{10}, 5, 2);
        test(new int[]{10}, 3, 4);
        test(new int[]{10}, 1, 10);
        test(new int[]{1}, 1, 1);

        // Minimum possible speed
        test(new int[]{1, 1, 1, 1, 1}, 5, 1);
        test(new int[]{3, 6, 7, 11}, 27, 1);

        // Identical piles
        test(new int[]{5, 5, 5, 5}, 4, 5);
        test(new int[]{5, 5, 5, 5}, 8, 3);

        // Unsorted piles
        test(new int[]{9, 1, 7, 3}, 7, 4);

        // Large values and potential integer overflow
        test(new int[]{1_000_000_000}, 1, 1_000_000_000);

        test(new int[]{1_000_000_000, 1_000_000_000},
                4, 500_000_000);

        test(new int[]{
                1_000_000_000,
                1_000_000_000,
                1_000_000_000
        }, 1_000_000_000, 4);

        System.out.println("All 15 tests passed!");
    }

    private static void test(int[] piles, int h, int expected) {
        int actual = minEatingSpeed(piles, h);

        if (actual != expected) {
            throw new AssertionError(
                    "Hours: " + h
                            + ", expected: " + expected
                            + ", but got: " + actual
            );
        }

        System.out.println(
                "PASS: h = " + h + ", speed = " + actual
        );
    }
}