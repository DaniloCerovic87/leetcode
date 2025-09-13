package problem06_MergeSorted;

public class MergeSorted {

    public static void main(String[] args) {
        ListNode list1a = buildList(new int[]{1, 2, 4});
        ListNode list2a = buildList(new int[]{1, 3, 4});
        ListNode merged1 = mergeTwoLists(list1a, list2a);
        System.out.println("Example 1: " + listToString(merged1));

        ListNode list1b = buildList(new int[]{});
        ListNode list2b = buildList(new int[]{});
        ListNode merged2 = mergeTwoLists(list1b, list2b);
        System.out.println("Example 2: " + listToString(merged2));

        ListNode list1c = buildList(new int[]{});
        ListNode list2c = buildList(new int[]{0});
        ListNode merged3 = mergeTwoLists(list1c, list2c);
        System.out.println("Example 3: " + listToString(merged3));
    }

    private static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    private static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        while (head != null) {
            sb.append(head.val);
            head = head.next;
            if (head != null) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    private static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        current.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }

}