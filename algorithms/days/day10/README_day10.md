# Day 10 — Linked List: In-Place Node Manipulation

## Goals

Practice modifying a singly linked list **in place**, understanding the distinction between a node's identity and its value, and choosing the simplest pointer operations for each problem.

## 1. LeetCode 83 — Remove Duplicates from Sorted List (Easy)

**Problem:** Given the head of a **sorted** singly linked list, remove duplicate values so that each value appears exactly once. Return the head of the resulting list.

**Example:**

```text
Input:  1 -> 1 -> 2 -> 3 -> 3
Output: 1 -> 2 -> 3
```

### Approach

- Return `null` for an empty list.
- Start at `head` and compare each node with its successor.
- If their values match, bypass the successor using `current.next = current.next.next` **without advancing** `current`: there may be more duplicates.
- Otherwise, advance to the next node.
- Return the original `head`.

### Java solution

```java
public static ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
        return null;
    }

    ListNode current = head;

    while (current.next != null) {
        if (current.val == current.next.val) {
            current.next = current.next.next;
        } else {
            current = current.next;
        }
    }

    return head;
}
```

**Complexity:** O(n) time; O(1) auxiliary space.

**Edge cases:** Empty list, single node, no duplicates, all identical values, multiple consecutive duplicates, negative values.

**Key insight:** Because the list is sorted, equal values are adjacent. One pointer is sufficient; no hash set or dummy node is needed.

## 2. LeetCode 237 — Delete Node in a Linked List (Medium)

**Problem:** Delete a given node from a singly linked list when **only that node reference** is available—not the head or the previous node. The target is guaranteed to be a non-tail node. The method returns `void`.

**Example:**

```text
Before: 4 -> 5 -> 1 -> 9
             ^ node
After:  4 -> 1 -> 9
```

### Approach

- Copy the next node's value into the given node.
- Bypass the next node by redirecting `node.next` to `node.next.next`.
- No traversal is necessary.

### Java solution

```java
public static void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
}
```

**Complexity:** O(1) time; O(1) auxiliary space.

**Precondition:** `node != null` and `node.next != null` (guaranteed by the problem).

**Key nuance — object identity:** This does **not** physically unlink the original target object. That object remains in the list with its successor's value; the successor object is the one unlinked. The resulting list has the required sequence of values, which is what this problem tests. This distinction matters if other code holds references to individual nodes.

## Day 10 Takeaways

- Changing `next` changes the linked structure; merely moving a local pointer does not modify the list.
- When removing consecutive duplicates, do not advance the pointer immediately after bypassing a node.
- Distinguish **logical removal of a list element** from removal of a particular **object identity**.
- Prefer O(1) auxiliary space for in-place linked-list operations when possible.
- Both problems were solved with optimal time and space complexity.
