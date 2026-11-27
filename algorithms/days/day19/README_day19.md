# Day 19 — Binary Search: Minimum in a Rotated Sorted Array

## Problem

LeetCode 153 — Find Minimum in Rotated Sorted Array (Medium).
Find the minimum value in a non-empty, possibly rotated sorted array of distinct integers.

## Approach

Use a closed search interval `[left, right]`, initially `[0, nums.length - 1]`.
Compare `nums[mid]` with `nums[right]`:

- If `nums[mid] > nums[right]`, the rotation point is strictly to the right of `mid`. Set `left = mid + 1`.
- Otherwise, the minimum is at `mid` or to its left. Set `right = mid` because `mid` may itself be the minimum.

The invariant is that the minimum always belongs to `[left, right]`.
When the boundaries meet, return `nums[left]`.

## Solution

```java
public static int findMin(int[] nums) {
    int left = 0;
    int right = nums.length - 1;

    while (left < right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] > nums[right]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return nums[left];
}
```

## Example

Input: `[4, 5, 6, 7, 1, 2, 3]`

| left | right | mid | Comparison | Update |
|---:|---:|---:|---|---|
| 0 | 6 | 3 | 7 > 3 | left = 4 |
| 4 | 6 | 5 | 2 < 3 | right = 5 |
| 4 | 5 | 4 | 1 < 2 | right = 4 |

Result: `nums[4] = 1`.

## Key lessons

- A closed interval does not necessarily require `while (left <= right)`.
- When `left == right`, `mid == right`. Using `<=` would allow `right = mid` to repeat indefinitely.
- Comparing with the right boundary handles both rotated and unrotated arrays without a separate sorted-interval check.
- Comparing with the left boundary alone is ambiguous: `nums[mid] > nums[left]` holds for both `[4,5,6,7,1,2,3]` and `[1,2,3,4,5,6,7]`, but their minima are on different sides of `mid`.
- Do not use `right = mid - 1` when `mid` could be the minimum.
- This solution assumes distinct elements; duplicates require additional handling.

## Complexity

- Time: O(log n)
- Space: O(1)
