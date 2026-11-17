package days.day11;

import java.util.Arrays;

public class PartitionList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Partitions a singly linked list around a given value x.
     * <p>
     * All nodes with values less than x must appear before
     * nodes with values greater than or equal to x.
     * <p>
     * The original relative order of nodes within each
     * partition must be preserved.
     * <p>
     * Existing nodes should be reused.
     * <p>
     * Target Time Complexity: O(n)
     * Target Space Complexity: O(1)
     *
     * @param head the head of the linked list
     * @param x    the partition value
     * @return the head of the partitioned linked list
     */
    public static ListNode partition(ListNode head, int x) {
        ListNode smallerDummy = new ListNode(-1);
        ListNode equalOrGreaterDummy = new ListNode(-1);

        ListNode current = head;
        ListNode currentSmaller = smallerDummy;
        ListNode currentEqualOrGreater = equalOrGreaterDummy;

        while (current != null) {
            if (current.val < x) {
                currentSmaller.next = current;
                currentSmaller = currentSmaller.next;
            } else {
                currentEqualOrGreater.next = current;
                currentEqualOrGreater = currentEqualOrGreater.next;
            }
            current = current.next;
        }

        currentEqualOrGreater.next = null;
        currentSmaller.next = equalOrGreaterDummy.next;
        return smallerDummy.next;
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

    private static void test(int[] input, int x, int[] expected) {
        ListNode head = buildList(input);
        ListNode result = partition(head, x);
        int[] actual = toArray(result);

        boolean passed = Arrays.equals(expected, actual);

        System.out.println(
                (passed ? "PASS" : "FAIL")
                        + " | Input: " + Arrays.toString(input)
                        + " | x: " + x
                        + " | Expected: " + Arrays.toString(expected)
                        + " | Actual: " + Arrays.toString(actual)
        );
    }

    public static void main(String[] args) {

        // Standard examples
        test(new int[]{1, 4, 3, 2, 5, 2}, 3,
                new int[]{1, 2, 2, 4, 3, 5});

        test(new int[]{2, 1}, 2,
                new int[]{1, 2});

        // Empty and single-node lists
        test(new int[]{}, 3,
                new int[]{});

        test(new int[]{1}, 3,
                new int[]{1});

        test(new int[]{5}, 3,
                new int[]{5});

        // All nodes in one partition
        test(new int[]{1, 2, 3}, 5,
                new int[]{1, 2, 3});

        test(new int[]{5, 6, 7}, 3,
                new int[]{5, 6, 7});

        // Already partitioned
        test(new int[]{1, 2, 4, 5}, 3,
                new int[]{1, 2, 4, 5});

        // Reverse order
        test(new int[]{5, 4, 3, 2, 1}, 3,
                new int[]{2, 1, 5, 4, 3});

        // All values equal to x
        test(new int[]{3, 3, 3}, 3,
                new int[]{3, 3, 3});

        // Negative values
        test(new int[]{-1, 5, -3, 2, 0}, 0,
                new int[]{-1, -3, 5, 2, 0});

        // Repeated partition values
        test(new int[]{3, 1, 3, 2, 3, 1}, 3,
                new int[]{1, 2, 1, 3, 3, 3});
    }
}
