# Day 11 — Linked List Partitioning and Odd–Even Reordering

## Goals

Practice **relinking existing nodes** without creating a new list of values, using dummy nodes and in-place pointer manipulation. Both solutions run in **O(n) time** and use **O(1) auxiliary space**.

## 1. LeetCode 86 — Partition List

**Problem:** Given the head of a linked list and an integer `x`, move all nodes with values less than `x` before nodes with values greater than or equal to `x`. Preserve the original relative order within both groups.

**Example:**

```text
Input:  1 -> 4 -> 3 -> 2 -> 5 -> 2, x = 3
Output: 1 -> 2 -> 2 -> 4 -> 3 -> 5
```

### Approach: Two dummy heads

Maintain two chains:

- `smallerDummy` for nodes where `val < x`
- `equalOrGreaterDummy` for nodes where `val >= x`

Append each original node to the appropriate chain, terminate the second chain with `null`, and connect the chains.

```java
public static ListNode partition(ListNode head, int x) {
    ListNode smallerDummy = new ListNode(-1);
    ListNode equalOrGreaterDummy = new ListNode(-1);

    ListNode current = head;
    ListNode currentSmaller = smallerDummy;
    ListNode currentEqualOrGreater = equalOrGreaterDummy;

    while (current != null) {
        if (current.val < x) {
            currentSmaller.next = current;
            currentSmaller = currentSmaller.next;
        } else {
            currentEqualOrGreater.next = current;
            currentEqualOrGreater = currentEqualOrGreater.next;
        }
        current = current.next;
    }

    currentEqualOrGreater.next = null;
    currentSmaller.next = equalOrGreaterDummy.next;
    return smallerDummy.next;
}
```

**Complexity:** O(n) time, O(1) extra space.

**Key detail:** `currentEqualOrGreater.next = null` prevents stale links from the original list from creating an incorrect tail or a cycle when the two chains are joined.

## 2. LeetCode 328 — Odd Even Linked List

**Problem:** Group nodes at **odd positions** (1, 3, 5, ...) before nodes at **even positions** (2, 4, 6, ...), preserving relative order. Odd/even refers to **positions, not node values**.

**Example:**

```text
Input:  1 -> 2 -> 3 -> 4 -> 5
Output: 1 -> 3 -> 5 -> 2 -> 4
```

### Approach A: Two dummy heads

Build odd-position and even-position chains by alternating a boolean flag. Reuse existing nodes; only two sentinel nodes are allocated.

```java
public static ListNode oddEvenListWithDummy(ListNode head) {
    if (head == null) {
        return null;
    }

    ListNode oddDummy = new ListNode(-1);
    ListNode evenDummy = new ListNode(-1);
    ListNode currentOdd = oddDummy;
    ListNode currentEven = evenDummy;

    boolean isOdd = true;
    ListNode current = head;

    while (current != null) {
        if (isOdd) {
            currentOdd.next = current;
            currentOdd = currentOdd.next;
        } else {
            currentEven.next = current;
            currentEven = currentEven.next;
        }

        isOdd = !isOdd;
        current = current.next;
    }

    currentEven.next = null;
    currentOdd.next = evenDummy.next;
    return oddDummy.next;
}
```

**Complexity:** O(n) time, O(1) extra space.

### Approach B: In-place pointer rewiring (no dummy nodes)

Keep three references:

- `odd`: the tail of the odd-position chain
- `even`: the tail of the even-position chain
- `evenHead`: the original second node, saved so the chains can be joined

```java
public static ListNode oddEvenListInPlace(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }

    ListNode odd = head;
    ListNode even = head.next;
    ListNode evenHead = even;

    while (even != null && even.next != null) {
        odd.next = even.next;
        odd = odd.next;

        even.next = odd.next;
        even = even.next;
    }

    odd.next = evenHead;
    return head;
}
```

**Complexity:** O(n) time, O(1) extra space. No nodes are allocated.

### Step-by-step trace

Original list:

```text
1 -> 2 -> 3 -> 4 -> 5 -> null
odd = 1, even = 2, evenHead = 2
```

**Iteration 1:**

```text
odd.next = even.next   => 1.next = 3
odd = odd.next         => odd = 3
even.next = odd.next   => 2.next = 4
even = even.next       => even = 4
```

At this point, the chains are **not fully separated**: the links are `1 -> 3 -> 4 -> 5` and `2 -> 4 -> 5`. This is temporary and expected.

**Iteration 2:**

```text
odd.next = even.next   => 3.next = 5
odd = odd.next         => odd = 5
even.next = odd.next   => 4.next = null
even = even.next       => even = null
```

Now the chains are:

```text
Odd:  1 -> 3 -> 5 -> null
Even: 2 -> 4 -> null
```

Finally:

```text
odd.next = evenHead    => 5.next = 2
Result: 1 -> 3 -> 5 -> 2 -> 4 -> null
```

### Why is the loop condition `even != null && even.next != null`?

- `even != null` protects against dereferencing `null`.
- `even.next != null` ensures there is another odd-position node to attach.
- For an **odd-length** list, `even` eventually becomes `null`.
- For an **even-length** list, `even` ends at the final node and `even.next` is `null`.
- Java's `&&` short-circuits, so `even.next` is not accessed when `even` is `null`.

### Why does `even.next = odd.next` work?

After `odd.next = even.next` and `odd = odd.next`, `odd` points to the newly attached odd-position node. Its `next` still points to the **following even-position node**. Therefore `even.next = odd.next` skips the odd-position node and extends the even chain.

## Edge Cases to Test

- Empty list (`null`)
- One node
- Two nodes
- Odd and even list lengths
- Duplicate values
- Negative values
- For LC 86: all nodes below `x`, all nodes at or above `x`, and mixed groups

## Key Takeaways

1. A dummy node simplifies building a chain without special handling for its first element.
2. Reuse existing nodes: changing `next` references is enough.
3. Preserve relative order by appending nodes in their original traversal order.
4. Carefully terminate the last chain before connecting chains to avoid stale links and cycles.
5. In-place odd/even reordering works because each iteration skips one node in each chain.
6. Both LC 328 approaches are asymptotically optimal; the dummy-node solution is a reusable pattern, while the in-place solution exploits the alternating positions.
