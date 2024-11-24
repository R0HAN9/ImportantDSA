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
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            stack.push(i);
        }

        return result;        
    }
}



// 3. Largest Rectangle in Histogram

class Solution {
    public int largestRectangleArea(int[] heights) {
        
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i <= n; i++) {
            int currHeight = i == n ? 0 : heights[i];

            while (!st.isEmpty() && currHeight < heights[st.peek()]) {
                
                int top = st.pop();
                int width = st.isEmpty() ? i : i-st.peek() - 1;
                int area = heights[top] * width;
                maxArea = Math.max(area, maxArea);
            }
            st.push(i);
        }
        return maxArea;
    }
}



// 4. Single Number

class Solution {
    public int singleNumber(int[] nums) {
        
        int n = nums.length;
        int c = 0;

        for (int i = 0; i < n; i++) {
            c = c ^ nums[i];
        }
        return c;
    }
}



// 5. Reverse Linked List  
    
    class Solution {
    public ListNode reverseList(ListNode head) {
        
        ListNode node = null;

        while (head != null) {
            ListNode temp = head.next;
            head.next = node;
            node = head;
            head = temp;
        }

        return node;
    }
}
