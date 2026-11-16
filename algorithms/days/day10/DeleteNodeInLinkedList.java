package days.day10;

import java.util.Arrays;

public class DeleteNodeInLinkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Deletes the given node from a singly linked list.
     *
     * Only the reference to the node is provided.
     * The node is guaranteed not to be the last node.
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param node the node to delete
     */
    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
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

    private static ListNode getNodeAt(ListNode head, int index) {
        ListNode current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
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
            int[] input,
            int nodeIndex,
            int[] expected
    ) {
        ListNode head = buildList(input);
        ListNode node = getNodeAt(head, nodeIndex);

        deleteNode(node);

        int[] actual = toArray(head);
        boolean passed = Arrays.equals(expected, actual);

        System.out.println(
                (passed ? "PASS" : "FAIL")
                        + " | Input: " + Arrays.toString(input)
                        + " | Node index: " + nodeIndex
                        + " | Expected: " + Arrays.toString(expected)
                        + " | Actual: " + Arrays.toString(actual)
        );
    }

    public static void main(String[] args) {
        test(new int[]{4, 5, 1, 9}, 1, new int[]{4, 1, 9});
        test(new int[]{4, 5, 1, 9}, 2, new int[]{4, 5, 9});
        test(new int[]{1, 2, 3}, 0, new int[]{2, 3});
        test(new int[]{1, 2}, 0, new int[]{2});
        test(new int[]{1, 2, 3, 4}, 2, new int[]{1, 2, 4});
        test(new int[]{-3, -2, -1, 0}, 1, new int[]{-3, -1, 0});
        test(new int[]{1, 2, 3, 4, 5, 6}, 3, new int[]{1, 2, 3, 5, 6});
        test(new int[]{10, 20}, 0, new int[]{20});
        test(new int[]{10, 20, 30, 40, 50}, 1,
             new int[]{10, 30, 40, 50});
        test(new int[]{10, 20, 30, 40, 50}, 3,
             new int[]{10, 20, 30, 50});
    }
}
