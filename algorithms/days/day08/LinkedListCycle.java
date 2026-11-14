package days.day08;

public class LinkedListCycle {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Determines whether a singly linked list contains a cycle.
     * <p>
     * A cycle exists if a node can be reached again by
     * continuously following the next pointer.
     * <p>
     * Target Time Complexity: O(n)
     * Target Space Complexity: O(1)
     *
     * @param head the head of the linked list
     * @return true if the list contains a cycle, false otherwise
     */
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    /**
     * Builds a linked list and optionally creates a cycle.
     *
     * @param pos index of the node that the tail connects to
     *            (-1 means no cycle)
     */
    private static ListNode buildList(int[] values, int pos) {
        if (values.length == 0) {
            return null;
        }

        ListNode[] nodes = new ListNode[values.length];

        for (int i = 0; i < values.length; i++) {
            nodes[i] = new ListNode(values[i]);
        }

        for (int i = 0; i < values.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        if (pos >= 0 && pos < values.length) {
            nodes[values.length - 1].next = nodes[pos];
        }

        return nodes[0];
    }

    private static void test(int[] values, int pos, boolean expected) {
        ListNode head = buildList(values, pos);
        boolean actual = hasCycle(head);

        System.out.println(
                (actual == expected ? "PASS" : "FAIL")
                        + " | pos=" + pos
                        + " | Expected: " + expected
                        + " | Actual: " + actual
        );
    }

    public static void main(String[] args) {

        // Test 1: Cycle connecting tail to node at index 1
        test(new int[]{3, 2, 0, -4}, 1, true);

        // Test 2: Two nodes forming a cycle
        test(new int[]{1, 2}, 0, true);

        // Test 3: Single node without a cycle
        test(new int[]{1}, -1, false);

        // Test 4: Single node pointing to itself
        test(new int[]{1}, 0, true);

        // Test 5: Multiple nodes without a cycle
        test(new int[]{1, 2, 3, 4, 5}, -1, false);

        // Test 6: Cycle connecting tail to head
        test(new int[]{1, 2, 3, 4, 5}, 0, true);

        // Test 7: Empty list
        test(new int[]{}, -1, false);

        // Test 8: Cycle connecting tail to itself
        test(new int[]{1, 2, 3, 4, 5}, 4, true);
    }
}