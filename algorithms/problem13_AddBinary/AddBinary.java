package problem13_AddBinary;

class AddBinary {

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("1010", "1011"));
    }

    private static String addBinary(String a, String b) {
        int lengthA = a.length();
        int lengthB = b.length();

        if (lengthA > lengthB) {
            b = "0".repeat(lengthA - lengthB) + b;
        } else {
            a = "0".repeat(lengthB - lengthA) + a;
        }

        StringBuilder sb = new StringBuilder();

        int carry = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            int sum = (a.charAt(i) - '0') + (b.charAt(i) - '0') + carry;
            if (sum == 1) {
                sb.append("1");
                carry = 0;
            } else if (sum == 2) {
                sb.append("0");
                carry = 1;
            } else if (sum == 3) {
                sb.append("1");
                carry = 1;
            } else {
                sb.append("0");
            }
        }

        if (carry == 1) {
            sb.append("1");
        }

        return sb.reverse().toString();
    }
}