// 1. Range Sum Query - Immutable

class NumArray {
    private int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
    }
    
    public int sumRange(int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        return sum;
    }
}



// 2. Contiguous Array

class Solution {
    public int findMaxLength(int[] nums) {
        
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();
        int sum = 0;
        int subArrayLength = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i] == 0 ? -1 : 1;

            if (sum == 0) {
                subArrayLength = i + 1;
            }
            else if (mp.containsKey(sum)) {
                subArrayLength = Math.max(subArrayLength, i - mp.get(sum));
            }
            else {
                mp.put(sum, i);
            }
        }
        return subArrayLength;
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
