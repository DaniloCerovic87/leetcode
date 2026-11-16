package days.day10;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Removes duplicate values from a sorted linked list.
     *
     * Each distinct value appears exactly once.
     * The existing nodes are reused.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head the head of the sorted linked list
     * @return the head of the list without duplicates
     */
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
        ListNode current = head;

        while (current != null) {
            length++;
            current = current.next;
        }

        int[] result = new int[length];
        current = head;

        for (int i = 0; i < length; i++) {
            result[i] = current.val;
            current = current.next;
        }

        return result;
    }

    private static void test(int[] input, int[] expected) {
        ListNode head = buildList(input);
        ListNode result = deleteDuplicates(head);

        int[] actual = toArray(result);
        boolean passed = Arrays.equals(expected, actual);

        System.out.println(
                (passed ? "PASS" : "FAIL")
                        + " | Input: " + Arrays.toString(input)
                        + " | Expected: " + Arrays.toString(expected)
                        + " | Actual: " + Arrays.toString(actual)
        );
    }

    public static void main(String[] args) {
        test(new int[]{1, 1, 2}, new int[]{1, 2});
        test(new int[]{1, 1, 2, 3, 3}, new int[]{1, 2, 3});
        test(new int[]{}, new int[]{});
        test(new int[]{1}, new int[]{1});
        test(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5});
        test(new int[]{2, 2, 2, 2, 2}, new int[]{2});
        test(new int[]{1, 1, 1, 2, 3}, new int[]{1, 2, 3});
        test(new int[]{1, 2, 3, 3, 3}, new int[]{1, 2, 3});
        test(new int[]{-3, -3, -2, -1, -1, 0}, new int[]{-3, -2, -1, 0});
        test(new int[]{1, 1, 1, 2, 2, 3, 3, 4, 4, 4, 5},
             new int[]{1, 2, 3, 4, 5});
    }
}
