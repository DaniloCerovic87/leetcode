package days.day04;

import java.util.HashMap;
import java.util.Map;

public class PermutationInString {

    /**
     * LeetCode 567 - Permutation in String (Medium)
     *
     * Given two strings s1 and s2, return true if s2 contains
     * a permutation of s1, or false otherwise.
     *
     * Both strings contain only lowercase English letters.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param s1 the string whose permutation we are looking for
     * @param s2 the string in which to search
     * @return true if s2 contains a permutation of s1
     */
    public static boolean checkInclusion(String s1, String s2) {
        int k = s1.length();

        if (k > s2.length()) {
            return false;
        }

        Map<Character, Integer> s1Frequencies = new HashMap<>();
        Map<Character, Integer> windowFrequencies = new HashMap<>();

        for (int i = 0; i < k; i++) {
            char s1Char = s1.charAt(i);
            char s2Char = s2.charAt(i);

            s1Frequencies.put(
                    s1Char,
                    s1Frequencies.getOrDefault(s1Char, 0) + 1
            );

            windowFrequencies.put(
                    s2Char,
                    windowFrequencies.getOrDefault(s2Char, 0) + 1
            );
        }

        if (s1Frequencies.equals(windowFrequencies)) {
            return true;
        }

        for (int i = 0; i < s2.length() - k; i++) {
            char outgoing = s2.charAt(i);
            char incoming = s2.charAt(i + k);

            int frequency = windowFrequencies.get(outgoing);

            if (frequency == 1) {
                windowFrequencies.remove(outgoing);
            } else {
                windowFrequencies.put(outgoing, frequency - 1);
            }

            windowFrequencies.put(
                    incoming,
                    windowFrequencies.getOrDefault(incoming, 0) + 1
            );

            if (s1Frequencies.equals(windowFrequencies)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        test("ab", "eidbaooo", true);
        test("ab", "eidboaoo", false);
        test("aab", "xxaba", true);
        test("abc", "bbbca", true);
        test("abc", "ccccbbb", false);
        test("a", "a", true);
        test("ab", "a", false);
        test("aa", "ab", false);
        test("adc", "dcda", true);
        test("ab", "cab", true);
        test("abcd", "ab", false);
        test("abc", "abc", true);
    }

    private static void test(String s1, String s2, boolean expected) {
        boolean actual = checkInclusion(s1, s2);

        System.out.printf(
                "s1=%s, s2=%s | Expected: %s | Actual: %s | %s%n",
                s1,
                s2,
                expected,
                actual,
                expected == actual ? "PASS" : "FAIL"
        );
    }
}
