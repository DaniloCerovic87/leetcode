# Day 03 — Sliding Window

## Overview

Day 03 focuses on the **Sliding Window** technique for contiguous subarrays and substrings. The exercises progress from **fixed-size windows** to a **variable-size window** that must maintain a validity condition.

The goal is to avoid repeatedly scanning the same elements by updating the state of the current window as its boundaries move.

## Problems Solved

| # | LeetCode | Problem | Difficulty | Approach | Time | Extra Space |
|---|---|---|---|---|---|---|
| 1 | [643](https://leetcode.com/problems/maximum-average-subarray-i/) | Maximum Average Subarray I | Easy | Fixed-size Sliding Window | O(n) | O(1) |
| 2 | [1456](https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/) | Maximum Number of Vowels in a Substring of Given Length | Medium | Fixed-size Sliding Window + HashSet | O(n) average | O(1) |
| 3 | [3](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Longest Substring Without Repeating Characters | Medium | Variable-size Sliding Window + HashSet | O(n) average | O(min(n, m)) |

Here, `n` is the input length and `m` is the size of the character alphabet. For a fixed-size alphabet, the extra space in Problem 3 is O(1).

---

## 1. Maximum Average Subarray I — LeetCode 643

**Problem:** Find the maximum average of any contiguous subarray of exactly `k` elements.

**Approach:**

1. Calculate the sum of the first `k` elements.
2. Initialize the maximum sum with that first window's sum (important when all sums are negative or `k == nums.length`).
3. Move the window one position at a time: subtract the outgoing element and add the incoming element.
4. Track the largest window sum and divide by `k` at the end.

**Core update:**

```java
currentSum = currentSum - nums[i] + nums[i + k];
maxSum = Math.max(maxSum, currentSum);
```

**Complexity:** O(n) time, O(1) extra space.

**Java detail:** Use `(double) maxSum / k` to avoid integer division. Casting after the division would be too late.

**Key takeaway:** When all candidate windows have the same positive length `k`, maximizing their sum also maximizes their average, even with negative numbers.

## 2. Maximum Number of Vowels in a Substring of Given Length — LeetCode 1456

**Problem:** Find the maximum number of vowels in any substring of exactly `k` characters.

**Approach:**

1. Keep a `HashSet<Character>` containing `a`, `e`, `i`, `o`, and `u`.
2. Count the vowels in the first window of length `k`.
3. For each move, subtract `1` if the outgoing character is a vowel and add `1` if the incoming character is a vowel.
4. Update the maximum count after each move.

**Core update:**

```java
int outgoing = allVowels.contains(s.charAt(i)) ? 1 : 0;
int incoming = allVowels.contains(s.charAt(i + k)) ? 1 : 0;
currentCount = currentCount - outgoing + incoming;
maxCount = Math.max(maxCount, currentCount);
```

**Complexity:** O(n) average time, O(1) extra space. The vowel set has only five entries.

**Key takeaway:** The fixed-size window pattern stays the same; only the information maintained inside the window changes.

## 3. Longest Substring Without Repeating Characters — LeetCode 3

**Problem:** Find the length of the longest contiguous substring containing no repeated characters.

**Approach:**

1. Use `left` and `right` to represent the current window boundaries.
2. Maintain a `HashSet<Character>` containing exactly the characters in the valid window.
3. When the character at `right` is already present, repeatedly remove `s.charAt(left)` and advance `left` until the duplicate is gone.
4. Add the current character, then update the maximum window length.

**Core logic:**

```java
for (int right = 0; right < s.length(); right++) {
    while (uniqueChars.contains(s.charAt(right))) {
        uniqueChars.remove(s.charAt(left));
        left++;
    }

    uniqueChars.add(s.charAt(right));
    maxCount = Math.max(maxCount, right - left + 1);
}
```

**Complexity:** O(n) average time: each pointer moves forward at most `n` times, even though a `while` loop is nested inside a `for` loop. Extra space is O(min(n, m)).

**Key takeaway:** The set must always reflect the **current** valid window, not every character ever encountered. Shrink only as much as necessary; do not reset the entire window.

**Optional alternative:** A `HashMap<Character, Integer>` of last-seen indices can move `left` directly instead of removing characters one by one. This is also O(n) average time, but `left` must never move backward. This alternative was discussed, not implemented as part of Day 03.

---

## Fixed-size vs. Variable-size Sliding Window

| Aspect | Fixed-size | Variable-size |
|---|---|---|
| Window length | Always exactly `k` | Changes to maintain a condition |
| Typical problem wording | “subarray/substring of length `k`” | “longest/shortest substring satisfying ...” |
| Movement | One element leaves as another enters | Expand with `right`; shrink with `left` as needed |
| State tracked here | Sum or vowel count | Unique characters in a `HashSet` |
| Day 03 examples | LC 643, LC 1456 | LC 3 |

### Sliding Window and negative numbers

Fixed-size Sliding Window works with negative numbers: each window is updated by removing one value and adding another. For variable-size **sum-constrained** windows, negative numbers can invalidate common monotonicity-based strategies. This does not prevent variable-size windows from working for other conditions, such as character uniqueness.



