# Day 04 — Sliding Window and Character Frequencies

This day focuses on finding permutations and anagrams within strings using a **fixed-size sliding window** and character-frequency maps.

## Problems Solved

| Problem | Difficulty | Approach | Time | Auxiliary Space |
| --- | --- | --- | --- | --- |
| [567. Permutation in String](https://leetcode.com/problems/permutation-in-string/) | Medium | Sliding Window + HashMap | O(n) | O(1) |
| [438. Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/) | Medium | Sliding Window + HashMap | O(n) | O(1) |

Here, `n` is the length of the string being searched. Both problems restrict characters to the 26 lowercase English letters.

## 567. Permutation in String

**Goal:** Determine whether `s2` contains any contiguous substring that is a permutation of `s1`.

**Approach:**

1. Return `false` if `s1` is longer than `s2`.
2. Build frequency maps for `s1` and the first window of `s2`, whose length is `s1.length()`.
3. Compare the maps. If they match, return `true`.
4. Slide the window one position at a time: decrement the outgoing character's frequency (removing its key when the count reaches zero) and increment the incoming character's frequency.
5. Return `true` on the first matching window; otherwise return `false`.

**Complexity:** O(n) time and O(1) auxiliary space. Map equality compares at most 26 entries, and each window shift requires constant expected-time map operations.

## 438. Find All Anagrams in a String

**Goal:** Return the starting indices of every contiguous substring of `s` that is an anagram of `p`.

**Approach:**

1. Return an empty list if `p` is longer than `s`.
2. Build frequency maps for `p` and the first window of `s`, whose length is `p.length()`.
3. Add index `0` to the result if the initial window matches.
4. For each subsequent window, remove the outgoing character, add the incoming character, and compare the frequency maps.
5. When a match is found, append the new window's starting index. Continue until all windows have been checked.

**Complexity:** O(n) time and O(1) auxiliary space, excluding the output list. The result list can use O(n) space in the worst case.

## Implementation Details

- **Maintain the window incrementally:** Update only the outgoing and incoming characters instead of rebuilding frequencies for each substring.
- **Remove zero-frequency entries:** A map containing `{'a': 0}` is not equal to an empty map, so keys with zero counts must be removed.
- **Check the first window separately:** After initialization, each loop iteration shifts the window exactly once.
- **Keep index semantics consistent:** With `i` as the outgoing character's index, the incoming character is at `i + k` and the new window starts at `i + 1`.
- **Compare maps by content:** `Map.equals()` compares key-value mappings, not insertion order.

Both solutions use the same core pattern; LC 567 stops at the first match, while LC 438 collects every matching starting index.
