// 1. Search in Rotated Sorted Array 

class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[left]) {
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

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

// 4. Middle of the Linked List

// 5. Convert Sorted Array to Binary Search Tree
