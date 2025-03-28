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
        // Get the length of the array
        int len = nums.length;

        // Define the start and end pointers
        int startIdx = 0;
        int endIdx = len - 1;

        // Initialize the answer with a maximum possible value
        int ans = Integer.MAX_VALUE;

        // Binary search to find the minimum value
        while (startIdx <= endIdx) {
            // Calculate the middle index
            int mid = startIdx + (endIdx - startIdx) / 2;

            // Check if the mid element is greater than the last element in the range
            // This indicates the minimum is in the right part of the array
            if (nums[mid] > nums[endIdx]) {
                startIdx = mid + 1; // Narrow the search to the right side
            } 
            // If the mid element is less than or equal to the last element
            // This indicates the minimum could be at mid or in the left part
            else if (nums[mid] <= nums[endIdx]) {
                ans = Math.min(ans, nums[mid]); // Update the minimum answer
                endIdx = mid - 1; // Narrow the search to the left side
            }
        }

        // Return the minimum value found
        return ans;
    }
}




// 3. Search a 2D Matrix II

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Get the dimensions of the matrix
        int n = matrix.length;     // Number of rows
        int m = matrix[0].length;  // Number of columns

        // Start from the top-right corner of the matrix
        int row = 0, col = m - 1;

        // Traverse the matrix while staying within its bounds
        while (row < n && col >= 0) {
            // If the target is found, return true
            if (matrix[row][col] == target) {
                return true;
            } 
            // If the current element is less than the target,
            // move down to the next row
            else if (matrix[row][col] < target) {
                row++;
            } 
            // If the current element is greater than the target,
            // move left to the previous column
            else {
                col--;
            }
        }

        // If the loop ends without finding the target, return false
        return false;
    }
}




// 4. Middle of the Linked List

class Solution {
    public ListNode middleNode(ListNode head) {
        // Base case: If the list is empty or has only one node, return the head
        if (head == null || head.next == null) 
            return head;

        // Initialize two pointers: 
        // 'slow' moves one step at a time, and 'fast' moves two steps at a time
        ListNode fast = head;
        ListNode slow = head;

        // Traverse the list until the 'fast' pointer reaches the end
        while (fast != null) {
            fast = fast.next; // Move 'fast' one step

            // Check if 'fast' can move one more step
            if (fast != null) {
                fast = fast.next; // Move 'fast' a second step
                slow = slow.next; // Move 'slow' one step
            }
        }

        // When 'fast' reaches the end, 'slow' will be at the middle node
        return slow;
    }
}




// 5. Convert Sorted Array to Binary Search Tree

class Solution {

    // Recursive helper function to build the BST from a sorted array
    private TreeNode helper(int[] nums, int startIdx, int endIdx) {
        // Base case: If the start index exceeds the end index, return null
        if (startIdx > endIdx) return null;

        // Calculate the middle index to select the root of the current subtree
        int mid = startIdx + (endIdx - startIdx) / 2;

        // Create a new TreeNode with the value at the middle index
        TreeNode node = new TreeNode(nums[mid]);

        // Recursively build the left subtree using the left part of the array
        node.left = helper(nums, startIdx, mid - 1);

        // Recursively build the right subtree using the right part of the array
        node.right = helper(nums, mid + 1, endIdx);

        // Return the constructed node
        return node;
    }

    // Main function to convert a sorted array to a height-balanced BST
    public TreeNode sortedArrayToBST(int[] nums) {
        // Edge case: If the array is empty, return null
        int n = nums.length;
        if (n == 0) return null;

        // Call the helper function with the full range of the array
        return helper(nums, 0, n - 1);
    }
}

