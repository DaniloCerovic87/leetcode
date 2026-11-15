# Day 9 — Linked Lists: Merge and Palindrome

## Problems

### 1. LeetCode 21 — Merge Two Sorted Lists (Easy)

**Problem:** Given the heads of two sorted singly linked lists, merge them into one sorted linked list by reusing the existing nodes.

**Approach:**
- Create a dummy node and maintain a `current` tail pointer.
- While both lists have nodes, attach the node with the smaller value and advance that list's pointer.
- When one list is exhausted, attach the remaining part of the other list directly.
- Return `dummy.next`.

**Complexity:**
- Time: **O(n + m)**, where `n` and `m` are the lengths of the input lists.
- Auxiliary space: **O(1)**.

**Edge cases:** Both lists empty; one list empty; duplicate values; negative values; lists of different lengths.

**Key takeaway:** Because each input list is already sorted, the remaining suffix can be linked directly without another traversal.

### 2. LeetCode 234 — Palindrome Linked List (Easy)

**Problem:** Determine whether the values in a singly linked list read the same forward and backward.

**Approach:**
- Use `slow` and `fast` pointers to locate the middle.
- For odd-length lists, skip the middle node.
- Reverse the second half of the list in place using `prev`, `current`, and `next` pointers.
- Compare nodes from the head and from the reversed second half until the second pointer reaches `null`.

**Complexity:**
- Time: **O(n)**.
- Auxiliary space: **O(1)**.

**Edge cases:** Empty list; single node; even- and odd-length lists; repeated values; non-palindromes.

**Implementation note:** This approach modifies the original list. If preserving the list is required, reverse the second half again and reconnect it before returning. The LeetCode task does not require restoration.

**Key takeaway:** Combining fast/slow pointers with in-place reversal makes it possible to compare both halves without an auxiliary array or stack.

## Day 9 Summary

- Practiced **dummy nodes**, **pointer manipulation**, **fast/slow pointers**, and **in-place linked-list reversal**.
- Solved both problems with optimal time and auxiliary-space complexity.
- Learned to stop palindrome comparison when the reversed second half is exhausted (`second != null`).
