# Day 14 — Move Zeroes (Stable, In-Place)

## Topics Covered

- Two pointers: `read` and `write`
- In-place array modification
- Stable ordering of non-zero elements
- Choosing the correct traversal direction
- O(n) time and O(1) auxiliary space

## Problem 1 — LeetCode 283: Move Zeroes (Easy)

**Link:** https://leetcode.com/problems/move-zeroes/

### Problem

Move all zeroes to the **end** of the array while preserving the relative order of all non-zero elements. Modify the input array in-place.

**Example:**

```text
Input:  [10, 0, 0, 15, 0, 0, 10, 10]
Output: [10, 15, 10, 10, 0, 0, 0, 0]
```

### My Solution

```java
public static void moveZeroes(int[] nums) {
    int write = 0;

    for (int read = 0; read < nums.length; read++) {
        if (nums[read] != 0) {
            nums[write] = nums[read];
            write++;
        }
    }

    for (int i = write; i < nums.length; i++) {
        nums[i] = 0;
    }
}
```

### Explanation

- `read` scans the array from left to right.
- `write` points to the next position for a non-zero element.
- Copying non-zero elements in their original encounter order preserves stability.
- After the scan, fill positions from `write` to the end with zeroes.

**Invariant:** After processing each element, `nums[0..write-1]` contains the non-zero elements encountered so far, in their original order.

**Complexity:** O(n) time, O(1) auxiliary space.

---

## Problem 2 — Move Zeroes to Beginning (Custom Variation)

### Problem

Move all zeroes to the **beginning** of the array while preserving the relative order of all non-zero elements. Modify the input array in-place.

**Example:**

```text
Input:  [10, 0, 0, 15, 0, 0, 10, 10]
Output: [0, 0, 0, 0, 10, 15, 10, 10]
```

### My Solution

```java
public static void moveZeroesToBeginning(int[] nums) {
    int length = nums.length;
    int write = length - 1;

    for (int read = length - 1; read >= 0; read--) {
        if (nums[read] != 0) {
            nums[write] = nums[read];
            write--;
        }
    }

    for (int i = 0; i <= write; i++) {
        nums[i] = 0;
    }
}
```

### Explanation

- Start both pointers at the end of the array.
- `read` scans from right to left.
- `write` marks the next available position for a non-zero element.
- Moving non-zero elements in reverse traversal order preserves their original relative order.
- Fill positions `0` through `write` (inclusive) with zeroes.

**Invariant:** After processing each element, `nums[write+1..length-1]` contains the non-zero elements encountered so far, in their original order.

**Complexity:** O(n) time, O(1) auxiliary space.

---

## Comparison

| Property | Zeroes at End | Zeroes at Beginning |
|---|---|---|
| Traversal | Left to right | Right to left |
| Initial `write` | `0` | `nums.length - 1` |
| Update `write` | `write++` | `write--` |
| Fill zeroes | `write` to `length - 1` | `0` to `write` |
| Time | O(n) | O(n) |
| Auxiliary space | O(1) | O(1) |
| Stable | Yes | Yes |

## Edge Cases to Test

- Empty array
- Single zero or single non-zero element
- All zeroes
- No zeroes
- Zeroes already at the required end
- Alternating zeroes and non-zero values
- Negative non-zero values
- Repeated non-zero values

## Key Takeaways

1. **Traversal direction matters:** When compacting elements left, scan left to right; when compacting right, scan right to left.
2. **Read/write pointers preserve stability** without requiring a second array.
3. **Fill the remaining positions correctly:** start at `write` for zeroes at the end, and fill through `write` inclusive for zeroes at the beginning.
4. **Nested loops are unnecessary:** each solution uses two sequential passes and remains O(n).
5. **In-place does not mean zero writes:** it means the original array is modified using O(1) auxiliary space.

**Day 14 complete:** Both variants solved with optimal asymptotic complexity.
