package days.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Day 12 - LeetCode 56: Merge Intervals
 *
 * Problem:
 * Given an array of intervals where intervals[i] = [start, end],
 * merge all overlapping intervals.
 *
 * Return an array of non-overlapping intervals that cover
 * all intervals in the input.
 *
 * Requirements:
 * 1. Merge all overlapping intervals.
 * 2. Return the merged intervals sorted by their start values.
 * 3. Intervals sharing an endpoint are considered overlapping.
 * 4. The input intervals are not necessarily sorted.
 *
 * Example 1:
 * Input:  [[1,3], [2,6], [8,10], [15,18]]
 * Output: [[1,6], [8,10], [15,18]]
 *
 * Example 2:
 * Input:  [[1,4], [4,5]]
 * Output: [[1,5]]
 *
 * Example 3:
 * Input:  [[1,4], [0,2], [3,5]]
 * Output: [[0,5]]
 *
 * Constraints:
 * - 1 <= intervals.length <= 10000
 * - intervals[i].length == 2
 * - 0 <= start <= end <= 10000
 *
 * Target complexity:
 * Time:  O(n log n)
 * Space: O(n) for the result
 */
public class MergeIntervals {

    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals,
                Comparator.comparingInt(a -> a[0]));

        List<int[]> result = new ArrayList<>();

        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];

            if (currentStart > end) {
                result.add(new int[]{start, end});
                start = currentStart;
                end = currentEnd;
            } else {
                end = Math.max(end, currentEnd);
            }
        }

        result.add(new int[]{start, end});

        return result.toArray(new int[][]{});
    }

    public static void main(String[] args) {

        // Test 1: Basic overlapping intervals
        test(
                new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}},
                new int[][]{{1, 6}, {8, 10}, {15, 18}}
        );

        // Test 2: Intervals sharing an endpoint
        test(
                new int[][]{{1, 4}, {4, 5}},
                new int[][]{{1, 5}}
        );

        // Test 3: Unsorted intervals
        test(
                new int[][]{{1, 4}, {0, 2}, {3, 5}},
                new int[][]{{0, 5}}
        );

        // Test 4: No overlapping intervals
        test(
                new int[][]{{1, 2}, {4, 5}, {7, 9}},
                new int[][]{{1, 2}, {4, 5}, {7, 9}}
        );

        // Test 5: All intervals overlap
        test(
                new int[][]{{1, 5}, {2, 6}, {3, 8}},
                new int[][]{{1, 8}}
        );

        // Test 6: One interval
        test(
                new int[][]{{1, 10}},
                new int[][]{{1, 10}}
        );

        // Test 7: Nested intervals
        test(
                new int[][]{{1, 10}, {2, 3}, {4, 8}},
                new int[][]{{1, 10}}
        );

        // Test 8: Same start values
        test(
                new int[][]{{1, 3}, {1, 5}, {1, 2}},
                new int[][]{{1, 5}}
        );

        // Test 9: Chain of overlapping intervals
        test(
                new int[][]{{1, 3}, {2, 4}, {4, 6}, {8, 10}},
                new int[][]{{1, 6}, {8, 10}}
        );
    }

    private static void test(int[][] intervals, int[][] expected) {
        int[][] actual = merge(intervals);

        boolean passed = Arrays.deepEquals(actual, expected);

        System.out.println(
                (passed ? "PASS" : "FAIL")
                        + " | Expected: " + Arrays.deepToString(expected)
                        + " | Actual: " + Arrays.deepToString(actual)
        );
    }
}
