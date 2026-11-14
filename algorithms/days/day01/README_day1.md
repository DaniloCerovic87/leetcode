# Day 01 — Arrays & Hashing

Algorithmic problem-solving practice focused on array processing, hash-based lookups, frequency counting, and grouping.

## Problems

| # | Problem | Difficulty | Approach | Time | Auxiliary Space |
|---|---|---|---|---|---|
| 217 | [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) | Easy | HashSet | O(n) average | O(n) |
| 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | Easy | Single-pass HashMap | O(n) average | O(n) |
| 242 | [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy | Character frequency array | O(n) | O(1) |
| 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium | Sorted-string canonical key | O(n · k log k) | O(n · k) |

## Implementation Notes

**Contains Duplicate**
- Early termination on the first duplicate.
- Average-case linear time using hash-based membership checks.

**Two Sum**
- Single-pass complement lookup while preserving original indices.

**Valid Anagram**
- HashMap-based and fixed-size frequency-array implementations.
- The `charAt()`-based frequency-array variant uses constant auxiliary space for the constrained alphabet.

**Group Anagrams**
- Canonicalizes each string by sorting its characters.
- Accumulates groups through hash-based lookup.

## Complexity Conventions

- `n` denotes the input size (elements or characters, depending on the problem).
- For Group Anagrams, `n` is the number of strings and `k` is the maximum string length.
- Hash-based operations assume average-case constant-time lookup and insertion.
