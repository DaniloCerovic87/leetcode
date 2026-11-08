package days.day01;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    /**
     * Checks whether the given array contains duplicate elements.
     * <p>
     * Return true if at least one integer appears more than once.
     * Return false if all elements are unique.
     * <p>
     * Examples:
     * [1, 2, 3, 1] -> true
     * [1, 2, 3, 4] -> false
     * [2, 2, 2]    -> true
     *
     * @param nums array of integers
     * @return true if duplicates exist, otherwise false
     */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> uniques = new HashSet<>();
        for (int i : nums) {
            if (!uniques.add(i)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        test(new int[]{1, 2, 3, 1}, true);
        test(new int[]{1, 2, 3, 4}, false);
        test(new int[]{2, 2, 2}, true);
        test(new int[]{5}, false);
        test(new int[]{}, false);
        test(new int[]{-1, -2, -3, -1}, true);
        test(new int[]{0, 1, 2, 0}, true);
        test(new int[]{1, 2, 2, 3}, true);
        test(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}, false);
        test(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, true);
    }

    private static void test(int[] nums, boolean expected) {
        boolean actual = containsDuplicate(nums);

        System.out.println(
                "Input: " + java.util.Arrays.toString(nums)
                        + " | Expected: " + expected
                        + " | Actual: " + actual
                        + " | " + (actual == expected ? "PASS" : "FAIL")
        );
    }
}