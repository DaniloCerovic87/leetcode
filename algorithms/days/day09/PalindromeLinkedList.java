package days.day09;

public class PalindromeLinkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Determines whether a singly linked list is a palindrome.
     * <p>
     * A palindrome reads the same forward and backward.
     * <p>
     * Target Time Complexity: O(n)
     * Target Space Complexity: O(1)
     *
     * @param head the head of the linked list
     * @return true if the list is a palindrome, false otherwise
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        ListNode prev = null;
        ListNode current = slow;

        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        ListNode first = head;
        ListNode second = prev;

        while (second != null) {
            if (first.val != second.val) {
                return false;
            }

            first = first.next;
            second = second.next;
        }

        return true;
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

    private static void test(boolean expected, int... values) {
        ListNode head = buildList(values);
        boolean actual = isPalindrome(head);

        System.out.println(
                (actual == expected ? "PASS" : "FAIL")
                        + " | Expected: " + expected
                        + " | Actual: " + actual
                        + " | Length: " + values.length
        );
    }

    public static void main(String[] args) {

        // Test 1: Even-length palindrome
        test(true, 1, 2, 2, 1);

        // Test 2: Odd-length palindrome
        test(true, 1, 2, 3, 2, 1);

        // Test 3: Not a palindrome
        test(false, 1, 2, 3, 4);

        // Test 4: Single node
        test(true, 1);

        // Test 5: Two equal nodes
        test(true, 1, 1);

        // Test 6: Two different nodes
        test(false, 1, 2);

        // Test 7: All equal values
        test(true, 5, 5, 5, 5, 5);

        // Test 8: Longer palindrome
        test(true, 1, 2, 3, 4, 3, 2, 1);

        // Test 9: Almost a palindrome
        test(false, 1, 2, 3, 4, 2, 1);

        // Test 10: Empty list
        test(true);
    }
}
