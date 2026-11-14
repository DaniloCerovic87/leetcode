# Day 8 — Linked List: Two Pointers

## Problems

1. **LeetCode 141 — Linked List Cycle (Easy)**
2. **LeetCode 160 — Intersection of Two Linked Lists (Easy)**

## 1. Linked List Cycle (LC 141)

**Goal:** Determine whether a singly linked list contains a cycle without modifying the list.

**Approach — Floyd's Cycle Detection:**
- Initialize `slow` and `fast` at `head`.
- Move `slow` one node and `fast` two nodes per iteration.
- If the references become equal, a cycle exists.
- If `fast` reaches `null` (or its `next` is `null`), there is no cycle.

```java
public static boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            return true;
        }
    }
    return false;
}
```

**Complexity:** Time **O(n)**; extra space **O(1)**.

**Edge cases:** Empty list, single node, self-loop, cycle starting at the head, and acyclic list.

## 2. Intersection of Two Linked Lists (LC 160)

**Goal:** Return the first shared node **by reference**, or `null` if the lists do not intersect. Equal node values do not imply an intersection.

**Approach — Two Pointers with Head Switching:**
- Start `currentA` at `headA` and `currentB` at `headB`.
- Advance both pointers one node at a time.
- When a pointer reaches `null`, redirect it to the other list's head.
- Continue until the pointers are equal. They meet at the intersection, or both become `null` if there is none.
- Switching heads compensates for differences in list lengths: each pointer traverses both lists.

```java
public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode currentA = headA;
    ListNode currentB = headB;

    while (currentA != currentB) {
        currentA = (currentA == null) ? headB : currentA.next;
        currentB = (currentB == null) ? headA : currentB.next;
    }
    return currentA;
}
```

**Complexity:** Time **O(m + n)**; extra space **O(1)**.

**Edge cases:** Different list lengths, same head, no intersection, empty list, intersection at the last node, and identical values stored in distinct nodes.
