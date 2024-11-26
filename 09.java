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
