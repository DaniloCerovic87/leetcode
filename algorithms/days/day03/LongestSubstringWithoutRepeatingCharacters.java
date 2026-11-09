package days.day03;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * LeetCode 3 - Longest Substring Without Repeating Characters
     * Difficulty: Medium
     * <p>
     * Given a string s, find the length of the longest
     * substring without repeating characters.
     * <p>
     * A substring is a contiguous sequence of characters
     * within a string.
     * <p>
     * Examples:
     * <p>
     * Input: s = "abcabcbb"
     * Output: 3
     * Explanation: "abc" is a valid longest substring.
     * <p>
     * Input: s = "bbbbb"
     * Output: 1
     * Explanation: "b" is the longest substring
     * without repeating characters.
     * <p>
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: "wke" is a valid longest substring.
     * Note that "pwke" is a subsequence, not a substring.
     * <p>
     * Constraints:
     * - 0 <= s.length() <= 50,000
     * - s consists of English letters, digits,
     * symbols, and spaces.
     *
     * @param s the input string
     * @return the length of the longest substring
     * containing no repeated characters
     */
    public static int lengthOfLongestSubstring(String s) {
        int maxCount = 0;
        int left = 0;

        Set<Character> uniqueChars = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            while (uniqueChars.contains(s.charAt(right))) {
                uniqueChars.remove(s.charAt(left));
                left++;
            }

            uniqueChars.add(s.charAt(right));
            maxCount = Math.max(maxCount, right - left + 1);
        }

        return maxCount;
    }

    public static void main(String[] args) {
        test("abcabcbb", 3);
        test("bbbbb", 1);
        test("pwwkew", 3);
        test("", 0);
        test("a", 1);
        test("abcdef", 6);
        test("abba", 2);
        test("dvdf", 3);
        test("anviaj", 5);
        test("tmmzuxt", 5);
        test("aab", 2);
        test("au", 2);
        test("aabaab!c", 4);
        test(" ", 1);
        test("a b c", 3);
        test("abcABC", 6);
        test("12345123", 5);
        test("ohvhjdml", 6);
        test("abc", 3);
    }

    private static void test(String s, int expected) {
        int actual = lengthOfLongestSubstring(s);
        boolean passed = actual == expected;

        System.out.println(
                (passed ? "PASS" : "FAIL")
                        + " | s=\"" + s + "\""
                        + " | Expected: " + expected
                        + " | Actual: " + actual
        );
    }
}
