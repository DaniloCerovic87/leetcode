package problem02_PalindromeNumber;

public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome(10));
    }

    private static boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        return s.contentEquals(new StringBuilder(s).reverse());
    }

}
