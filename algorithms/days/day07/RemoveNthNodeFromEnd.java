package days.day07;

import java.util.Arrays;

public class RemoveNthNodeFromEnd {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Removes the nth node from the end of a singly linked list.
     *
     * Uses two pointers separated by n + 1 nodes
     * and a dummy node to handle head removal.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1);

        ListNode first = dummy;
        ListNode second = dummy;

        dummy.next = head;

        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;

        return dummy.next;
    }

    private static ListNode buildList(int... values) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    private static int[] toArray(ListNode head) {
        int length = 0;

        for (ListNode current = head;
             current != null;
             current = current.next) {
            length++;
        }

        int[] result = new int[length];
        int index = 0;

        while (head != null) {
            result[index++] = head.val;
            head = head.next;
        }

        return result;
    }

    private static void test(int[] input, int n, int[] expected) {
        ListNode head = buildList(input);
        ListNode result = removeNthFromEnd(head, n);

        int[] actual = toArray(result);
        boolean passed = Arrays.equals(expected, actual);

        System.out.println(
                (passed ? "PASS" : "FAIL")
                        + " | n=" + n
                        + " | Expected: " + Arrays.toString(expected)
                        + " | Actual: " + Arrays.toString(actual)
        );
    }

    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4, 5}, 2,
                new int[]{1, 2, 3, 5});

        test(new int[]{1}, 1,
                new int[]{});

        test(new int[]{1, 2}, 1,
                new int[]{1});

        test(new int[]{1, 2}, 2,
                new int[]{2});

        test(new int[]{1, 2, 3}, 2,
                new int[]{1, 3});

        test(new int[]{1, 2, 3, 4}, 4,
                new int[]{2, 3, 4});

        test(new int[]{1, 2, 3, 4}, 1,
                new int[]{1, 2, 3});
    }
}