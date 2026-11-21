# Day 13 — Monotonic Stack

## Topics Covered

- Stack (LIFO)
- Java `Deque` and `ArrayDeque`
- Monotonic decreasing stack
- Storing indices instead of values
- Next Greater Element pattern
- Amortized O(n) complexity

## LeetCode 739 — Daily Temperatures (Medium)

**Problem:** https://leetcode.com/problems/daily-temperatures/

Given an array of daily temperatures, return an array in which each element is the number of days until the next **strictly warmer** temperature. If there is no warmer future day, return `0`.

### Example

```text
Input:  [73, 74, 75, 71, 69, 72, 76, 73]
Output: [ 1,  1,  4,  2,  1,  1,  0,  0]
```

## Approach — Monotonic Decreasing Stack

Maintain a stack of **indices** whose next warmer day has not yet been found. Temperatures at those indices are non-increasing from the bottom to the top of the stack.

For each current day `i`:

1. While the stack is not empty and the current temperature is **strictly greater** than the temperature at the top index, pop that previous index.
2. Write `results[previousIndex] = i - previousIndex`.
3. Push the current index `i`.

Days left on the stack have no warmer future day. Their results remain zero because Java initializes `int[]` with zeros.

## My Solution (Java)

```java
public static int[] dailyTemperatures(int[] temperatures) {
    int[] results = new int[temperatures.length];
    Deque<Integer> monotonicStack = new ArrayDeque<>();

    for (int i = 0; i < temperatures.length; i++) {
        while (!monotonicStack.isEmpty()
                && temperatures[monotonicStack.peek()] < temperatures[i]) {
            int prevLowerIndex = monotonicStack.pop();
            results[prevLowerIndex] = i - prevLowerIndex;
        }
        monotonicStack.push(i);
    }
    return results;
}
```

Imports: `java.util.ArrayDeque` and `java.util.Deque`.

## Complexity

- **Time: O(n)** — each index is pushed exactly once and popped at most once. Although the loops are nested, total stack operations are linear.
- **Auxiliary space: O(n)** — the stack may contain all indices. The output array also uses O(n) space.

## Key Observations

- **Why indices?** They let us calculate the waiting time with `i - previousIndex`.
- **Why `while` rather than `if`?** One warmer day can resolve several earlier days.
- **Why strictly `<`?** Equal temperatures are not warmer.
- **Why no final stack processing?** Unresolved days correctly retain zero in the output.
- **Why only future days?** A current day resolves earlier days; earlier warmer temperatures cannot answer a future day's question.

## Test Cases

| Test | Input | Expected |
|---|---|---|
| Standard example | `[73,74,75,71,69,72,76,73]` | `[1,1,4,2,1,1,0,0]` |
| Increasing | `[30,40,50,60]` | `[1,1,1,0]` |
| Three increasing | `[30,60,90]` | `[1,1,0]` |
| Decreasing | `[90,80,70,60]` | `[0,0,0,0]` |
| All equal | `[70,70,70,70]` | `[0,0,0,0]` |
| Single element | `[75]` | `[0]` |
| Warmer after several days | `[75,71,69,72,76]` | `[4,2,1,1,0]` |
| Repeated temperatures | `[73,73,75,73,76]` | `[2,1,2,1,0]` |
| Warmer at the end | `[80,70,60,90]` | `[3,2,1,0]` |
| Mixed temperatures | `[60,65,62,70,68,75]` | `[1,2,1,2,1,0]` |

## Key Takeaway

A monotonic stack keeps unresolved indices in a useful order. When a new value arrives, it can resolve previous values without repeatedly scanning the rest of the array, turning a potential O(n²) approach into O(n).
