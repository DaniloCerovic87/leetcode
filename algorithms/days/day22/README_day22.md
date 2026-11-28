# Day 22 – Binary Search on Answer

## LeetCode 875 – Koko Eating Bananas

**Difficulty:** Medium

### Problem

Given an array of banana piles and a deadline of `h` hours, find the minimum integer eating speed `k` that allows Koko to finish all piles within the deadline. Each hour, Koko eats up to `k` bananas from a single pile.

### Approach

Use binary search on the answer rather than on the array itself.

- Lower bound: `1`, the smallest possible eating speed.
- Upper bound: `max(piles)`, which guarantees completion because `h >= piles.length`.
- Predicate: `canEat(piles, h, k)` checks whether a given speed is sufficient.
- If the speed is sufficient, keep `mid` by setting `right = mid`.
- Otherwise, discard `mid` and all smaller speeds by setting `left = mid + 1`.
- Stop when `left == right` and return `left`.

### Java Implementation

```java
public static int minEatingSpeed(int[] piles, int h) {
    int max = 0;
    for (int p : piles) {
        max = Math.max(p, max);
    }

    int left = 1;
    int right = max;

    while (left < right) {
        int mid = left + (right - left) / 2;
        if (canEat(piles, h, mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}

private static boolean canEat(int[] piles, int h, int k) {
    long totalHours = 0;
    for (int p : piles) {
        totalHours += p / k + (p % k != 0 ? 1 : 0);
        if (totalHours > h) {
            return false;
        }
    }
    return true;
}
```

### Example

`piles = [3, 6, 7, 11], h = 8`

| Left | Right | Mid | Required hours | Feasible |
| --- | --- | --- | --- | --- |
| 1 | 11 | 6 | 6 | Yes |
| 1 | 6 | 3 | 10 | No |
| 4 | 6 | 5 | 8 | Yes |
| 4 | 5 | 4 | 8 | Yes |

Minimum eating speed: **4**. Required hours in the table represent the full calculation, even if the predicate exits early.

### Complexity

- Time: O(n + n log M), where M is the largest pile (commonly expressed as O(n log M) for M >= 2).
- Space: O(1).

### Key Takeaways

- Binary search can operate on a monotonic space of possible answers.
- The First True pattern preserves a feasible midpoint as a candidate.
- The closed interval always contains the minimum feasible answer.
- Use `left < right` with `right = mid` to avoid an infinite loop.
- Round integer division up when there is a remainder; parenthesize the ternary expression.
- Use `long` for accumulated hours to prevent integer overflow.
- Exit early when the required hours exceed the deadline; success can only be confirmed after all piles are processed.

### Testing

The implementation was logically reviewed against the problem constraints. Test cases cover standard examples, single piles, identical piles, minimum speed, unsorted input, and large values. Automated test execution was not verified in this session.
