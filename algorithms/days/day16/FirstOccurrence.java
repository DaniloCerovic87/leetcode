package days.day16;

public class FirstOccurrence {

    /**
     * Returns the index of the first occurrence
     * of target in a sorted array.
     *
     * @return first index, or -1 if not found
     */
    public static int firstOccurrence(int[] nums, int target) {
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

        return left < nums.length && nums[left] == target ? left : -1;
    }

    public static void main(String[] args) {
        test(new int[]{1, 2, 2, 2, 4}, 2, 1);
        test(new int[]{1, 2, 3, 4}, 3, 2);
        test(new int[]{1, 2, 3, 4}, 5, -1);
        test(new int[]{1, 2, 3, 4}, 0, -1);
        test(new int[]{2, 2, 2, 2}, 2, 0);
        test(new int[]{1, 1, 2, 3}, 1, 0);
        test(new int[]{1, 2, 3, 3}, 3, 2);
        test(new int[]{5}, 5, 0);
        test(new int[]{5}, 3, -1);
        test(new int[]{}, 3, -1);
    }

    private static void test(int[] nums, int target, int expected) {
        int actual = firstOccurrence(nums, target);
        if (actual != expected) {
            throw new AssertionError(
                    "Expected " + expected + ", got " + actual
            );
        }
        System.out.println("PASS");
    }
}
