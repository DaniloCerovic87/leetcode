# Day 21 — Search in Rotated Sorted Array II

## Problem

LeetCode 81 — Medium

Given a sorted array that may have been rotated and may contain duplicates, return whether a target exists in the array.

## Approach — Modified Binary Search

Maintain a closed search interval `[left, right]` containing a possible occurrence of the target.

1. Calculate `mid` and check whether it matches the target.
2. If `left`, `mid`, and `right` contain the same value, neither endpoint can be the target because `mid` has already been checked. Shrink both boundaries and start the next iteration.
3. Otherwise, identify a sorted half and check whether the target belongs to its value range.
4. Discard the half that cannot contain the target.
5. Return `false` when the interval becomes empty.

## Solution

```java
public static boolean search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return true;
        }

        if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
            left++;
            right--;
            continue;
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
    return false;
}
```

## Key Insight — Duplicates Create Ambiguity

Consider `[3, 3, 3, 3, 4, 5, 3, 3, 3, 3, 3, 3, 3]` with target `5`.

The initial left, middle, and right values are all `3`. The left half is not sorted, despite `nums[mid] >= nums[left]` being true. We cannot safely discard an entire half based on that comparison.

Since the middle value is not the target, both equal endpoints can safely be removed.

Equal endpoints alone are not enough to justify this operation. For example, in `[4, 5, 6, 7, 8, 9, 1, 2, 3, 4]`, the middle value differs from the endpoints, and the endpoints might themselves match the target.

If only the left and middle values are equal while the right value differs, the left half must be sorted for a valid rotated non-decreasing array. The triple-equality guard therefore resolves the ambiguity needed by this implementation.

## Boundary Rules

- Use `while (left <= right)` because the last remaining element must be checked.
- Check `mid` before eliminating duplicates.
- Left sorted range: `target >= nums[left] && target < nums[mid]`.
- Right sorted range: `target > nums[mid] && target <= nums[right]`.
- Include the outer endpoints because they have not necessarily been checked; exclude `mid` because it has already been checked.
- Use `continue` after shrinking both endpoints to recompute the search interval and middle index.

## Complexity

- Time: O(log n) when each iteration eliminates approximately half of the remaining elements.
- Worst-case time: O(n), for example when all elements are equal and the target is absent.
- Auxiliary space: O(1).

## Edge Cases

| Input | Target | Expected |
|---|---:|---|
| `[2, 5, 6, 0, 0, 1, 2]` | 0 | true |
| `[1, 0, 1, 1, 1]` | 0 | true |
| `[1, 1, 1, 0, 1]` | 0 | true |
| `[3, 3, 3, 3, 3]` | 3 | true |
| `[3, 3, 3, 3, 3]` | 5 | false |
| `[1]` | 0 | false |
| `[6, 7, 0, 1, 2, 3, 4]` | 4 | true |
| `[4, 5, 6, 7, 8, 9, 1, 2, 3, 4]` | 4 | true |

## Review Questions

1. Why can equal left, middle, and right values hide the rotation point?
2. Why must the middle element be checked before removing equal endpoints?
3. Why is equality with the right endpoint alone insufficient for eliminating both endpoints?
4. Why do we use `<=` when checking the target against the right endpoint?
5. Why can duplicates increase the worst-case time complexity to O(n)?

## Takeaway

Binary search is about safely eliminating candidates. When duplicates prevent us from identifying a sorted half, eliminate only elements known not to be the target.
