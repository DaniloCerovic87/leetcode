# Day 02 — Prefix Sum & HashMap

## Overview

This day focuses on **prefix sums** and using a **HashMap** to solve problems involving contiguous subarrays efficiently. The key idea is to use the difference between two prefix sums instead of recomputing each subarray's sum.

## Problems Solved

| # | Problem | Difficulty | Approach | Time | Space |
|---|---|---|---|---|---|
| [1480](https://leetcode.com/problems/running-sum-of-1d-array/) | Running Sum of 1d Array | Easy | Prefix Sum | O(n) | O(n) |
| [560](https://leetcode.com/problems/subarray-sum-equals-k/) | Subarray Sum Equals K | Medium | Prefix Sum + HashMap | O(n) average | O(n) |
| [325](https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/) | Maximum Size Subarray Sum Equals k (Premium) | Medium | Prefix Sum + HashMap | O(n) average | O(n) |

## 1. Running Sum of 1d Array — LeetCode 1480

**Problem:** Given an integer array, return an array in which each element is the sum of all input elements up to that index.

**Approach:** Traverse the input once, accumulating the running sum in a separate result array. The input remains unchanged.

- **Time:** O(n)
- **Space:** O(n) for the returned array

**Key takeaway:** A running sum can be calculated in one pass.

## 2. Subarray Sum Equals K — LeetCode 560

**Problem:** Count all non-empty contiguous subarrays whose sum equals `k`. Values may be positive, negative, or zero.

**Approach:** Maintain the current prefix sum and a HashMap recording the frequency of each previously seen prefix sum. At each index, the number of new valid subarrays is the frequency of `prefixSum - k`. Add this frequency to the answer, then record the current prefix sum.

**HashMap:** `prefixSum -> frequency`

**Initialization:** `map.put(0, 1)` represents one empty prefix, so subarrays starting at index `0` are counted.

- **Time:** O(n) average
- **Space:** O(n)

**Key takeaway:** Each earlier occurrence of the required prefix sum corresponds to another valid subarray.

## 3. Maximum Size Subarray Sum Equals k — LeetCode 325 (Premium)

**Problem:** Return the length of the longest non-empty contiguous subarray whose sum equals `k`, or `0` if none exists. Values may be positive, negative, or zero.

**Approach:** Maintain the current prefix sum and a HashMap storing the **earliest index** where each prefix sum occurred. At index `i`, if `prefixSum - k` was seen at index `j`, the candidate length is `i - j`. Keep the maximum length. Record a prefix sum only on its first occurrence.

**HashMap:** `prefixSum -> earliestIndex`

**Initialization:** `map.put(0, -1)` represents the empty prefix before index `0`, ensuring correct lengths for subarrays starting at the beginning.

**Java API:** `putIfAbsent(prefixSum, i)` preserves the earliest index.

- **Time:** O(n) average
- **Space:** O(n)

**Key takeaway:** To maximize subarray length, preserve the earliest occurrence of each prefix sum rather than overwriting it.

## Core Concepts

### Prefix Sum Identity

For a contiguous subarray from index `l` through index `r`:

```text
subarraySum(l, r) = prefixSum[r] - prefixSum[l - 1]
```

A subarray ending at the current index has sum `k` when an earlier prefix sum equals:

```text
previousPrefixSum = currentPrefixSum - k
```

### Count vs. Longest Subarray

| Concept | Count Subarrays (560) | Longest Subarray (325) |
|---|---|---|
| Goal | Number of matching subarrays | Maximum matching length |
| Map stores | `prefixSum -> frequency` | `prefixSum -> earliestIndex` |
| Initial entry | `0 -> 1` | `0 -> -1` |
| Result update | Add matching frequency | Maximize `i - previousIndex` |
| Map update | Increment current sum frequency | Store current sum only if absent |

### Why Standard Sliding Window Is Not Suitable Here

When negative numbers are allowed, extending a window can decrease its sum and shrinking it can increase its sum. A conventional sum-based sliding window therefore cannot reliably decide how to move its boundaries. Prefix Sum + HashMap handles positive numbers, negative numbers, and zeros.

## Java APIs Practiced

- `Map` and `HashMap`
- `getOrDefault()`
- `containsKey()` and `get()`
- `put()` and `putIfAbsent()`
- `Math.max()`

## Lessons Learned

1. Prefix sums convert contiguous-subarray sum problems into lookups of previously seen cumulative sums.
2. Searching for `prefixSum - k` and recording `prefixSum` are separate operations.
3. Counting requires **frequencies**; maximizing length requires **earliest indices**.
4. The empty prefix needs a different map value depending on the problem: `0 -> 1` versus `0 -> -1`.
5. Do not overwrite the earliest index when solving the longest-subarray variant.
6. Both HashMap approaches run in O(n) expected time and O(n) extra space.
