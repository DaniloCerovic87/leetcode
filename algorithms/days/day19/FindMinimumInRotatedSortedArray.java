package days.day19;

public class FindMinimumInRotatedSortedArray {
    /*
     * LeetCode 153 - Find Minimum in Rotated Sorted Array
     * Difficulty: Medium
     * Given a sorted array of unique integers rotated between
     * 1 and n times, return the minimum element.
     * Examples: [3,4,5,1,2] -> 1; [11,13,15,17] -> 11.
     * Constraints: 1 <= nums.length <= 5000;
     * -5000 <= nums[i] <= 5000; all elements are unique.
     * Time: O(log n), Space: O(1).
     */
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        test(new int[]{3, 4, 5, 1, 2}, 1);
        test(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        test(new int[]{11, 13, 15, 17}, 11);
        test(new int[]{7}, 7);
        test(new int[]{1, 2}, 1);
        test(new int[]{2, 1}, 1);
        test(new int[]{5, 1, 2, 3, 4}, 1);
        test(new int[]{4, 5, 1, 2, 3}, 1);
        test(new int[]{3, 4, 5, 1, 2}, 1);
        test(new int[]{2, 3, 4, 5, 1}, 1);
        test(new int[]{-5, -4, -3, -2, -1}, -5);
        test(new int[]{-2, -1, -5, -4, -3}, -5);
        test(new int[]{3, 5, 7, -4, -2, 0}, -4);
        test(new int[]{0, 1, 2, -3, -2, -1}, -3);
        test(new int[]{5000, -5000}, -5000);
        System.out.println("All 15 tests passed!");
    }

    private static void test(int[] nums, int expected) {
        int actual = findMin(nums);
        if (actual != expected) {
            throw new AssertionError("Expected: " + expected + ", but got: " + actual);
        }
        System.out.println("PASS: " + actual);
    }
}
