package days.day11;

import java.util.Arrays;

public class OddEvenLinkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Groups nodes by their original positions using two dummy nodes.
     *
     * Odd positions (1, 3, 5, ...) come first,
     * followed by even positions (2, 4, 6, ...).
     *
     * Reuses existing nodes and preserves their relative order.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head the head of the linked list
     * @return the reordered linked list
     */
    public static ListNode oddEvenListWithDummy(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode oddDummy = new ListNode(-1);
        ListNode evenDummy = new ListNode(-1);

        ListNode currentOdd = oddDummy;
        ListNode currentEven = evenDummy;

        boolean isOdd = true;
        ListNode current = head;

        while (current != null) {
            if (isOdd) {
                currentOdd.next = current;
                currentOdd = currentOdd.next;
            } else {
                currentEven.next = current;
                currentEven = currentEven.next;
            }

            isOdd = !isOdd;
            current = current.next;
        }

        currentEven.next = null;
        currentOdd.next = evenDummy.next;

        return oddDummy.next;
    }

    /**
     * Groups nodes by their original positions in-place.
     *
     * Uses three references:
     * - odd: last node in the odd-position group
     * - even: last node in the even-position group
     * - evenHead: first node in the even-position group
     *
     * No new nodes are created.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head the head of the linked list
     * @return the reordered linked list
     */
    public static ListNode oddEvenListInPlace(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;

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
        // Build separate lists because both solutions mutate nodes.
        ListNode headWithDummy = buildList(input);
        ListNode headInPlace = buildList(input);

        int[] actualWithDummy =
                toArray(oddEvenListWithDummy(headWithDummy));

        int[] actualInPlace =
                toArray(oddEvenListInPlace(headInPlace));

        boolean dummyPassed =
                Arrays.equals(expected, actualWithDummy);

        boolean inPlacePassed =
                Arrays.equals(expected, actualInPlace);

        System.out.println(
                (dummyPassed ? "PASS" : "FAIL")
                + " [Dummy]   Input: " + Arrays.toString(input)
                + " | Expected: " + Arrays.toString(expected)
                + " | Actual: " + Arrays.toString(actualWithDummy)
        );

        System.out.println(
                (inPlacePassed ? "PASS" : "FAIL")
                + " [InPlace] Input: " + Arrays.toString(input)
                + " | Expected: " + Arrays.toString(expected)
                + " | Actual: " + Arrays.toString(actualInPlace)
        );

        System.out.println();
    }

    public static void main(String[] args) {

        // Standard examples
        test(new int[]{1, 2, 3, 4, 5},
             new int[]{1, 3, 5, 2, 4});

        test(new int[]{2, 1, 3, 5, 6, 4, 7},
             new int[]{2, 3, 6, 7, 1, 5, 4});

        // Empty list
        test(new int[]{},
             new int[]{});

        // Single node
        test(new int[]{10},
             new int[]{10});

        // Two nodes
        test(new int[]{10, 20},
             new int[]{10, 20});

        // Three nodes
        test(new int[]{10, 20, 30},
             new int[]{10, 30, 20});

        // Four nodes
        test(new int[]{10, 20, 30, 40},
             new int[]{10, 30, 20, 40});

        // Six nodes
        test(new int[]{1, 2, 3, 4, 5, 6},
             new int[]{1, 3, 5, 2, 4, 6});

        // All equal values
        test(new int[]{7, 7, 7, 7, 7},
             new int[]{7, 7, 7, 7, 7});

        // Negative values
        test(new int[]{-1, -2, -3, -4, -5},
             new int[]{-1, -3, -5, -2, -4});

        // Repeated values
        test(new int[]{1, 2, 1, 2, 1, 2},
             new int[]{1, 1, 1, 2, 2, 2});

        // Mixed values
        test(new int[]{8, 3, 9, 1, 4, 7, 2, 6},
             new int[]{8, 9, 4, 2, 3, 1, 7, 6});
    }
}
