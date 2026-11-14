package days.day07;

public class MiddleOfTheLinkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Returns the middle node of a singly linked list.
     * If the list has two middle nodes, returns the second one.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
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

    private static void test(int expected, int... values) {
        ListNode head = buildList(values);
        ListNode result = middleNode(head);

        boolean passed = result != null && result.val == expected;

        System.out.println(
                (passed ? "PASS" : "FAIL")
                        + " | Expected: " + expected
                        + " | Actual: "
                        + (result == null ? "null" : result.val)
        );
    }

    public static void main(String[] args) {
        test(3, 1, 2, 3, 4, 5);
        test(4, 1, 2, 3, 4, 5, 6);
        test(1, 1);
        test(2, 1, 2);
        test(6, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
    }
}