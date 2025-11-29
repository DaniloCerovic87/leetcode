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
        int i = length - 1;
        while(i >= 0) {
            if(digits[i] < 9) {
                digits[i] = digits[i] + 1;
                break;
            }
            digits[i] = 0;
            i--;
        }

        if(digits[0] == 0) {
            int[] newArray = new int[length + 1];
            newArray[0] = 1;
            for(int j = 0; j < length; j++) {
                newArray[j + 1] = digits[j];
            }
            return newArray;
        } 
        return digits;
    }
}