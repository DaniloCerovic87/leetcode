# Day 15 — Binary Search

## Goals

- Understand how binary search reduces the search interval in a sorted array.
- Distinguish **indices** (`left`, `right`, `mid`) from **values** (`nums[mid]`, `target`).
- Understand the difference between a closed interval `[left, right]` and a half-open interval `[left, right)`.
- Practice finding an exact value and finding an insertion boundary.

## Problem 1 — LeetCode 704: Binary Search (Easy)

**Task:** Given a sorted array of distinct integers, return the index of `target`, or `-1` if it does not exist.

**Approach:** Binary search over the closed interval `[left, right]`.

```java
public static int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return -1;
}
```

**Why it works:**

- If `nums[mid] == target`, the target has been found.
- If `nums[mid] > target`, `mid` and everything to its right can be discarded.
- If `nums[mid] < target`, `mid` and everything to its left can be discarded.
- `left <= right` ensures that the final remaining candidate (`left == right`) is checked.
- If the interval becomes empty (`left > right`), the target is absent.

**Complexity:** Time `O(log n)`; auxiliary space `O(1)`.

## Problem 2 — LeetCode 35: Search Insert Position (Easy)

**Task:** Given a sorted array of distinct integers, return the index of `target` if found; otherwise return the index where it should be inserted to preserve sorted order.

**Approach:** Lower-bound binary search: find the first index whose value is **greater than or equal to** `target`.

```java
public static int searchInsert(int[] nums, int target) {
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

    return left;
}
```

**Why it works:**

- `right = nums.length` allows the insertion position to be *after* the final element.
- The half-open interval `[left, right)` contains indices `left` through `right - 1`.
- If `nums[mid] < target`, `mid` cannot be the answer, so use `left = mid + 1`.
- If `nums[mid] >= target`, `mid` might be the first valid position, so keep it with `right = mid`.
- When `left == right`, the search interval is empty and the insertion boundary has been identified.
- At termination, `left` and `right` are equal, so either could be returned; `return left` is conventional.

**Complexity:** Time `O(log n)`; auxiliary space `O(1)`.

### Example trace

For `nums = [1, 3, 5, 6]` and `target = 4`:

| Step | left | right | mid | nums[mid] | Action |
| --- | ---: | ---: | ---: | ---: | --- |
| 1 | 0 | 4 | 2 | 5 | `right = 2` |
| 2 | 0 | 2 | 1 | 3 | `left = 2` |
| End | 2 | 2 | — | — | `return 2` |

The insertion position is index `2`, before the value `5`.

## Key Lessons

1. **Never confuse indices with array values.** Initialize `left = 0`, not `left = nums[0]`.
2. **Do not memorize boundary updates blindly.** Ask which indices are definitely impossible and whether `mid` might still be the answer.
3. **Choose the loop condition to match the interval and update rules.** Closed-interval exact search commonly uses `left <= right`; half-open lower-bound search uses `left < right`.
4. **Use a safe midpoint calculation:** `left + (right - left) / 2` avoids overflow from adding two large nonnegative indices.
5. **Insertion indices range from `0` to `nums.length`**, including before the first and after the last element.
