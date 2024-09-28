// 1. Next Greater Element I


class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        Stack<Integer> s1 = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = nums2.length-1; i >= 0; i--) {
            int num = nums2[i];

            while (!s1.isEmpty() && s1.peek() <= num) {
                s1.pop();
            }

            if(s1.isEmpty()) map.put(num, -1);
            else map.put(num, s1.peek());

            s1.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.containsKey(nums1[i]) ? map.get(nums1[i]) : -1;
        }

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
