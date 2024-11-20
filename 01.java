// 1. Range Sum Query - Immutable

class NumArray {
    // Private array to store the input numbers
    private int[] nums;

    // Constructor to initialize the array
    public NumArray(int[] nums) {
        this.nums = nums; // Store the input array in the private nums array
    }
    
    // Method to calculate the sum of elements within a given range [left, right]
    public int sumRange(int left, int right) {
        int sum = 0; // Variable to store the sum of elements
        // Loop through the array from index 'left' to 'right' (inclusive)
        for (int i = left; i <= right; i++) {
            sum += nums[i]; // Add each element in the range to the sum
        }
        return sum; // Return the computed sum
    }
}



// 2. Contiguous Array

class Solution {
    public int findMaxLength(int[] nums) {
        int n = nums.length; // Length of the input array
        Map<Integer, Integer> mp = new HashMap<>(); // HashMap to store the sum and its first occurrence index
        int sum = 0; // Variable to keep track of the cumulative sum
        int subArrayLength = 0; // Variable to store the maximum length of the subarray found

        // Iterate through the array
        for (int i = 0; i < n; i++) {
            // Convert 0 to -1 for easier handling of the equal count condition
            sum += nums[i] == 0 ? -1 : 1;

            // If the cumulative sum is 0, it means the subarray from the start to the current index is valid
            if (sum == 0) {
                subArrayLength = i + 1; // Update the subarray length to the current index + 1
            }
            // If the sum has been seen before, a subarray with equal 0s and 1s exists between the two occurrences
            else if (mp.containsKey(sum)) {
                // Update the maximum subarray length found so far
                subArrayLength = Math.max(subArrayLength, i - mp.get(sum));
            }
            // If the sum is not in the map, store its first occurrence index
            else {
                mp.put(sum, i);
            }
        }
        return subArrayLength; // Return the maximum subarray length
    }
}



// 3. Subarray Sum Equals K

class Solution {
    public int subarraySum(int[] nums, int k) {
        
        int sum = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int rem = sum - k;

            if (map.containsKey(rem)) {
                count += map.get(rem);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}



// 04. Two Sum

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> pairIdx = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (pairIdx.containsKey(target - num)) {
                return new int[] {i, pairIdx.get(target - num)};
            }
            pairIdx.put(num, i);
        }
        return new int[] {};
    }
}



// 05. Valid Parentheses

class Solution {
    public boolean isValid(String s) {
        
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> mapping = new HashMap<>();

        mapping.put(')', '(');
        mapping.put('}', '{');
        mapping.put(']', '[');

        for (char c : s.toCharArray()) {
            if (mapping.containsValue(c)) {
                stack.push(c);

            }else if (mapping.containsKey(c)) {
                if (stack.isEmpty() || mapping.get(c) != stack.pop()) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
