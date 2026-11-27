# Day 20 - Binary Search: Search in Rotated Sorted Array

## Problem

**LeetCode 33 - Search in Rotated Sorted Array (Medium)**

Given a possibly rotated ascending array of distinct integers and a target, return the index of the target or `-1` if it does not exist.

## Approach

Use binary search with a closed interval `[left, right]`.

A rotated sorted array has at most one break in ascending order. Therefore, at least one of the two halves is sorted.

1. Calculate `mid` and check whether `nums[mid] == target`.
2. Determine whether the left half is sorted using `nums[mid] >= nums[left]`.
3. Check whether the target belongs to the value range of the sorted half.
4. Eliminate the half that cannot contain the target, including the already checked middle element.
5. Return `-1` if the search interval becomes empty.

## Solution

```java
public static int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return mid;
        }

        if (nums[mid] >= nums[left]) {
            if (target >= nums[left] && target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else {
            if (target > nums[mid] && target <= nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
    return -1;
}
```

## Example

Input: `[4, 5, 6, 7, 0, 1, 2]`, target = `1`.

| left | right | mid | nums[mid] | Decision |
| --- | --- | --- | --- | --- |
| 0 | 6 | 3 | 7 | Left half is sorted; target is outside its range. Set left = 4. |
| 4 | 6 | 5 | 1 | Target found. Return 5. |

## Key Takeaways

- Identify the sorted half before deciding where to search.
- Use both boundaries of the sorted half to check the target range, joined by `&&`.
- Equality in `nums[mid] >= nums[left]` handles the case where both indices refer to the same element.
- Include the outer boundary but exclude `mid` from the target range because `mid` has already been checked.
- To retain the left half, set `right = mid - 1`.
- To retain the right half, set `left = mid + 1`.
- Update the search boundaries, not `mid` directly.
- The invariant is that if the target exists, its index remains within the current search interval until it is found.
- Unlike LC 153, the target may not exist, so the interval can become empty and the loop uses `left <= right`.

## Complexity

- Time: O(log n), because each iteration eliminates approximately half of the remaining candidates.
- Space: O(1), because only a constant number of variables is used.

## Edge Cases

- Single-element arrays (target present or absent).
- Two-element arrays, rotated or unrotated.
- Fully sorted arrays.
- Target at the first, middle, or last index.
- Target absent from the array.
- Negative values.

## Review

Reconstruct the algorithm from its properties rather than memorizing the code:

1. Why must at least one half be sorted?
2. How can we identify that half?
3. How can we determine whether the target belongs to its range?
4. Which candidates can safely be discarded?
5. Why can we exclude `mid` after checking it?

