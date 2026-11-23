# Day 16 — Binary Search: First and Last Occurrence

## Goal

Practice finding the first and last occurrence of a target in a **sorted array that may contain duplicates**. Focus on understanding boundary searches rather than memorizing implementations.

## Problems Completed

### 1. First Occurrence

Return the index of the **first** occurrence of `target`, or `-1` if it is absent.

The key is to find the **lower bound**: the first index where `nums[i] >= target`.

```java
public static int firstOccurrence(int[] nums, int target) {
    int left = 0;
    int right = nums.length;

    while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] >= target) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }

    return left < nums.length && nums[left] == target ? left : -1;
}
```

**Important edge case:** `left` can equal `nums.length`. Check the index before accessing `nums[left]`, including for an empty array.

### 2. Last Occurrence

Return the index of the **last** occurrence of `target`, or `-1` if it is absent.

Find the **upper bound**: the first index where `nums[i] > target`. If the target exists, its last occurrence is at `upperBound - 1`.

```java
public static int lastOccurrence(int[] nums, int target) {
    int left = 0;
    int right = nums.length;

    while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] > target) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }

    if (left == 0) {
        return -1;
    }

    return nums[left - 1] == target ? left - 1 : -1;
}
```

**Why skip an equal element?** When searching for the first element *strictly greater* than the target, an equal element cannot be the boundary. If it was the last occurrence, it will still be recovered via `left - 1`.

## Lower Bound vs. Upper Bound

| Concept | Definition | Condition that moves `right` |
| --- | --- | --- |
| Lower bound | First index with `nums[i] >= target` | `nums[mid] >= target` |
| Upper bound | First index with `nums[i] > target` | `nums[mid] > target` |

Example:

```text
Index:    0  1  2  3  4  5
Array:   [1, 2, 2, 2, 4, 5]
Target:   2

lowerBound = 1
upperBound = 4
firstOccurrence = 1
lastOccurrence = 3
```

If the target is absent, the bounds still exist as insertion boundaries:

```text
Array: [1, 2, 4, 5]
Target: 3
lowerBound = 2
upperBound = 2
```

If the boundary is beyond the final element, it equals `nums.length` (a valid insertion position, **not** a valid array index).

## Boundary Search Invariants

Use a half-open search interval `[left, right)` with `right = nums.length` and `while (left < right)`.

- If the boundary condition is true at `mid`, keep that candidate with `right = mid`.
- Otherwise, the boundary must be strictly to the right: `left = mid + 1`.
- At termination, `left == right` and identifies the boundary.
- The expression `left + (right - left) / 2` avoids overflow from adding the two indices directly.

## Complexity

Both methods use **O(log n)** time and **O(1)** extra space.

## Lessons Learned

1. A boundary index is not necessarily an occurrence of the target.
2. First occurrence requires a final equality check after finding the lower bound.
3. Last occurrence uses the upper bound minus one, with an index and equality check.
4. The upper bound is **not** the last occurrence; it is the first index *after* all equal values.
5. For a target that is absent, lower and upper bounds coincide.
6. In a half-open interval, `right = mid` retains a possible boundary, whereas `left = mid + 1` excludes positions that cannot be the boundary.

