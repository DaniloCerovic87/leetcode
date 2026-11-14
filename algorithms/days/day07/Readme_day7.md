# Day 7 — Linked List: Two Pointers

## Overview

Day 7 focuses on using two pointers to navigate singly linked lists efficiently. Both problems can be solved in **O(n) time** and **O(1) auxiliary space**, without converting the list to an array.

## Problems

### 1. [876. Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/) — Easy

**Goal:** Return the middle node of a singly linked list. If the list has an even number of nodes, return the **second** middle node.

**Approach — Slow and Fast Pointers**

- Initialize `slow` and `fast` at `head`.
- Move `slow` one node at a time and `fast` two nodes at a time.
- Continue while `fast != null && fast.next != null`.
- When the loop ends, `slow` points to the required middle node.

**Example:**

```text
Input:  1 -> 2 -> 3 -> 4 -> 5 -> 6
Output: 4 -> 5 -> 6
```

**Complexity:** Time **O(n)**; auxiliary space **O(1)**.

### 2. [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) — Medium

**Goal:** Remove the `n`th node from the end of a singly linked list and return its head.

**Approach — Two Pointers with a Dummy Node**

- Create a `dummy` node and connect it to `head`.
- Initialize `first` and `second` at `dummy`.
- Advance `first` by `n + 1` links to establish a gap.
- Move both pointers one node at a time until `first == null`.
- `second` now points to the node immediately before the one to remove.
- Bypass the target with `second.next = second.next.next`.
- Return `dummy.next`, which also handles removing the original head.

**Example:**

```text
Input:  1 -> 2 -> 3 -> 4 -> 5, n = 2
Output: 1 -> 2 -> 3 -> 5
```

**Edge cases:** Removing the first node, removing the last node, and a single-node list. The problem guarantees `1 <= n <= list length`.

**Complexity:** Time **O(n)**; auxiliary space **O(1)**.

## Key Concepts

- **Slow/fast pointers:** Different movement speeds locate the middle without counting nodes first.
- **Fixed pointer gap:** A distance of `n + 1` lets the trailing pointer stop immediately before the node to delete.
- **Dummy node:** Simplifies deletions, especially when the head itself must be removed.
- **Returning the head:** After deletion, return `dummy.next`, not a traversal pointer.
