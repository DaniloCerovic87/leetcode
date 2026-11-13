package days.day06;

import java.util.Arrays;

public class RemoveLinkedListElements {

    /**
     * Represents a node in a singly linked list.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * LeetCode 203 - Remove Linked List Elements (Easy)
     *
     * Removes all nodes whose value equals val.
     *
     * Uses a dummy node to handle removal of the head
     * without requiring special-case logic.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head the first node of the linked list
     * @param val the value to remove
     * @return the head of the modified linked list
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }

            curr = curr.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        test(new int[]{1, 2, 6, 3, 4, 5, 6}, 6,
             new int[]{1, 2, 3, 4, 5});

        test(new int[]{}, 1, new int[]{});

        test(new int[]{7, 7, 7, 7}, 7, new int[]{});

        test(new int[]{1, 2, 3}, 4,
             new int[]{1, 2, 3});

        test(new int[]{5}, 5, new int[]{});

        test(new int[]{5}, 3, new int[]{5});

        test(new int[]{2, 2, 1, 2, 3, 2}, 2,
             new int[]{1, 3});
    }

    /**
     * Builds a linked list from an array.
     */
    private static ListNode buildList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    /**
     * Converts a linked list to an array.
     */
    private static int[] toArray(ListNode head) {
        int size = 0;
        ListNode current = head;

        while (current != null) {
            size++;
            current = current.next;
        }

        int[] result = new int[size];
        current = head;

        for (int i = 0; i < size; i++) {
            result[i] = current.val;
            current = current.next;
        }

        return result;
    }

    /**
     * Executes a test case and prints its result.
     */
    private static void test(int[] input, int val, int[] expected) {
        ListNode head = buildList(input);
        ListNode result = removeElements(head, val);
        int[] actual = toArray(result);

        System.out.printf(
                "Expected: %s, Actual: %s -> %s%n",
                Arrays.toString(expected),
                Arrays.toString(actual),
                Arrays.equals(expected, actual) ? "PASS" : "FAIL"
        );
    }
}
