# Day 05 — Sliding Window vs. Prefix Sum

## Overview

Day 5 focuses on selecting the right pattern for contiguous subarray problems: **sliding window** when window boundaries can be adjusted predictably, and **prefix sum** when relationships between cumulative states are more useful.

## Problems Solved

| LeetCode | Problem | Difficulty | Approach | Time | Auxiliary Space |
|---|---|---|---|---|---|
| [209](https://leetcode.com/problems/minimum-size-subarray-sum/) | Minimum Size Subarray Sum | Medium | Variable-size Sliding Window | O(n) | O(1) |
| [525](https://leetcode.com/problems/contiguous-array/) | Contiguous Array | Medium | Prefix Sum + HashMap | O(n) average | O(n) |

## 209. Minimum Size Subarray Sum

**Goal:** Find the shortest contiguous subarray whose sum is at least `target`, or return `0` if none exists.

**Approach:** Maintain a running sum between `left` and `right`. Expand the window by moving `right`; whenever the sum reaches `target`, update the minimum length and repeatedly move `left` to find a shorter qualifying window.

Because all input values are positive, removing an element cannot increase the sum. This predictable behavior makes the variable-size sliding window approach valid.

**Complexity:** O(n) time because each boundary advances at most n times; O(1) auxiliary space.

**Important boundary case:** A single element can be a valid answer, so shrinking must allow `left <= right`.

## 525. Contiguous Array

**Goal:** Find the longest contiguous subarray containing an equal number of `0`s and `1`s.

**Approach:** Interpret each `0` as `-1` and each `1` as `+1`. A subarray has equal counts exactly when its transformed sum is zero. Track the running prefix sum and store the **earliest index** where each sum appeared. When the same prefix sum occurs again at index `i`, the intervening subarray has sum zero, with length `i - earliestIndex`.

Initialize the map with `0 -> -1` to include subarrays starting at index `0`. Do not overwrite an existing prefix-sum index: keeping the earliest occurrence maximizes the possible length.

**Complexity:** O(n) average time with HashMap operations; O(n) auxiliary space.

**Implementation note:** The submitted solution converts `0`s to `-1`s in the input array. An alternative is to update the running sum using `nums[i] == 0 ? -1 : 1`, avoiding input mutation.

## Pattern Selection

- **Sliding Window:** Prefer it when the current window can be maintained incrementally and moving its boundaries gives predictable information about validity (as with positive-number sums in LC 209).
- **Prefix Sum:** Prefer it when a subarray property can be expressed as a difference between two cumulative states (as with equal counts in LC 525).
- **HashMap values matter:** Store frequencies when counting matching subarrays; store earliest indices when maximizing their length.
