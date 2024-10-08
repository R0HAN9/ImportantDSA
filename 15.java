// 1. Climbing Stairs 

class Solution {
    public int climbStairs(int n) {
        if (n <= 3) return n;

        int prev1 = 3;
        int prev2 = 2;
        int cur = 0;

        for (int i = 3; i < n; i++) {
            cur = prev1 + prev2;
            prev2 = prev1;
            prev1 = cur;
        }

        return cur;        
    }
}

// 2. Coin Change 

class Solution {
    public int coinChange(int[] coins, int amount) {
        
        int[] minCoins = new int[amount + 1];
        Arrays.fill(minCoins, amount + 1);
        minCoins[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    minCoins[i] = Math.min(minCoins[i], 1 + minCoins[i - coins[j]]);
                }
            }
        }
        return minCoins[amount] != amount + 1 ? minCoins[amount] : -1;
    }
}

// 3. Longest Common Subsequence (LCS) 

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        
        int[] dp = new int[text1.length()];
        int longest = 0;

        for (char c : text2.toCharArray()) {
            int currentLen = 0;

            for (int i = 0; i < dp.length; i++) {
                if (currentLen < dp[i]) {
                    currentLen = dp[i];
                }
                else if (c == text1.charAt(i)) {
                    dp[i] = currentLen + 1;
                    longest = Math.max(longest, currentLen + 1);
                }
            }
        }
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
