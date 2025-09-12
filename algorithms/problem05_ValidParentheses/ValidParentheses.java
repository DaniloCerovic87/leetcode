package problem05_ValidParentheses;

import java.util.Stack;

public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([])"));
        System.out.println(isValid("([)]"));

    }

    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char current : s.toCharArray()) {
            if (current == '(' || current == '[' || current == '{') {
                stack.push(current);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char previous = stack.pop();
                if (current == ')' && previous != '(' ||
                        current == ']' && previous != '[' ||
                        current == '}' && previous != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

}
