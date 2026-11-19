# Day 12 — Sorting, Comparator & One-Pass Patterns

## Goals

- Understand Java's `Comparable` and `Comparator` interfaces.
- Sort by one or multiple keys using `comparing`, `comparingInt`, `thenComparing`, and `reversed`.
- Understand stable sorting and why subtraction-based comparators can overflow.
- Apply the **sort + one pass** pattern and in-place two-pointer processing.

## Java Sorting Notes

- `Comparable<T>` defines a class's natural order through `compareTo`.
- `Comparator<T>` defines an external ordering strategy through `compare`.
- `Comparator.comparingInt(Employee::getSalary)` avoids boxing an `int` key.
- `thenComparing(...)` is used when the first comparison is equal.
- Calling `reversed()` **after** `thenComparing()` reverses the entire composed comparator.
- `Comparator.nullsFirst(...)` / `nullsLast(...)` specify where null keys belong.
- Java's object-array and list sorting is stable: equal keys retain their relative order.
- Prefer `Integer.compare(a, b)` over `a - b`: subtraction may overflow and produce the wrong comparison sign.

## Problem 1 — LeetCode 26: Remove Duplicates from Sorted Array (Easy)

**File:** `RemoveDuplicatesFromSortedArray.java`

**Task:** Given a sorted integer array, remove duplicate values in place and return the number `k` of unique values. Only the first `k` positions matter.

**My approach — read/write pointers:**

1. Initialize `write = 0` as the index of the last unique value.
2. Scan from `read = 1` to the end.
3. When `nums[read] != nums[write]`, increment `write` and copy the new value into `nums[write]`.
4. Return `write + 1`.

```java
public static int removeDuplicates(int[] nums) {
    int write = 0;

    for (int read = 1; read < nums.length; read++) {
        if (nums[read] != nums[write]) {
            write++;
            nums[write] = nums[read];
        }
    }

    return write + 1;
}
```

**Example:** `[0,0,1,1,2,2,3]` → `k = 4`, first four positions `[0,1,2,3]`.

**Complexity:** `O(n)` time, `O(1)` auxiliary space.

**Important:** The original LeetCode constraints guarantee a nonempty array. The implementation relies on that guarantee.

## Problem 2 — LeetCode 56: Merge Intervals (Medium)

**File:** `MergeIntervals.java`

**Task:** Given possibly unsorted intervals, merge all overlapping or touching intervals and return the non-overlapping result.

**My approach — sort + one pass:**

1. Sort intervals by their start values using `Comparator.comparingInt(a -> a[0])`.
2. Keep `start` and `end` for the interval currently being built.
3. For each next interval:
   - If `currentStart > end`, the current merged interval is complete. Add it to the result, then start a new interval.
   - Otherwise, extend the end using `Math.max(end, currentEnd)`. This is important when one interval is nested inside another.
4. After the loop, add the final interval, which has not yet been saved.

```java
public static int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    List<int[]> result = new ArrayList<>();

    int start = intervals[0][0];
    int end = intervals[0][1];

    for (int i = 1; i < intervals.length; i++) {
        int currentStart = intervals[i][0];
        int currentEnd = intervals[i][1];

        if (currentStart > end) {
            result.add(new int[]{start, end});
            start = currentStart;
            end = currentEnd;
        } else {
            end = Math.max(end, currentEnd);
        }
    }

    result.add(new int[]{start, end});
    return result.toArray(new int[][]{});
}
```

**Example:** `[[1,3],[2,6],[8,10],[15,18]]` → `[[1,6],[8,10],[15,18]]`.

**Complexity:** `O(n log n)` time for sorting plus `O(n)` for scanning; `O(n)` space for the output (sorting may use additional implementation-dependent space).

**Important edge cases:** Touching endpoints (`[1,4]` and `[4,5]`), nested intervals (`[1,10]` and `[2,3]`), unsorted input, and a single interval.

## Lessons Learned

- State can be maintained across iterations using variables declared **before** the loop; a nested loop is not necessary.
- For interval merging, sorting first makes a linear scan possible.
- `Math.max(end, currentEnd)` prevents accidentally shrinking a merged interval.
- In an approach that only appends a completed interval when a gap appears, the last interval must be appended **after** the loop.
- An alternative is to insert the first interval into the result immediately and update the last result entry in place, but the `start`/`end` approach keeps the state explicit and avoids mutating the input interval arrays.

