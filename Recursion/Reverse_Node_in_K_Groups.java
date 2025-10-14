class Reverse_Node_in_K_Groups {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;

        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail == null) return head;
            tail = tail.next;
        }

        ListNode newHead = reverse(head, tail);
        head.next = reverseKGroup(tail, k);
        return newHead;
    }

    private ListNode reverse(ListNode cur, ListNode end) {
        ListNode prev = null;
        while (cur != end) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Reverse_Node_in_K_Groups solution = new Reverse_Node_in_K_Groups();
        
        System.out.println("Testing Reverse Nodes in K-Groups Algorithm:");
        System.out.println("===========================================");
        
        // Test case 1: Basic k=2 reversal
        System.out.println("Test Case 1: [1,2,3,4,5] with k=2");
        ListNode head1 = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        printList(head1);
        ListNode result1 = solution.reverseKGroup(head1, 2);
        System.out.print("After reversing in groups of 2: ");
        printList(result1);
        System.out.println("Expected: [2,1,4,3,5]\n");
        
        // Test case 2: k=3 reversal
        System.out.println("Test Case 2: [1,2,3,4,5] with k=3");
        ListNode head2 = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        printList(head2);
        ListNode result2 = solution.reverseKGroup(head2, 3);
        System.out.print("After reversing in groups of 3: ");
        printList(result2);
        System.out.println("Expected: [3,2,1,4,5]\n");
        
        // Test case 3: k=1 (no reversal)
        System.out.println("Test Case 3: [1,2,3,4] with k=1");
        ListNode head3 = createLinkedList(new int[]{1, 2, 3, 4});
        System.out.print("Original: ");
        printList(head3);
        ListNode result3 = solution.reverseKGroup(head3, 1);
        System.out.print("After reversing in groups of 1: ");
        printList(result3);
        System.out.println("Expected: [1,2,3,4]\n");
        
        // Test case 4: k equals list length
        System.out.println("Test Case 4: [1,2,3] with k=3");
        ListNode head4 = createLinkedList(new int[]{1, 2, 3});
        System.out.print("Original: ");
        printList(head4);
        ListNode result4 = solution.reverseKGroup(head4, 3);
        System.out.print("After reversing in groups of 3: ");
        printList(result4);
        System.out.println("Expected: [3,2,1]\n");
        
        // Test case 5: k greater than list length
        System.out.println("Test Case 5: [1,2] with k=3");
        ListNode head5 = createLinkedList(new int[]{1, 2});
        System.out.print("Original: ");
        printList(head5);
        ListNode result5 = solution.reverseKGroup(head5, 3);
        System.out.print("After reversing in groups of 3: ");
        printList(result5);
        System.out.println("Expected: [1,2] (no change)\n");
    }
    
    // Helper method to create a linked list from an array
    private static ListNode createLinkedList(int[] values) {
        if (values.length == 0) return null;
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        
        return head;
    }
    
    // Helper method to print the linked list
    private static void printList(ListNode head) {
        System.out.print("[");
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(",");
            }
            current = current.next;
        }
        System.out.println("]");
    }
}

// ListNode class definition
class ListNode {
    int val;
    ListNode next;
    
    ListNode() {}
    
    ListNode(int val) {
        this.val = val;
    }
    
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}