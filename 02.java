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
        // Create a list to store the resulting triplets
        List<List<Integer>> res = new ArrayList<>();
        
        // Sort the array to make it easier to avoid duplicates and perform the two-pointer approach
        Arrays.sort(nums);

        // Iterate through the array to find potential triplets
        for (int i = 0; i < nums.length; i++) {
            // Skip duplicate values for the first element of the triplet
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            
            // Initialize two pointers: j (left) and k (right) for the remaining part of the array
            int j = i + 1;
            int k = nums.length - 1;

            // Use the two-pointer approach to find pairs that sum up to the negative of nums[i]
            while (j < k) {
                // Calculate the sum of the triplet: nums[i], nums[j], nums[k]
                int total = nums[i] + nums[j] + nums[k];

                // If the sum is greater than zero, move the right pointer leftward to decrease the sum
                if (total > 0) {
                    k--;
                } 
                // If the sum is less than zero, move the left pointer rightward to increase the sum
                else if (total < 0) {
                    j++;
                } 
                // If the sum is exactly zero, add the triplet to the result
                else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;  // Move the left pointer rightward to continue searching for other pairs

                    // Skip over duplicate values for the second element (nums[j])
                    while (nums[j] == nums[j-1] && j < k) {
                        j++;
                    }
                }
            }
        }
        
        // Return the list of triplets found
        return res;        
    }
}



// 3. Container With Most Water

class Solution {
    public int maxArea(int[] height) {
    // Initialize variable to store the maximum area found so far
        int maxArea = 0;
    
    // Set the left and right pointers, which initially point to the first and last elements of the array
        int left = 0;
        int right = height.length - 1;

    // Iterate while left pointer is to the left of right pointer
        while (left < right) {
        // Calculate the area between the two pointers (width * height)
        // Width is the distance between the two pointers: (right - left)
        // Height is the minimum of the heights at the two pointers
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));

        // Move the pointer that points to the shorter height, 
        // because the area is limited by the shorter height
            if (height[left] < height[right]) {
                left++; // Move the left pointer to the right
            } else {
                right--; // Move the right pointer to the left
        }
    }

    // Return the maximum area found
    return maxArea;
}




// 4. Merge Two Sorted Lists

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Base case: If one of the lists is null, return the other list
        // If both lists are null, return null by default
        if (list1 == null || list2 == null) {
            return (list1 != null) ? list1 : list2;
        }

        // Make sure list1 is always the smaller list (or equal)
        // Swap the lists if list2's value is smaller
        if (list1.val > list2.val) {
            ListNode temp = list1;
            list1 = list2;
            list2 = temp;
        }

        // Recur for the next nodes of both lists
        // Since list1 is guaranteed to be smaller or equal, we link it to the result of the next merge
        list1.next = mergeTwoLists(list1.next, list2);

        // Return the merged list starting from list1
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


