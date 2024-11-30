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
        List<Integer> res = new ArrayList<>();

        for (int n : nums) {
            if (res.isEmpty() || res.get(res.size() - 1) < n) {
                res.add(n);
            } else {
                int idx = binarySearch(res, n);
                res.set(idx, n);
            }
        }

        return res.size();        
    }

    private int binarySearch(List<Integer> arr, int target) {
        int left = 0;
        int right = arr.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr.get(mid) == target) {
                return mid;
            } else if (arr.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }    
}

// 5. Partition Equal Subset Sum 

class Solution {
    public boolean canPartition(int[] nums) {
        
        int sum = 0;
        for (int elements : nums) {
            sum += elements;
        }

        if (sum % 2 != 0) return false;
        sum = sum / 2;

        boolean dp[] = new boolean[sum + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int i = sum; i > 0; i--) {

                if (i >= num) {
                    dp[i] = dp[i] || dp[i - num];
                }
            }
        }
        return dp[sum];
    }
}


// 6. Kth Smallest Element in a BST

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        
        int[] count = new int[]{0};
        int[] kthSmallest = new int[] {Integer.MIN_VALUE};

        inOrderHelper(root, count, k, kthSmallest);
        return kthSmallest[0];

    }
    public void inOrderHelper(TreeNode root, int[] count, int k, int[] kthSmallest) {
        if (root == null || count[0] >= k) return;

        inOrderHelper(root.left, count, k, kthSmallest);
        count[0]++;

        if (count[0] == k) {
            kthSmallest[0] = root.val;
            return;
        } 

        inOrderHelper(root.right, count, k, kthSmallest);
    }
}

// 7. Merge Intervals 

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                merged.add(prev);
                prev = interval;
            }
        }

        merged.add(prev);

        return merged.toArray(new int[merged.size()][]);         
    }
}


// BurstBallons

public class BurstBallons {
    public int maxCoins(int[] nums) {

        int n = nums.length;
        int[] balloons = new int[n + 2];

        balloons[0] = 1;
        balloons[n + 1] = 1;

        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];

        for (int length = 2; length <= n + 1; length++) {
            for (int start = 0; start <= n + 1 - length; start++) {

                int end = start + length;
                for (int k = start + 1; k < end; k++) {

                    int coins = balloons[start] * balloons[k] * balloons[end];
                    int totalCoins = coins + dp[start][k] * dp[k][end];

                    dp[start][end] = Math.max(dp[start][end], totalCoins);
                }
            }
        }

        return dp[0][n + 1];
    }
}

