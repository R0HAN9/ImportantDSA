// 1. Climbing Stairs 

class Solution {
    public int climbStairs(int n) {
        // Base cases: if there are 1, 2, or 3 steps, the number of ways to climb
        // is equal to the number of steps.
        if (n <= 3) return n;

        // Initialize variables to represent the number of ways to climb to the
        // last two steps (prev1 and prev2). For n = 3, the values are:
        // prev1 (ways to climb to step 3) = 3
        // prev2 (ways to climb to step 2) = 2
        int prev1 = 3;
        int prev2 = 2;
        int cur = 0; // This will store the current number of ways to climb.

        // Iterate from step 4 up to step n. Calculate the number of ways to climb
        // to the current step based on the sum of the previous two steps.
        for (int i = 3; i < n; i++) {
            cur = prev1 + prev2; // Current step = sum of ways to climb to the previous two steps.
            prev2 = prev1;       // Update prev2 to the value of prev1 (move one step ahead).
            prev1 = cur;         // Update prev1 to the current value (move one step ahead).
        }

        // Return the number of ways to climb to the nth step.
        return cur;        
    }
}


// 2. Coin Change 

class Solution {
    public int coinChange(int[] coins, int amount) {
        
        // Initialize an array to store the minimum number of coins required for each amount.
        // The array size is (amount + 1) since we need to compute values from 0 to 'amount'.
        int[] minCoins = new int[amount + 1];

        // Fill the array with a large value (amount + 1) to represent that the amount is initially unreachable.
        // This ensures any valid solution will replace this value with a smaller count of coins.
        Arrays.fill(minCoins, amount + 1);

        // Base case: The minimum number of coins needed to make amount 0 is 0.
        minCoins[0] = 0;

        // Iterate over each amount from 1 to the target amount.
        for (int i = 1; i <= amount; i++) {
            // Check each coin to see if it can be used to make the current amount.
            for (int j = 0; j < coins.length; j++) {
                // If the current amount is greater than or equal to the coin value,
                // we consider using this coin.
                if (i - coins[j] >= 0) {
                    // Update the minimum coins needed for the current amount.
                    // Take the minimum of the current value and the result of using this coin
                    // (1 + minCoins[i - coins[j]]).
                    minCoins[i] = Math.min(minCoins[i], 1 + minCoins[i - coins[j]]);
                }
            }
        }

        // If the value at minCoins[amount] is still the initial large value, 
        // it means the amount cannot be formed using the given coins. Return -1.
        return minCoins[amount] != amount + 1 ? minCoins[amount] : -1;
    }
}


// 3. Longest Common Subsequence (LCS) 

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        
        // Create a 1D array `dp` to store the length of the LCS for each character in `text1`.
        // `dp[i]` represents the length of the LCS ending at the ith character of `text1`.
        int[] dp = new int[text1.length()];

        // Variable to keep track of the longest LCS found so far.
        int longest = 0;

        // Iterate through each character in `text2`.
        for (char c : text2.toCharArray()) {
            // This variable stores the maximum LCS length seen so far for the current character of `text2`.
            int currentLen = 0;

            // Iterate through each character in `text1`.
            for (int i = 0; i < dp.length; i++) {
                // Update `currentLen` if the LCS length for the previous character of `text1` is greater.
                if (currentLen < dp[i]) {
                    currentLen = dp[i];
                }
                // If the characters of `text1` and `text2` match, update the LCS length for the ith position.
                else if (c == text1.charAt(i)) {
                    dp[i] = currentLen + 1; // Extend the LCS by 1.
                    // Update the longest LCS found so far.
                    longest = Math.max(longest, dp[i]);
                }
            }
        }

        // Return the length of the longest common subsequence.
        return longest;
    }
}


// 4. Longest Increasing Subsequence (LIS) 

class Solution {
    public int lengthOfLIS(int[] nums) {
        // Initialize a list `res` to store the elements of the increasing subsequence.
        List<Integer> res = new ArrayList<>();

        // Iterate through each number in the input array `nums`.
        for (int n : nums) {
            // If `res` is empty or the current number `n` is greater than the last number in `res`,
            // append `n` to `res` to extend the subsequence.
            if (res.isEmpty() || res.get(res.size() - 1) < n) {
                res.add(n);
            } else {
                // If `n` is smaller than or equal to the last element of `res`,
                // we need to replace the first element that is greater than or equal to `n`.
                // This is done using binary search to find the index for replacement.
                int idx = binarySearch(res, n);
                res.set(idx, n);  // Replace the element at index `idx` with `n`.
            }
        }

        // The size of `res` will give us the length of the longest increasing subsequence.
        return res.size();
    }

    // Binary search to find the position where `target` should be inserted in `arr`
    private int binarySearch(List<Integer> arr, int target) {
        int left = 0;
        int right = arr.size() - 1;

        // Perform binary search on `arr` to find the smallest element greater than or equal to `target`.
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr.get(mid) == target) {
                return mid;  // Return the index if the target is found.
            } else if (arr.get(mid) > target) {
                right = mid - 1;  // If target is smaller, narrow the search to the left half.
            } else {
                left = mid + 1;  // If target is larger, narrow the search to the right half.
            }
        }

        // When the loop ends, `left` will be the insertion point for the target element.
        return left;
    }    
}


// 5. Partition Equal Subset Sum 

class Solution {
    public boolean canPartition(int[] nums) {
        
        // Step 1: Calculate the total sum of all elements in the array.
        int sum = 0;
        for (int elements : nums) {
            sum += elements;
        }

        // Step 2: If the total sum is odd, it's impossible to partition it into two equal subsets.
        if (sum % 2 != 0) return false;
        
        // Step 3: We now need to check if there is a subset whose sum is equal to half of the total sum.
        sum = sum / 2;

        // Step 4: Create a DP array to track which subset sums are achievable.
        // dp[i] will be true if we can make a sum of `i` using the elements in `nums`.
        boolean dp[] = new boolean[sum + 1];
        dp[0] = true; // We can always make a sum of 0 (empty subset).

        // Step 5: For each element in `nums`, update the DP array.
        for (int num : nums) {
            // Step 6: Update the DP array backwards (from sum to num) to avoid re-using the same element in the same iteration.
            for (int i = sum; i > 0; i--) {
                // If the current sum `i` can be achieved by including the current number `num`.
                if (i >= num) {
                    dp[i] = dp[i] || dp[i - num]; // Update dp[i] to be true if dp[i - num] is true.
                }
            }
        }

        // Step 7: If dp[sum] is true, then we can partition the array into two subsets with equal sum.
        return dp[sum];
    }
}



// 6. Kth Smallest Element in a BST

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        // Initialize an array `count` to track how many nodes we've visited.
        // `kthSmallest` will store the value of the kth smallest node.
        int[] count = new int[]{0};
        int[] kthSmallest = new int[] {Integer.MIN_VALUE};

        // Perform the in-order traversal to find the kth smallest element.
        inOrderHelper(root, count, k, kthSmallest);

        // Return the kth smallest element found.
        return kthSmallest[0];
    }

    // Helper function for performing the in-order traversal.
    public void inOrderHelper(TreeNode root, int[] count, int k, int[] kthSmallest) {
        // Base case: if the node is null or we've already found the kth smallest.
        if (root == null || count[0] >= k) return;

        // Traverse the left subtree.
        inOrderHelper(root.left, count, k, kthSmallest);

        // Increment the count after visiting a node.
        count[0]++;

        // Check if we have visited the kth smallest node.
        if (count[0] == k) {
            // If so, store the current node's value in `kthSmallest`.
            kthSmallest[0] = root.val;
            return;  // Return early as we've found the answer.
        }

        // Traverse the right subtree.
        inOrderHelper(root.right, count, k, kthSmallest);
    }
}


// 7. Merge Intervals 

class Solution {
    public int[][] merge(int[][] intervals) {
        
        // Step 1: Sort intervals based on the start value of each interval
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Initialize a list to hold the merged intervals
        List<int[]> merged = new ArrayList<>();

        // Start with the first interval as the previous interval
        int[] prev = intervals[0];

        // Step 3: Iterate over the remaining intervals
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            
            // Step 4: If the current interval overlaps with the previous one
            if (interval[0] <= prev[1]) {
                // Merge the intervals by updating the end of the previous interval
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                // No overlap, so add the previous interval to the result list
                merged.add(prev);
                // Move to the next interval
                prev = interval;
            }
        }

        // Step 5: Add the last interval to the merged list
        merged.add(prev);

        // Step 6: Convert the list of merged intervals to a 2D array and return
        return merged.toArray(new int[merged.size()][]);
    }
}



// BurstBallons

public class BurstBallons {
    public int maxCoins(int[] nums) {

        int n = nums.length;

        // Create an array with two additional elements to handle boundary cases.
        int[] balloons = new int[n + 2];

        // Initialize the boundaries of the balloons array to 1.
        balloons[0] = 1;
        balloons[n + 1] = 1;

        // Copy the input balloons into the new array, adjusting indices.
        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }

        // dp[i][j] will store the maximum coins that can be obtained
        // by bursting all the balloons between index i and j.
        int[][] dp = new int[n + 2][n + 2];

        // Iterate over different lengths of the subarrays of balloons to be considered.
        for (int length = 2; length <= n + 1; length++) {
            // For each possible starting point of the subarray.
            for (int start = 0; start <= n + 1 - length; start++) {

                // The ending point of the subarray.
                int end = start + length;

                // Try bursting each balloon between start and end.
                for (int k = start + 1; k < end; k++) {

                    // Calculate the coins obtained by bursting the k-th balloon,
                    // which is the product of the adjacent balloons at start, k, and end.
                    int coins = balloons[start] * balloons[k] * balloons[end];

                    // Add the maximum coins obtained from the subarrays to the left and right of k.
                    int totalCoins = coins + dp[start][k] + dp[k][end];

                    // Update dp[start][end] with the maximum value.
                    dp[start][end] = Math.max(dp[start][end], totalCoins);
                }
            }
        }

        // Return the maximum coins that can be obtained for the entire range (from 0 to n + 1).
        return dp[0][n + 1];
    }
}


