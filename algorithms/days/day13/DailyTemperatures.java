package days.day13;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DailyTemperatures {

    /**
     * LeetCode 739 - Daily Temperatures (Medium)
     *
     * Given an array of daily temperatures, returns an array
     * where each element represents the number of days until
     * a strictly warmer temperature.
     *
     * Uses a monotonic decreasing stack of indices.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param temperatures array of daily temperatures
     * @return days until the next warmer temperature,
     *         or 0 if no warmer day exists
     */
    public static int[] dailyTemperatures(int[] temperatures) {

        int[] results = new int[temperatures.length];

        Deque<Integer> monotonicStack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!monotonicStack.isEmpty()
                    && temperatures[monotonicStack.peek()] < temperatures[i]) {

                int prevLowerIndex = monotonicStack.pop();
                results[prevLowerIndex] = i - prevLowerIndex;
            }

            monotonicStack.push(i);
        }

        return results;
    }

    private static void test(String name, int[] input, int[] expected) {
        int[] actual = dailyTemperatures(input);
        boolean passed = Arrays.equals(expected, actual);

        System.out.println(
                (passed ? "PASS" : "FAIL") + " - " + name
        );

        if (!passed) {
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Actual:   " + Arrays.toString(actual));
            throw new AssertionError("Test failed: " + name);
        }
    }

    public static void main(String[] args) {

        test("Standard example",
                new int[]{73, 74, 75, 71, 69, 72, 76, 73},
                new int[]{1, 1, 4, 2, 1, 1, 0, 0});

        test("Increasing temperatures",
                new int[]{30, 40, 50, 60},
                new int[]{1, 1, 1, 0});

        test("Three increasing temperatures",
                new int[]{30, 60, 90},
                new int[]{1, 1, 0});

        test("Decreasing temperatures",
                new int[]{90, 80, 70, 60},
                new int[]{0, 0, 0, 0});

        test("All equal temperatures",
                new int[]{70, 70, 70, 70},
                new int[]{0, 0, 0, 0});

        test("Single temperature",
                new int[]{75},
                new int[]{0});

        test("Warmer after several days",
                new int[]{75, 71, 69, 72, 76},
                new int[]{4, 2, 1, 1, 0});

        test("Repeated temperatures",
                new int[]{73, 73, 75, 73, 76},
                new int[]{2, 1, 2, 1, 0});

        test("Warmer temperature at the end",
                new int[]{80, 70, 60, 90},
                new int[]{3, 2, 1, 0});

        test("Mixed temperatures",
                new int[]{60, 65, 62, 70, 68, 75},
                new int[]{1, 2, 1, 2, 1, 0});

        System.out.println("All 10 tests passed!");
    }
}
