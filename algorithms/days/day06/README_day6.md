# Day 6 — Singly Linked Lists

## Overview

Practiced in-place manipulation of singly linked lists, focusing on node references, pointer updates, and handling changes to the head of a list.

## Completed Problems

| Problem | Difficulty | Technique | Time | Auxiliary Space |
| --- | --- | --- | --- | --- |
| [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) | Easy | Three pointers; in-place link reversal | O(n) | O(1) |
| [203. Remove Linked List Elements](https://leetcode.com/problems/remove-linked-list-elements/) | Easy | Dummy node; previous/current pointers | O(n) | O(1) |

## 206 — Reverse Linked List

Reverse a singly linked list by changing each node's `next` reference rather than allocating new nodes.

- Keep `prev` as the head of the already-reversed portion and `current` as the node being processed.
- Save `current.next` before changing the link so the unprocessed portion remains reachable.
- Redirect `current.next` to `prev`, then advance both pointers.
- Return `prev`, which points to the new head after `current` becomes `null`.

The algorithm handles empty and single-node lists without special-case logic.

## 203 — Remove Linked List Elements

Remove every node whose value equals a target value.

- Introduce a `dummy` node before the original head so removing the first node requires no special handling.
- Maintain `prev` as the last retained node and `curr` as the node under inspection.
- If `curr.val == val`, bypass it with `prev.next = curr.next` **without moving `prev`**.
- Otherwise, advance `prev` to `curr`.
- Advance `curr` on every iteration and return `dummy.next`.

This handles consecutive matches, removal of the original head, removal of every node, and empty input.

## Key Concepts

- `ArrayList` insertion/removal at arbitrary positions can require shifting O(n) elements; linked-list link changes take O(1) once the relevant nodes are known.
- Assigning a pointer variable changes which node it references; assigning `node.next` changes the list structure.
- Pointer update order matters: save any link that would otherwise be lost.
- A dummy node simplifies deletions when the head may change.
