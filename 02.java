// 1. Two Sum II - Input Array Is Sorted

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // Initialize two pointers: left at the beginning and right at the end
        int left = 0;
        int right = numbers.length - 1;

        // Use a while loop to iterate through the array
        while (left < right) {
            // Calculate the sum of the two numbers at the left and right pointers
            int total = numbers[left] + numbers[right];

            // If the sum matches the target, return the 1-based indices
            if (total == target) {
                return new int[] {left + 1, right + 1};  // +1 to convert to 1-based index
            }
            // If the sum is greater than the target, move the right pointer leftward
            else if (total > target) {
                right--;
            }
            // If the sum is less than the target, move the left pointer rightward
            else {
                left++;
            }
        }
        
        // If no solution is found, return an array with -1, -1 (indicating no solution)
        return new int[] {-1, -1};
    }
}




// 2. 3Sum

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int total = nums[i] + nums[j] + nums[k];

                if (total > 0) {
                    k--;
                } else if (total < 0) {
                    j++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;

                    while (nums[j] == nums[j-1] && j < k) {
                        j++;
                    }
                }
            }
        }
        return res;        
    }
}


// 3. Container With Most Water

class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;        
    }
}



// 4. Merge Two Sorted Lists

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return (list1 != null) ? list1 : list2;
        }

        if (list1.val > list2.val) {
            ListNode temp = list1;
            list1 = list2;
            list2 = temp;
        }

        list1.next = mergeTwoLists(list1.next, list2);
        return list1;        
    }
}



// 5. Best Time to Buy and Sell Stock

class Solution {
    public int maxProfit(int[] prices) {
        int buyPrice = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (buyPrice > prices[i]) {
                buyPrice = prices[i];
            }

            profit = Math.max(profit, prices[i] - buyPrice);
        }

        return profit;        
    }
}


