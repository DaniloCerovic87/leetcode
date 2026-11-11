package days.day04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString {

    /**
     * LeetCode 438 - Find All Anagrams in a String (Medium)
     * <p>
     * Given two strings s and p, return a list of all starting
     * indices of p's anagrams in s.
     * <p>
     * An anagram is a rearrangement of a string's characters,
     * preserving the frequency of each character.
     * <p>
     * The matching substrings must be contiguous.
     * The result may be returned in any order.
     * <p>
     * Both strings contain only lowercase English letters.
     * <p>
     * Examples:
     * s = "cbaebabacd", p = "abc" -> [0, 6]
     * s = "abab",       p = "ab"  -> [0, 1, 2]
     *
     * @param s the string in which to search
     * @param p the string whose anagrams are being searched for
     * @return all starting indices of anagrams of p in s
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int k = p.length();

        if (k > s.length()) {
            return result;
        }

        Map<Character, Integer> targetCharFreq = new HashMap<>();
        for (int i = 0; i < k; i++) {
            targetCharFreq.put(p.charAt(i), targetCharFreq.getOrDefault(p.charAt(i), 0) + 1);
        }

        Map<Character, Integer> currentCharFreq = new HashMap<>();
        for (int i = 0; i < k; i++) {
            currentCharFreq.put(s.charAt(i), currentCharFreq.getOrDefault(s.charAt(i), 0) + 1);
        }

        if (currentCharFreq.equals(targetCharFreq)) {
            result.add(0);
        }

        for (int i = 0; i < s.length() - k; i++) {
            char outgoing = s.charAt(i);
            char incoming = s.charAt(i + k);

            if (currentCharFreq.get(outgoing) == 1) {
                currentCharFreq.remove(outgoing);
            } else {
                currentCharFreq.put(outgoing, currentCharFreq.get(outgoing) - 1);
            }

            currentCharFreq.put(incoming, currentCharFreq.getOrDefault(incoming, 0) + 1);

            if (currentCharFreq.equals(targetCharFreq)) {
                result.add(i + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        test("cbaebabacd", "abc", List.of(0, 6));
        test("abab", "ab", List.of(0, 1, 2));
        test("baa", "aa", List.of(1));
        test("abc", "abcd", List.of());
        test("aaaa", "aa", List.of(0, 1, 2));
        test("abc", "abc", List.of(0));
        test("abcdef", "xyz", List.of());
        test("a", "a", List.of(0));
        test("a", "b", List.of());
    }

    private static void test(
            String s,
            String p,
            List<Integer> expected
    ) {
        List<Integer> actual = findAnagrams(s, p);

        System.out.printf(
                "s=%s, p=%s | Expected: %s | Actual: %s | %s%n",
                s,
                p,
                expected,
                actual,
                expected.equals(actual) ? "PASS" : "FAIL"
        );
    }
}
