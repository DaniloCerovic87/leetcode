package days.day06;

import java.util.Arrays;

public class ReverseLinkedList {

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
     * LeetCode 206 - Reverse Linked List (Easy)
     * <p>
     * Given the head of a singly linked list,
     * reverse the list and return the new head.
     * <p>
     * Example:
     * Input:  1 -> 2 -> 3 -> 4 -> 5
     * Output: 5 -> 4 -> 3 -> 2 -> 1
     *
     * @param head the first node of the linked list
     * @return the head of the reversed linked list
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4, 5},
                new int[]{5, 4, 3, 2, 1});

        test(new int[]{1, 2}, new int[]{2, 1});

        test(new int[]{1}, new int[]{1});

        test(new int[]{}, new int[]{});

        test(new int[]{1, 2, 3}, new int[]{3, 2, 1});

        test(new int[]{10, 20, 30, 40},
                new int[]{40, 30, 20, 10});
    }

    private static void test(int[] input, int[] expected) {
        ListNode head = buildList(input);
        ListNode reversed = reverseList(head);
        int[] actual = toArray(reversed, input.length);

        System.out.printf(
                "Expected: %s, Actual: %s -> %s%n",
                Arrays.toString(expected),
                Arrays.toString(actual),
                Arrays.equals(expected, actual) ? "PASS" : "FAIL"
        );
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
     * The limit prevents infinite traversal
     * if the implementation creates a cycle.
     */
    private static int[] toArray(ListNode head, int limit) {
        int[] buffer = new int[limit + 1];
        int count = 0;

        while (head != null && count < buffer.length) {
            buffer[count++] = head.val;
            head = head.next;
        }

        return Arrays.copyOf(buffer, count);
    }
}
