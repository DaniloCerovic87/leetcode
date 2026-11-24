# Day 18 — Binary Search: Boundary Mastery

## Goals

Apply lower and upper bounds to counting occurrences and finding neighboring values in sorted arrays.

## Task 1: CountOccurrences

Return the number of occurrences of target in a sorted array. Return 0 if absent.

### Solution

```java
    public static int countOccurrences(int[] nums, int target) {
        int upperBound = upperBound(nums, target);
        if (upperBound == 0) {
            return 0;
        }
        return upperBound - lowerBound(nums, target);
    }

    private static int lowerBound(int[] nums, int target) {
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

    private static int upperBound(int[] nums, int target) {
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

### Approach

Find both boundaries and subtract lower bound from upper bound. The early return when upper bound is zero is valid but optional.

Time: O(log n). Space: O(1).

### Examples

| Array | Target | Expected |
|---|---:|---:|
| `[1, 2, 2, 2, 4, 5]` | 2 | 3 |
| `[1, 2, 4, 5]` | 3 | 0 |
| `[2, 2, 2, 2]` | 2 | 4 |
| `[2, 2, 2, 2]` | 3 | 0 |
| `[1, 3, 5, 7]` | 5 | 1 |
| `[1, 2, 4, 5]` | 0 | 0 |
| `[1, 2, 4, 5]` | 6 | 0 |
| `[1, 1, 1, 2, 3]` | 1 | 3 |
| `[1, 2, 3, 3, 3]` | 3 | 3 |
| `[5]` | 5 | 1 |
| `[5]` | 8 | 0 |
| `[]` | 3 | 0 |
| `[-5, -2, -2, 0, 3]` | -2 | 2 |
| `[-3, 0, 0, 0, 4]` | 0 | 3 |

## Task 2: FloorInSortedArray

Return the last index of the largest element less than or equal to target. Return -1 if absent.

### Solution

```java
    public static int floor(int[] nums, int target) {
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
        return left == 0 ? -1 : left - 1;
    }
```

### Approach

Find the upper bound and subtract one. The ternary is optional because zero minus one already equals -1.

Time: O(log n). Space: O(1).

### Examples

| Array | Target | Expected |
|---|---:|---:|
| `[1, 3, 5, 7, 9]` | 6 | 2 |
| `[1, 3, 5, 7, 9]` | 5 | 2 |
| `[1, 3, 5, 7, 9]` | 10 | 4 |
| `[1, 3, 5, 7, 9]` | 0 | -1 |
| `[1, 2, 2, 2, 5]` | 2 | 3 |
| `[2, 2, 2, 2]` | 2 | 3 |
| `[2, 2, 2, 2]` | 1 | -1 |
| `[2, 2, 2, 2]` | 3 | 3 |
| `[5]` | 5 | 0 |
| `[5]` | 3 | -1 |
| `[5]` | 8 | 0 |
| `[]` | 3 | -1 |
| `[-5, -3, -1, 2]` | -2 | 1 |
| `[-5, -3, -1, 2]` | -6 | -1 |
| `[1, 3, 5, 7]` | 6 | 2 |

## Task 3: CeilInSortedArray

Return the first index of the smallest element greater than or equal to target. Return -1 if absent.

### Solution

```java
    public static int ceil(int[] nums, int target) {
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
        return left == nums.length ? -1 : left;
    }
```

### Approach

Find the lower bound. Convert the exclusive end position (nums.length) to -1.

Time: O(log n). Space: O(1).

### Examples

| Array | Target | Expected |
|---|---:|---:|
| `[1, 3, 5, 7, 9]` | 6 | 3 |
| `[1, 3, 5, 7, 9]` | 5 | 2 |
| `[1, 3, 5, 7, 9]` | 10 | -1 |
| `[1, 3, 5, 7, 9]` | 0 | 0 |
| `[1, 2, 2, 2, 5]` | 2 | 1 |
| `[2, 2, 2, 2]` | 2 | 0 |
| `[2, 2, 2, 2]` | 1 | 0 |
| `[2, 2, 2, 2]` | 3 | -1 |
| `[5]` | 5 | 0 |
| `[5]` | 3 | 0 |
| `[5]` | 8 | -1 |
| `[]` | 3 | -1 |
| `[-5, -3, -1, 2]` | -2 | 2 |
| `[-5, -3, -1, 2]` | -6 | 0 |
| `[1, 3, 5, 7]` | 6 | 3 |

## Key takeaways

- Lower bound: first index with value >= target.
- Upper bound: first index with value > target.
- Count = upper bound - lower bound.
- Floor = upper bound - 1.
- Ceil = lower bound, unless it equals the array length (return -1).
- The half-open search interval starts at [0, nums.length). Each iteration shrinks it; empty arrays need no special loop handling.
- First occurrence requires checking equality at the lower bound; last occurrence requires checking equality at upper bound - 1.

