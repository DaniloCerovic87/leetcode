package problem15_ClimbingStars;

public class ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(4));
        System.out.println(climbStairs(5));
    }

    private static int climbStairs(int n) {
        if(n <= 2) {
            return n;
        }

        int one = 1;
        int two = 2;

        for(int i = 3; i <= n; i++) {
            int three = one + two;
            one = two;
            two = three;
        }

        return two;
    }

}
