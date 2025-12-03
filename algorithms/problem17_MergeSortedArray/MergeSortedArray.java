package problem17_MergeSortedArray;

import java.util.Arrays;

public class MergeSortedArray {

    public static void main(String[] args) {

        test(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);

        test(new int[]{0, 0}, 0, new int[]{4, 7}, 2);

        test(new int[]{3}, 1, new int[]{}, 0);

        test(new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3);

        test(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{4, 5, 6}, 3);

        test(new int[]{2, 2, 2, 0, 0, 0}, 3, new int[]{2, 2, 2}, 3);

        test(new int[]{1, 3, 5, 0, 0, 0}, 3, new int[]{2, 4, 6}, 3);

        test(new int[]{1, 1, 5, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3);

        test(new int[]{7, 8, 9, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3);

        test(new int[]{2, 4, 6, 0}, 3, new int[]{5}, 1);
    }

    private static void test(int[] nums1, int m, int[] nums2, int n) {
        int[] original1 = Arrays.copyOf(nums1, nums1.length);
        int[] original2 = Arrays.copyOf(nums2, nums2.length);

        merge(nums1, m, nums2, n);

        System.out.println("nums1 original: " + Arrays.toString(original1));
        System.out.println("nums2 original: " + Arrays.toString(original2));
        System.out.println("merged result : " + Arrays.toString(nums1));
        System.out.println("-------------------------------------");
    }

    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }

}
