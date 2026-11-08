package days.day01;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    /**
     * Given two strings s and t, return true if t is
     * an anagram of s, and false otherwise.
     *
     * An anagram contains exactly the same characters
     * with the same frequencies, but possibly in a
     * different order.
     *
     * Both strings contain only lowercase English letters.
     *
     * Examples:
     * s = "anagram", t = "nagaram" -> true
     * s = "rat", t = "car"         -> false
     * s = "listen", t = "silent"   -> true
     *
     * @param s the first string
     * @param t the second string
     * @return true if the strings are anagrams
     */
    public static boolean isAnagramUsingFrequencyArray(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] frequencies = new int[26];

        for (int i = 0; i < s.length(); i++) {
            frequencies[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';

            if (frequencies[index] == 0) {
                return false;
            }

            frequencies[index]--;
        }

        return true;
    }

    public static boolean isAnagramUsingHashMap(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        Map<Character,Integer> first = new HashMap<>();
        for(char c : s.toCharArray()) {
            first.put(c, first.getOrDefault(c, 0) + 1);
        }

        for(char c : t.toCharArray()) {
            Integer current = first.get(c);
            if(current == null || current == 0) {
                return false;
            }

            first.put(c, current - 1);
        }

        return true;
    }

    public static void main(String[] args) {
        test("anagram", "nagaram", true);
        test("rat", "car", false);
        test("listen", "silent", true);
        test("a", "a", true);
        test("a", "b", false);
        test("ab", "a", false);
        test("aa", "ab", false);
        test("aabb", "bbaa", true);
        test("aabb", "abbb", false);
        test("abc", "cba", true);
        test("abc", "abd", false);
        test("", "", true);
        test("aacc", "ccac", false);
        test("abcdefghijklmnopqrstuvwxyz",
             "zyxwvutsrqponmlkjihgfedcba", true);
    }

    private static void test(String s, String t, boolean expected) {
        boolean actual = isAnagramUsingFrequencyArray(s, t);

        System.out.println(
                "s: \"" + s + "\""
                + " | t: \"" + t + "\""
                + " | Expected: " + expected
                + " | Actual: " + actual
                + " | " + (actual == expected ? "PASS" : "FAIL")
        );
    }
}
