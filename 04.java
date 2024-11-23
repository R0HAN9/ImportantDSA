// 1. Linked List Cycle

public class Solution {
    public boolean hasCycle(ListNode head) {
        // Initialize two pointers: fast and slow, both starting at the head of the list.
        ListNode fast = head;
        ListNode slow = head;

        // Traverse the list until the fast pointer or its next node becomes null
        // (indicating the end of the list) to avoid null pointer exceptions.
        while (fast != null && fast.next != null) {
            // Move the fast pointer two steps ahead.
            fast = fast.next.next;
            // Move the slow pointer one step ahead.
            slow = slow.next;

            // If the fast pointer meets the slow pointer, a cycle is detected.
            if (fast == slow) {
                return true; // Return true as there is a cycle.
            }
        }

        // If the loop completes without the fast and slow pointers meeting,
        // it means there is no cycle in the linked list.
        return false;
    }
}




// 2. Happy Number

class Solution {
    public boolean isHappy(int n) {
        // Use two pointers (slow and fast) to detect a cycle in the sequence of numbers.
        // Start slow with the next number and fast with the next-next number.
        int slow = getNextNumber(n);
        int fast = getNextNumber(getNextNumber(n));

        // Continue until slow and fast pointers meet, which would indicate a cycle.
        while (slow != fast) {
            // If the fast pointer reaches 1, the number is happy.
            if (fast == 1) return true;

            // Move slow pointer one step ahead in the sequence.
            slow = getNextNumber(slow);
            // Move fast pointer two steps ahead in the sequence.
            fast = getNextNumber(getNextNumber(fast));
        }

        // If the loop ends and slow equals 1, the number is happy.
        return slow == 1;
    }

    // Helper function to compute the sum of the squares of the digits of a number.
    private int getNextNumber(int n) {
        int output = 0;
        
        // Extract each digit of the number.
        while (n > 0) {
            int digit = n % 10; // Get the last digit.
            output += digit * digit; // Add the square of the digit to the output.
            n = n / 10; // Remove the last digit from the number.
        }
        
        return output; // Return the computed sum of squares.
    }
}




// 3. Find the Duplicate Number 

class Solution {
    public int findDuplicate(int[] nums) {
        // Initialize two pointers, both starting at the first element of the array.
        int slow = nums[0];
        int fast = nums[0];

        // Phase 1: Use the Floyd's Tortoise and Hare algorithm to find the intersection point.
        while (true) {
            // Move the slow pointer one step ahead.
            slow = nums[slow];
            // Move the fast pointer two steps ahead.
            fast = nums[nums[fast]];

            // Break the loop when slow and fast pointers meet.
            if (slow == fast) {
                break;
            }
        }

        // Phase 2: Find the entrance to the cycle, which is the duplicate number.
        int slow2 = nums[0]; // Start a new pointer from the beginning.

        // Move both pointers one step at a time until they meet.
        while (slow != slow2) {
            slow = nums[slow];
            slow2 = nums[slow2];
        }

        // The meeting point is the duplicate number.
        return slow;
    }
}




// 4. Valid Anagram

class Solution {
    public boolean isAnagram(String s, String t) {
        // If the strings have different lengths, they can't be anagrams.
        if (s.length() != t.length()) {
            return false;
        }

        // Create two hash maps to count the frequency of each character in both strings.
        HashMap<Character, Integer> sCount = new HashMap<>();
        HashMap<Character, Integer> tCount = new HashMap<>();

        // Iterate through each character of both strings simultaneously.
        for (int i = 0; i < s.length(); i++) {
            // Update the count of the current character from string 's' in sCount.
            sCount.put(s.charAt(i), 1 + sCount.getOrDefault(s.charAt(i), 0));
            // Update the count of the current character from string 't' in tCount.
            tCount.put(t.charAt(i), 1 + tCount.getOrDefault(t.charAt(i), 0));
        }

        // Check if the two hash maps are equal. If they are, 's' and 't' are anagrams.
        return sCount.equals(tCount);        
    }
}




// 5. Binary Search

class Solution {
    public int search(int[] nums, int target) {
        int l=0;
        int h=nums.length-1;
        while(l<=h){
            int mid = (l+h)/2;
            if(nums[mid]==target){
                return mid;
            }
            if(nums[mid]>target){
                h = mid-1;
            }else{
                l = mid+1;
            }
        }
        return -1;
    }
}
