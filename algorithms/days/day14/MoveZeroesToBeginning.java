package days.day14;

import java.util.Arrays;

public class MoveZeroesToBeginning {

    /**
     * Move Zeroes to Beginning (Stable, In-Place)
     *
     * Given an integer array nums, move all zeroes to the
     * beginning while maintaining the relative order of
     * all non-zero elements.
     *
     * Modify the input array in-place.
     *
     * Approach: Reverse Read/Write Two Pointers
     *
     * Time Complexity: O(n)
     * Auxiliary Space Complexity: O(1)
     *
     * @param nums the array to modify in-place
     */
    public static void moveZeroesToBeginning(int[] nums) {
        int length = nums.length;
        int write = length - 1;

        for (int read = length - 1; read >= 0; read--) {
            if (nums[read] != 0) {
                nums[write] = nums[read];
                write--;
            }
        }

        for (int i = 0; i <= write; i++) {
            nums[i] = 0;
        }
    }

    private static void test(String name, int[] input, int[] expected) {
        moveZeroesToBeginning(input);

        boolean passed = Arrays.equals(input, expected);

        System.out.println((passed ? "PASS" : "FAIL") + " - " + name);

        if (!passed) {
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Actual:   " + Arrays.toString(input));
            throw new AssertionError("Test failed: " + name);
        }
    }

    public static void main(String[] args) {

        test("Document example",
                new int[]{10, 0, 0, 15, 0, 0, 10, 10},
                new int[]{0, 0, 0, 0, 10, 15, 10, 10});

        test("Standard example",
                new int[]{0, 1, 0, 3, 12},
                new int[]{0, 0, 1, 3, 12});

        test("All zeroes",
                new int[]{0, 0, 0, 0},
                new int[]{0, 0, 0, 0});

        test("No zeroes",
                new int[]{1, 2, 3, 4},
                new int[]{1, 2, 3, 4});

        test("Single zero",
                new int[]{0},
                new int[]{0});

        test("Single non-zero",
                new int[]{5},
                new int[]{5});

        test("Zeroes at the beginning",
                new int[]{0, 0, 1, 2, 3},
                new int[]{0, 0, 1, 2, 3});

        test("Zeroes at the end",
                new int[]{1, 2, 3, 0, 0},
                new int[]{0, 0, 1, 2, 3});

        test("Alternating zeroes",
                new int[]{0, 1, 0, 2, 0, 3},
                new int[]{0, 0, 0, 1, 2, 3});

        test("Negative numbers and zeroes",
                new int[]{0, -1, 0, -3, 2, 0},
                new int[]{0, 0, 0, -1, -3, 2});

        System.out.println("All 10 tests passed!");
    }
}
