package days.day16;

public class LastOccurrence {

    /**
     * Finds the last occurrence of target
     * in a sorted array.
     * <p>
     * Time: O(log n)
     * Space: O(1)
     *
     * @return last index of target, or -1 if absent
     */
    public static int lastOccurrence(int[] nums, int target) {
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

        if (left == 0) {
            return -1;
        }

        return nums[left - 1] == target ? left - 1 : -1;
    }

    public static void main(String[] args) {
        test(new int[]{1, 2, 2, 2, 4, 5}, 2, 3);
        test(new int[]{1, 2, 3, 3, 3, 7}, 3, 4);
        test(new int[]{1, 2, 4, 5}, 3, -1);
        test(new int[]{2, 2, 2, 2}, 2, 3);
        test(new int[]{1, 3, 5}, 8, -1);
        test(new int[]{1, 3, 5}, 0, -1);
        test(new int[]{1, 2, 3}, 1, 0);
        test(new int[]{1, 2, 3}, 3, 2);
        test(new int[]{5}, 5, 0);
        test(new int[]{}, 5, -1);
    }

    private static void test(int[] nums, int target, int expected) {
        int actual = lastOccurrence(nums, target);

        if (actual != expected) {
            throw new AssertionError(
                    "Expected " + expected + ", got " + actual
            );
        }

        System.out.println("PASS");
    }
}
