package problem08_RemoveElement;

import java.util.Arrays;

public class RemoveElement {

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 2, 1};
        int k1 = removeElement(nums1, 2);
        System.out.println("k = " + k1
                + ", nums = " + Arrays.toString(Arrays.copyOf(nums1, k1)));

        // Example 2
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int k2 = removeElement(nums2, 2);
        System.out.println("k = " + k2
                + ", nums = " + Arrays.toString(Arrays.copyOf(nums2, k2)));

        int[] nums3 = {3, 2, 2, 3};
        int k3 = removeElement(nums3, 3);
        System.out.println("k = " + k1
                + ", nums = " + Arrays.toString(Arrays.copyOf(nums3, k3)));

    }

    private static int removeElement(int[] nums, int val) {
        int k = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

}