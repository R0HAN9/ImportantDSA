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
        int sum = 0; // Variable to keep track of the cumulative sum
        int count = 0; // Variable to count the number of subarrays with sum equal to k
        HashMap<Integer, Integer> map = new HashMap<>(); // Map to store cumulative sum frequencies
        map.put(0, 1); // Initialize the map with sum 0 occurring once (important for subarrays starting from index 0)

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // Update the cumulative sum

            // Calculate the remaining sum needed to reach k
            int rem = sum - k;

            // If the remaining sum exists in the map, it means there are subarrays ending at the current index
            // that sum to k
            if (map.containsKey(rem)) {
                count += map.get(rem); // Add the frequency of the remaining sum to the count
            }

            // Update the frequency of the current cumulative sum in the map
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count; // Return the total count of subarrays with sum equal to k
    }
}



// 04. Two Sum

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // HashMap to store the elements and their indices for quick lookup
        Map<Integer, Integer> pairIdx = new HashMap<>();

        // Loop through the array to find the required pair
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i]; // Current number in the array

            // Check if the complement (target - num) exists in the map
            if (pairIdx.containsKey(target - num)) {
                // If found, return the current index and the index of the complement
                return new int[] {i, pairIdx.get(target - num)};
            }

            // Store the current number and its index in the map for future lookup
            pairIdx.put(num, i);
        }

        // If no solution is found (though the problem guarantees one), return an empty array
        return new int[] {};
    }
}



// 05. Valid Parentheses

class Solution {
    public boolean isValid(String s) {
        // Stack to store opening brackets for matching with closing brackets
        Stack<Character> stack = new Stack<>();

        // HashMap to define the mapping of closing brackets to their corresponding opening brackets
        Map<Character, Character> mapping = new HashMap<>();
        mapping.put(')', '(');
        mapping.put('}', '{');
        mapping.put(']', '[');

        // Iterate through each character in the string
        for (char c : s.toCharArray()) {
            // If the character is an opening bracket, push it onto the stack
            if (mapping.containsValue(c)) {
                stack.push(c);
            }
            // If the character is a closing bracket
            else if (mapping.containsKey(c)) {
                // Check if the stack is empty or the top of the stack does not match the current closing bracket
                if (stack.isEmpty() || mapping.get(c) != stack.pop()) {
                    return false; // Invalid string if the brackets do not match
                }
            }
        }
        // If the stack is empty at the end, all brackets were matched correctly
        return stack.isEmpty();
    }
}
