package days.day09;

import java.util.Arrays;

public class MergeTwoSortedLists {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Merges two sorted singly linked lists into one sorted list.
     * <p>
     * The result should reuse the existing nodes without
     * creating new nodes for the merged elements.
     * <p>
     * Target Time Complexity: O(n + m)
     * Target Space Complexity: O(1)
     *
     * @param list1 head of the first sorted linked list
     * @param list2 head of the second sorted linked list
     * @return head of the merged sorted linked list
     */
    public static ListNode mergeTwoLists(
            ListNode list1,
            ListNode list2
    ) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        ListNode current1 = list1;
        ListNode current2 = list2;

        while (current1 != null && current2 != null) {
            if (current1.val > current2.val) {
                current.next = current2;
                current = current2;
                current2 = current2.next;
            } else {
                current.next = current1;
                current = current1;
                current1 = current1.next;
            }
        }

        current.next = (current1 != null) ? current1 : current2;
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

    private static void test(
            int[] input1,
            int[] input2,
            int[] expected
    ) {
        ListNode list1 = buildList(input1);
        ListNode list2 = buildList(input2);

        ListNode result = mergeTwoLists(list1, list2);
        int[] actual = toArray(result);

        boolean passed = Arrays.equals(expected, actual);

        System.out.println(
                (passed ? "PASS" : "FAIL")
                        + " | List1: " + Arrays.toString(input1)
                        + " | List2: " + Arrays.toString(input2)
                        + " | Expected: " + Arrays.toString(expected)
                        + " | Actual: " + Arrays.toString(actual)
        );
    }

    public static void main(String[] args) {

        // Test 1: Standard merge
        test(
                new int[]{1, 2, 4},
                new int[]{1, 3, 5},
                new int[]{1, 1, 2, 3, 4, 5}
        );

        // Test 2: Both lists empty
        test(
                new int[]{},
                new int[]{},
                new int[]{}
        );

        // Test 3: First list empty
        test(
                new int[]{},
                new int[]{0},
                new int[]{0}
        );

        // Test 4: Second list empty
        test(
                new int[]{1, 2, 3},
                new int[]{},
                new int[]{1, 2, 3}
        );

        // Test 5: All elements in first list are smaller
        test(
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{1, 2, 3, 4, 5, 6}
        );

        // Test 6: All elements in second list are smaller
        test(
                new int[]{4, 5, 6},
                new int[]{1, 2, 3},
                new int[]{1, 2, 3, 4, 5, 6}
        );

        // Test 7: Duplicate values
        test(
                new int[]{1, 1, 2},
                new int[]{1, 2, 2},
                new int[]{1, 1, 1, 2, 2, 2}
        );

        // Test 8: Negative values
        test(
                new int[]{-10, -3, 0},
                new int[]{-5, -2, 4},
                new int[]{-10, -5, -3, -2, 0, 4}
        );

        // Test 9: Lists of different lengths
        test(
                new int[]{1, 5},
                new int[]{2, 3, 4, 6, 7},
                new int[]{1, 2, 3, 4, 5, 6, 7}
        );
    }
}