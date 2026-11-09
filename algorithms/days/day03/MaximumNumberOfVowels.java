package days.day03;

import java.util.HashSet;
import java.util.Set;

public class MaximumNumberOfVowels {

    /**
     * LeetCode 1456 - Maximum Number of Vowels in a
     * Substring of Given Length (Medium)
     *
     * Given a string s and an integer k, return the maximum
     * number of vowel letters in any substring of s
     * with length k.
     *
     * Vowel letters in English are:
     * 'a', 'e', 'i', 'o', and 'u'.
     *
     * Examples:
     *
     * Input: s = "abciiidef", k = 3
     * Output: 3
     * Explanation: The substring "iii" contains 3 vowels.
     *
     * Input: s = "aeiou", k = 2
     * Output: 2
     *
     * Input: s = "leetcode", k = 3
     * Output: 2
     *
     * Constraints:
     * - 1 <= s.length() <= 100,000
     * - s consists of lowercase English letters
     * - 1 <= k <= s.length()
     *
     * @param s the input string
     * @param k the required substring length
     * @return the maximum number of vowels in any
     *         substring of length k
     */
    public static int maxVowels(String s, int k) {
        Set<Character> allVowels = new HashSet<>();
        allVowels.add('a');
        allVowels.add('e');
        allVowels.add('i');
        allVowels.add('o');
        allVowels.add('u');

        int currentCount = 0;

        for(int i = 0; i < k; i++) {
            if(allVowels.contains(s.charAt(i))) {
                currentCount++;
            }
        }

        int maxCount = currentCount;

        for(int i = 0; i < s.length() - k; i++) {
            int outgoing = allVowels.contains(s.charAt(i)) ? 1 : 0;
            int incoming = allVowels.contains(s.charAt(i + k)) ? 1 : 0;
            currentCount = currentCount - outgoing + incoming;
            maxCount = Math.max(maxCount, currentCount);
        }

        return maxCount;
    }

    public static void main(String[] args) {
        test("abciiidef", 3, 3);
        test("aeiou", 2, 2);
        test("leetcode", 3, 2);
        test("rhythms", 4, 0);
        test("tryhard", 4, 1);
        test("aaaaa", 3, 3);
        test("bbbbba", 2, 1);
        test("a", 1, 1);
        test("z", 1, 0);
        test("aeiou", 5, 5);
        test("abcde", 5, 2);
        test("abac", 2, 1);
        test("zzzaei", 3, 3);
        test("baaaab", 4, 4);
        test("aabb", 2, 2);
    }

    private static void test(String s, int k, int expected) {
        int actual = maxVowels(s, k);
        boolean passed = actual == expected;

        System.out.println(
            (passed ? "PASS" : "FAIL")
                + " | s=\"" + s + "\""
                + ", k=" + k
                + " | Expected: " + expected
                + " | Actual: " + actual
        );
    }
}
