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

// 4. Longest Increasing Subsequence (LIS) 

// 5. Partition Equal Subset Sum 
