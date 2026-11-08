package days.day01;

import java.util.*;

public class GroupAnagrams {

    /**
     * Given an array of strings, group the anagrams together.
     *
     * An anagram is a word formed by rearranging the letters
     * of another word, using every letter exactly once.
     *
     * Each string contains only lowercase English letters.
     *
     * The order of the groups does not matter.
     * The order of strings within each group does not matter.
     *
     * Examples:
     *
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * Output: [["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]
     *
     * Input: [""]
     * Output: [[""]]
     *
     * Input: ["a"]
     * Output: [["a"]]
     *
     * @param strs an array of lowercase English strings
     * @return a list of groups containing anagrams
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramsByKey  = new HashMap<>();
        for(String s : strs) {
            String sorted = sortChars(s);
            anagramsByKey
                    .computeIfAbsent(sorted, key -> new ArrayList<>())
                    .add(s);
        }

        return new ArrayList<>(anagramsByKey .values());
    }

    private static String sortChars(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        test(
                new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                List.of(
                        List.of("eat", "tea", "ate"),
                        List.of("tan", "nat"),
                        List.of("bat")
                )
        );

        test(
                new String[]{""},
                List.of(List.of(""))
        );

        test(
                new String[]{"a"},
                List.of(List.of("a"))
        );

        test(
                new String[]{"abc", "bca", "cab"},
                List.of(List.of("abc", "bca", "cab"))
        );

        test(
                new String[]{"abc", "def", "ghi"},
                List.of(
                        List.of("abc"),
                        List.of("def"),
                        List.of("ghi")
                )
        );

        test(
                new String[]{"a", "b", "a", "b"},
                List.of(
                        List.of("a", "a"),
                        List.of("b", "b")
                )
        );

        test(
                new String[]{"ab", "ba", "abc", "cab", "bca", "xyz"},
                List.of(
                        List.of("ab", "ba"),
                        List.of("abc", "cab", "bca"),
                        List.of("xyz")
                )
        );

        test(
                new String[]{"", "", "a", ""},
                List.of(
                        List.of("", "", ""),
                        List.of("a")
                )
        );

        test(
                new String[]{},
                List.of()
        );

        test(
                new String[]{"aab", "aba", "baa", "abb", "bab", "bba"},
                List.of(
                        List.of("aab", "aba", "baa"),
                        List.of("abb", "bab", "bba")
                )
        );
    }

    private static void test(
            String[] input,
            List<List<String>> expected
    ) {
        List<List<String>> actual = groupAnagrams(input);

        boolean passed = normalize(actual).equals(normalize(expected));

        System.out.println(
                "Input: " + Arrays.toString(input)
                + " | Expected: " + expected
                + " | Actual: " + actual
                + " | " + (passed ? "PASS" : "FAIL")
        );
    }

    private static List<List<String>> normalize(
            List<List<String>> groups
    ) {
        List<List<String>> normalized = new ArrayList<>();

        for (List<String> group : groups) {
            List<String> sortedGroup = new ArrayList<>(group);
            sortedGroup.sort(String::compareTo);
            normalized.add(sortedGroup);
        }

        normalized.sort(
                (a, b) -> String.join(",", a)
                        .compareTo(String.join(",", b))
        );

        return normalized;
    }
}
