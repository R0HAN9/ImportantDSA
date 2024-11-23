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
        if (head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode cur = prev.next;

        for (int i = 0; i < right - left; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = prev.next;
            prev.next = temp;
        }

        return dummy.next;        
    }
}



// 3. Swap Nodes in Pairs

class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy, cur = head;

        while (cur != null && cur.next != null) {
            ListNode npn = cur.next.next;
            ListNode second = cur.next;

            second.next = cur;
            cur.next = npn;
            prev.next = second;

            prev = cur;
            cur = npn;
        }

        return dummy.next;        
    }
}



// 4. Linked List Cycle

public class Solution {
    public boolean hasCycle(ListNode head) {
        
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}



// 5. Maximum Depth of Binary Tree

class Solution {
    public int maxDepth(TreeNode root) {
        
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }
}
