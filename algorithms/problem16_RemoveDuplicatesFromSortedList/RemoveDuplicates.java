package problem16_RemoveDuplicatesFromSortedList;

public class RemoveDuplicates {
    public static void main(String[] args) {

        ListNode head1 = createList(new int[]{1, 1, 2, 3, 3});
        System.out.println("INPUT : ");
        printList(head1);
        deleteDuplicates(head1);
        System.out.println("OUTPUT:");
        printList(head1);
        System.out.println("-----");


        ListNode head2 = createList(new int[]{1, 1, 1, 1});
        System.out.println("INPUT : ");
        printList(head2);
        deleteDuplicates(head2);
        System.out.println("OUTPUT:");
        printList(head2);
        System.out.println("-----");


        ListNode head3 = createList(new int[]{1, 2, 3, 4});
        System.out.println("INPUT : ");
        printList(head3);
        deleteDuplicates(head3);
        System.out.println("OUTPUT:");
        printList(head3);
        System.out.println("-----");


        ListNode head4 = null;
        System.out.println("INPUT : ");
        printList(head4);
        deleteDuplicates(head4);
        System.out.println("OUTPUT:");
        printList(head4);
        System.out.println("-----");
    }


    public static ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }


    public static ListNode createList(int[] arr) {
        if (arr.length == 0) return null;

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("EMPTY LIST");
            return;
        }

        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }
}
