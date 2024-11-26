// 1. Search in Rotated Sorted Array 

class Solution {
    public int search(int[] nums, int target) {
        int left = 0; // Initialize the left pointer
        int right = nums.length - 1; // Initialize the right pointer

        // Perform binary search
        while (left <= right) {
            // Calculate the middle index
            int mid = (left + right) / 2;

            // Check if the middle element is the target
            if (nums[mid] == target) {
                return mid; // Target found, return its index
            }
            
            // Determine which part of the array is sorted
            if (nums[mid] >= nums[left]) { // Left part is sorted
                // Check if the target lies within the sorted left part
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1; // Narrow down to the left part
                } else {
                    left = mid + 1; // Narrow down to the right part
                }
            } else { // Right part is sorted
                // Check if the target lies within the sorted right part
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1; // Narrow down to the right part
                } else {
                    right = mid - 1; // Narrow down to the left part
                }
            }
        }

        // If we exit the loop, the target is not in the array
        return -1;        
    }
}




// 2. Find Minimum in Rotated Sorted Array

class Solution {
    public int findMin(int[] nums) {
        
        int len = nums.length;
        int startIdx = 0;
        int endIdx = len - 1;
        int ans = Integer.MAX_VALUE;

        while (startIdx <= endIdx) {
            int mid = startIdx + (endIdx - startIdx) / 2;

            if (nums[mid] > nums[endIdx]) {
                startIdx = mid + 1;
            }
            else if (nums[mid] <= nums[endIdx]) {
                ans = Math.min(ans, nums[mid]);
                endIdx = mid - 1;
            }
        }
        return ans;
    }
}



// 3. Search a 2D Matrix II

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int n = matrix.length;
        int m = matrix[0].length;
        int row = 0, col = m - 1;

        while (row < n && col >= 0) {
            if (matrix[row][col] == target) return true;

            else if (matrix[row][col] < target) row++;
            else col--;
        }
        return false;
    }
}



// 4. Middle of the Linked List

class Solution {
    public ListNode middleNode(ListNode head) {
        
        if (head == null || head.next == null) 
            return head;

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null) {
            fast = fast.next;

            if (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow;
    }
}



// 5. Convert Sorted Array to Binary Search Tree

class Solution {

    private TreeNode helper(int[] nums, int startIdx, int endIdx) {

        if (startIdx > endIdx) return null;
        int mid = startIdx + (endIdx - startIdx) / 2;
        TreeNode node = new TreeNode(nums[mid]);

        node.left = helper(nums, startIdx, mid - 1);
        node.right = helper(nums, mid + 1, endIdx);
        return node;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        int n = nums.length;
        if (n == 0) return null;
        return helper(nums, 0, n - 1);
    }
}
