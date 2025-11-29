package problem12_PlusOne;

import java.util.Arrays;

class PlusOne {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(plusOne(new int[]{4, 3, 2, 1})));
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9})));
    }

    private static int[] plusOne(int[] digits) {
        int length = digits.length;
        for(int i = length - 1; i >= 0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
            i--;
        }

        int[] newArray = new int[length + 1];
        newArray[0] = 1;
        return newArray;
    }

}