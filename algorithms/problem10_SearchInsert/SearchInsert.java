package problem10_SearchInsert;

public class SearchInsert {

    public static void main(String[] args) {
        System.out.println(searchInsert( new int[]{1, 3, 5, 6}, 5));
        System.out.println(searchInsert( new int[]{1, 3, 5, 6}, 2));
        System.out.println(searchInsert( new int[]{1, 3, 5, 6}, 7));
    }

    private static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2; //to prevent potential overflow if right and left are too big
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

}