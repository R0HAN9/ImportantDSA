// 1. Reverse Linked List

class Solution {
    public ListNode reverseList(ListNode head) {
        // Base case: If the list is empty (head == null) or has only one node (head.next == null),
        // return the head as it is already reversed.
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively reverse the rest of the list starting from the next node.
        ListNode newHead = reverseList(head.next);

        // Reverse the current node's next pointer:
        // Point the next node's "next" to the current node.
        head.next.next = head;

        // Disconnect the current node from the rest of the list by setting its next to null.
        head.next = null;

        // Return the new head of the reversed list.
        return newHead;
    }
}




// 2. Reverse Linked List II

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // If the list is empty or no reversal is needed (left == right), return the original list.
        if (head == null || left == right) {
            return head;
        }

        // Create a dummy node to handle edge cases (e.g., reversing starts at the head).
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Initialize 'prev' to the node before the reversal starts.
        ListNode prev = dummy;

        // Move 'prev' to the node just before the 'left' position.
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // 'cur' points to the first node in the sublist to be reversed.
        ListNode cur = prev.next;

        // Perform the reversal between 'left' and 'right' positions.
        for (int i = 0; i < right - left; i++) {
            // Temporarily store the next node to be reversed.
            ListNode temp = cur.next;

            // Adjust pointers to skip 'temp' in the original order.
            cur.next = temp.next;

            // Insert 'temp' at the beginning of the reversed section.
            temp.next = prev.next;
            prev.next = temp;
        }

        // Return the head of the modified list (dummy.next).
        return dummy.next;
    }
}




// 3. Swap Nodes in Pairs

class Solution {
    public ListNode swapPairs(ListNode head) {
        // Create a dummy node to handle edge cases and simplify the logic.
        // The dummy node points to the head of the list.
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy; // 'prev' initially points to the dummy node.
        ListNode cur = head;   // 'cur' initially points to the head of the list.

        // Traverse the list while there are at least two nodes to swap.
        while (cur != null && cur.next != null) {
            // Store the node after the pair (node to process after swapping).
            ListNode npn = cur.next.next;

            // Identify the second node of the pair.
            ListNode second = cur.next;

            // Reverse the pair by adjusting pointers.
            second.next = cur; // The second node now points to the first node.
            cur.next = npn;    // The first node now points to the node after the pair.
            prev.next = second; // The previous node now points to the new first node of the pair.

            // Move the 'prev' and 'cur' pointers forward for the next pair.
            prev = cur; // 'prev' moves to the current node, which is now the second node of the swapped pair.
            cur = npn;  // 'cur' moves to the next pair (or null if at the end of the list).
        }

        // Return the new head of the list (dummy.next).
        return dummy.next;
    }
}




// 4. Linked List Cycle

public class Solution {
    public boolean hasCycle(ListNode head) {
        // Use two pointers: 'fast' and 'slow', both starting at the head of the list.
        ListNode fast = head;
        ListNode slow = head;

        // Traverse the list while the fast pointer and its next node are not null.
        while (fast != null && fast.next != null) {
            // Move the fast pointer two steps ahead.
            fast = fast.next.next;

            // Move the slow pointer one step ahead.
            slow = slow.next;

            // Check if the fast and slow pointers meet, indicating a cycle.
            if (fast == slow) {
                return true; // A cycle is detected.
            }
        }

        // If the fast pointer reaches the end of the list, there is no cycle.
        return false;
    }
}




// 5. Maximum Depth of Binary Tree

class Solution {
    public int maxDepth(TreeNode root) {
        // Base case: If the tree is empty (root is null), the depth is 0.
        if (root == null) return 0;

        // Recursively find the maximum depth of the left subtree.
        int left = maxDepth(root.left);

        // Recursively find the maximum depth of the right subtree.
        int right = maxDepth(root.right);

        // The depth of the current node is the greater of the left or right subtree depths plus 1 (for the current node).
        return Math.max(left, right) + 1;
    }
}

