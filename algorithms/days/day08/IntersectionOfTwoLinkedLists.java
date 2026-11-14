package days.day08;

public class IntersectionOfTwoLinkedLists {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Finds the node at which two singly linked lists intersect.
     * <p>
     * Two lists intersect only if they share the same node
     * reference, not merely nodes with equal values.
     * <p>
     * Target Time Complexity: O(m + n)
     * Target Space Complexity: O(1)
     *
     * @param headA head of the first linked list
     * @param headB head of the second linked list
     * @return the intersection node, or null if no intersection exists
     */
    public static ListNode getIntersectionNode(
            ListNode headA,
            ListNode headB
    ) {
        ListNode currentA = headA;
        ListNode currentB = headB;

        while (currentA != currentB) {
            if (currentA == null) {
                currentA = headB;
            } else {
                currentA = currentA.next;
            }

            if (currentB == null) {
                currentB = headA;
            } else {
                currentB = currentB.next;
            }
        }

        return currentA;
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

    private static void connect(ListNode head, ListNode shared) {
        if (head == null) {
            return;
        }

        ListNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = shared;
    }

    private static void test(
            String name,
            ListNode headA,
            ListNode headB,
            ListNode expected
    ) {
        ListNode actual = getIntersectionNode(headA, headB);

        boolean passed = actual == expected;

        System.out.println(
                (passed ? "PASS" : "FAIL")
                        + " | " + name
                        + " | Expected: "
                        + (expected == null ? "null" : expected.val)
                        + " | Actual: "
                        + (actual == null ? "null" : actual.val)
        );
    }

    public static void main(String[] args) {

        // Test 1: Lists intersect at node 8
        ListNode shared1 = buildList(8, 4, 5);
        ListNode a1 = buildList(4, 1);
        ListNode b1 = buildList(5, 6, 1);

        connect(a1, shared1);
        connect(b1, shared1);

        test("Intersection at 8", a1, b1, shared1);

        // Test 2: Lists intersect at node 2
        ListNode shared2 = buildList(2, 4);
        ListNode a2 = buildList(1, 9, 1);
        ListNode b2 = buildList(3);

        connect(a2, shared2);
        connect(b2, shared2);

        test("Intersection at 2", a2, b2, shared2);

        // Test 3: No intersection
        ListNode a3 = buildList(2, 6, 4);
        ListNode b3 = buildList(1, 5);

        test("No intersection", a3, b3, null);

        // Test 4: Both heads point to the same list
        ListNode shared4 = buildList(1, 2, 3);

        test("Same head", shared4, shared4, shared4);

        // Test 5: Both lists are empty
        test("Both empty", null, null, null);

        // Test 6: One list is empty
        ListNode a6 = buildList(1, 2, 3);

        test("One empty", a6, null, null);

        // Test 7: Same values, different node references
        ListNode a7 = buildList(1, 2, 3);
        ListNode b7 = buildList(1, 2, 3);

        test("Same values, different nodes", a7, b7, null);

        // Test 8: Intersection at the last node
        ListNode shared8 = new ListNode(10);
        ListNode a8 = buildList(1, 2, 3, 4);
        ListNode b8 = buildList(5, 6);

        connect(a8, shared8);
        connect(b8, shared8);

        test("Intersection at tail", a8, b8, shared8);
    }
}