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

// 3. Longest Common Subsequence (LCS) 

// 4. Longest Increasing Subsequence (LIS) 

// 5. Partition Equal Subset Sum 
