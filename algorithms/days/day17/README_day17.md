# Day 17 — Binary Search: Lower Bound & Upper Bound

## Goals

- Find insertion boundaries in a sorted array using binary search.
- Understand the difference between `>= target` and `> target`.
- Handle duplicates, absent targets, and empty arrays.
- Achieve **O(log n)** time and **O(1)** auxiliary space.

Both problems assume the input array is sorted in non-decreasing order.

## Task 1 — Lower Bound

**Problem:** Given a sorted integer array `nums` and an integer `target`, return the index of the **first element greater than or equal to** `target`. If no such element exists, return `nums.length`.

**Examples:**

| `nums` | `target` | Result | Explanation |
|---|---:|---:|---|
| `[1, 2, 2, 2, 4, 5]` | 2 | 1 | First value `>= 2` is at index 1 |
| `[1, 2, 4, 5]` | 3 | 2 | First value `>= 3` is 4 |
| `[1, 3, 5, 7]` | 8 | 4 | No qualifying value; return length |
| `[]` | 3 | 0 | Empty array |

### My solution

```java
public static int lowerBound(int[] nums, int target) {
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

    return left == nums.length ? nums.length : left;
}
```

**How it works:** If `nums[mid] >= target`, `mid` may be the first qualifying position, so retain it by moving `right` to `mid`. Otherwise, discard `mid` and everything to its left by setting `left = mid + 1`. The search uses the half-open interval `[left, right)`. When the loop ends, `left` is the lower bound.

**Simplification:** The final line can be written as `return left;` because the conditional returns `left` in both cases.

## Task 2 — Upper Bound

**Problem:** Given a sorted integer array `nums` and an integer `target`, return the index of the **first element strictly greater than** `target`. If no such element exists, return `nums.length`.

**Examples:**

| `nums` | `target` | Result | Explanation |
|---|---:|---:|---|
| `[1, 2, 2, 2, 4, 5]` | 2 | 4 | First value `> 2` is 4 |
| `[1, 2, 4, 5]` | 3 | 2 | First value `> 3` is 4 |
| `[2, 2, 2, 2]` | 2 | 4 | No value strictly greater than 2 |
| `[]` | 3 | 0 | Empty array |

### My solution

```java
public static int upperBound(int[] nums, int target) {
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

    return left;
}
```

**How it works:** If `nums[mid] > target`, `mid` is a possible upper bound, so move `right = mid`. Otherwise, including when `nums[mid] == target`, move `left = mid + 1` because `mid` does not meet the strict condition. When the loop ends, `left` is the upper bound.

## Key differences

| Concept | Lower Bound | Upper Bound |
|---|---|---|
| First qualifying element | `nums[i] >= target` | `nums[i] > target` |
| Equality at `mid` | Move `right = mid` | Move `left = mid + 1` |
| If no qualifying element | `nums.length` | `nums.length` |
| If array is empty | `0` | `0` |

For `nums = [1, 2, 2, 2, 4, 5]` and `target = 2`:

- Lower bound = `1`.
- Upper bound = `4`.
- The range of matching elements is `[1, 4)`.

For `nums = [1, 2, 4, 5]` and `target = 3`, both bounds equal `2`, even though 3 is not in the array.

## Complexity

Both methods use **O(log n)** time for nonempty arrays and **O(1)** auxiliary space. For an empty array, each returns immediately.

## Edge cases covered

- Empty array
- Single-element array
- All elements equal to the target
- Target smaller than every element
- Target greater than every element
- Target absent but between two values
- Duplicate values

## Takeaways

1. A bound is an insertion boundary, not necessarily the index of an existing target.
2. With a half-open interval `[left, right)`, initialize `right = nums.length`.
3. `right = mid` retains a possible answer; `left = mid + 1` eliminates an impossible answer.
4. The loop terminates because the interval shrinks on every iteration.
5. Both algorithms can simply return `left`.
