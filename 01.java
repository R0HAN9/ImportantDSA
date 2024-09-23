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


