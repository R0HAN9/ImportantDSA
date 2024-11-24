// 1. Next Greater Element I

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        // Stack to keep track of the elements in nums2 in decreasing order
        Stack<Integer> s1 = new Stack<>();
        
        // Map to store the next greater element for each number in nums2
        HashMap<Integer, Integer> map = new HashMap<>();

        // Traverse nums2 in reverse order to find the next greater element for each number
        for (int i = nums2.length - 1; i >= 0; i--) {
            int num = nums2[i];

            // Pop all elements from the stack which are less than or equal to the current number
            while (!s1.isEmpty() && s1.peek() <= num) {
                s1.pop();
            }

            // If the stack is empty, no greater element exists, so we map it to -1
            if (s1.isEmpty()) {
                map.put(num, -1);
            } else {
                // If the stack is not empty, the top element of the stack is the next greater element
                map.put(num, s1.peek());
            }

            // Push the current number onto the stack to be used for future comparisons
            s1.push(num);
        }

        // Now, for each element in nums1, find its next greater element from the map
        for (int i = 0; i < nums1.length; i++) {
            // If the element exists in the map, set it to the next greater element; otherwise, set it to -1
            nums1[i] = map.containsKey(nums1[i]) ? map.get(nums1[i]) : -1;
        }

        // Return the result array with the next greater elements
        return nums1;
    }
}




// 2. Daily Temperatures

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // Stack to store indices of temperatures in increasing order
        Stack<Integer> stack = new Stack<>();
        
        // Result array to store the number of days to wait for a warmer temperature
        int[] result = new int[temperatures.length];

        // Iterate through the temperatures array
        for (int i = 0; i < temperatures.length; i++) {
            // Check if the current temperature is higher than the one at the top of the stack
            // If it is, we pop from the stack and update the result array
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();  // Get the index of the previous temperature
                result[idx] = i - idx;  // Calculate the number of days to wait for a warmer temperature
            }
            // Push the current index onto the stack
            stack.push(i);
        }

        // Return the result array after processing all temperatures
        return result;        
    }
}




// 3. Largest Rectangle in Histogram

class Solution {
    public int largestRectangleArea(int[] heights) {
        
        int n = heights.length;  // The number of bars in the histogram
        int maxArea = 0;  // To keep track of the maximum area found
        Stack<Integer> st = new Stack<>();  // Stack to store indices of the bars

        // Iterate over all bars (including one extra iteration to handle remaining bars in the stack)
        for (int i = 0; i <= n; i++) {
            // If we've reached the end of the histogram, set current height to 0 (to pop remaining bars)
            int currHeight = i == n ? 0 : heights[i];

            // While stack is not empty and the current height is less than the height at the index at the top of the stack
            while (!st.isEmpty() && currHeight < heights[st.peek()]) {
                
                // Pop the top index from the stack and calculate the area with the popped height
                int top = st.pop();
                
                // Calculate the width of the rectangle: 
                // If the stack is empty, the width is from index 0 to i (i.e., all bars from 0 to i-1).
                // Otherwise, the width is from the next index in the stack (after popping) to the current index i.
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                
                // Calculate the area using the height of the popped bar and the width
                int area = heights[top] * width;
                
                // Update the maximum area found
                maxArea = Math.max(area, maxArea);
            }
            // Push the current index onto the stack
            st.push(i);
        }
        
        // Return the maximum area found
        return maxArea;
    }
}




// 4. Single Number

class Solution {
    public int singleNumber(int[] nums) {
        
        // n stores the length of the input array
        int n = nums.length;
        
        // c will hold the result (the number that appears only once)
        int c = 0;

        // Loop through each element in the array
        for (int i = 0; i < n; i++) {
            // Use the XOR operator to cancel out numbers that appear twice
            c = c ^ nums[i];
        }
        
        // After the loop, c will contain the single number (the one that appears only once)
        return c;
    }
}




// 5. Reverse Linked List  
    
class Solution {
    public ListNode reverseList(ListNode head) {
        
        // node will be used to store the reversed linked list (initially null)
        ListNode node = null;

        // Traverse through the linked list until we reach the end (head == null)
        while (head != null) {
            // Temporarily store the next node
            ListNode temp = head.next;
            
            // Reverse the current node's link to point to the previous node (node)
            head.next = node;
            
            // Move the node pointer to the current node (this will be the new "previous" node)
            node = head;
            
            // Move to the next node in the original list
            head = temp;
        }

        // After the loop, node points to the new head of the reversed list
        return node;
    }
}

