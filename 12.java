// 1. Binary Tree Level Order Traversal

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        // Initialize a queue to facilitate level order traversal using BFS.
        Queue<TreeNode> queue = new LinkedList<>();
        // List to store the final result, each inner list will contain values of nodes at each level.
        List<List<Integer>> finalAns = new ArrayList<List<Integer>>();

        // If the root is null, return an empty list.
        if (root == null) return finalAns;
        
        // Start the BFS by adding the root node to the queue.
        queue.add(root);

        // Perform BFS until the queue is empty.
        while (!queue.isEmpty()) {
            // Get the number of nodes at the current level (all nodes at the current depth).
            int levels = queue.size();

            // A temporary list to hold the values of nodes at the current level.
            List<Integer> subLevels = new ArrayList<>();
            // Process all nodes at the current level.
            for (int i = 0; i < levels; i++) {
                
                // Check and add the left child of the current node, if it exists.
                if (queue.peek().left != null) {
                    queue.add(queue.peek().left);
                }
                
                // Check and add the right child of the current node, if it exists.
                if (queue.peek().right != null) {
                    queue.add(queue.peek().right);
                }

                // Remove the current node from the queue and add its value to the subList for the current level.
                subLevels.add(queue.remove().val);
            }

            // Add the list of nodes at the current level to the final answer.
            finalAns.add(subLevels);
        }

        // Return the final result containing lists of nodes grouped by levels.
        return finalAns;
    }
}


// 2. Rotting Oranges 

class Solution {
    public int orangesRotting(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = grid;

        Queue<int[]> queue = new LinkedList<>();
        int countFreshOrange = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 2) {
                    queue.offer(new int[] {i, j});
                }
                if (visited[i][j] == 1) {
                    countFreshOrange++;
                }
            }
        }
        if (countFreshOrange == 0) return 0;
        if (queue.isEmpty()) return -1;

        int minutes = -1;
        int[][] dirs = {{1,0}, {-1,0}, {0,-1}, {0,1}};

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] cell = queue.poll();

                int x = cell[0];
                int y = cell[1];

                for (int[] dir : dirs) {
                    int i = x + dir[0];
                    int j = y + dir[1];

                    if (i >= 0 && i < m && j >= 0 && j <n && visited[i][j] == 1) {
                        visited[i][j] = 2;
                        countFreshOrange--;
                        queue.offer(new int[] {i, j});
                    }
                }
            }
            minutes++;
        }

        if (countFreshOrange == 0) {
            return minutes;
        }

        return -1;
    }
}

// 3. Word Ladder

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>();
        Boolean isPresent = false;

        wordSet.addAll(wordList);
        for (String currWord : wordList) {
            if (endWord.equals(currWord)) {
                isPresent = true;
                break;
            }
        }

        if (!isPresent)
            return 0;
        Queue<String> wordQueue = new LinkedList<>();

        wordQueue.add(beginWord);
        int distance = 0;

        while (!wordQueue.isEmpty()) {
            int size = wordQueue.size();
            distance++;

            while (size-- != 0) {
                String currWord = wordQueue.poll();

                for (int i = 0; i < currWord.length(); i++) {
                    char[] temp = currWord.toCharArray();

                    for (char j = 'a'; j <= 'z'; j++) {
                        temp[i] = j;

                        String newWord = new String(temp);

                        if (newWord.equals(endWord))
                            return distance + 1;
                        if (wordSet.contains(newWord)) {
                            wordQueue.add(newWord);
                            wordSet.remove(newWord);

                            System.out.println(newWord);
                        }
                    }
                }
            }
        }
        return 0;
    }
}

// 4. Best Time to Buy and Sell Stock II

class Solution {
    public int maxProfit(int[] prices) {
        
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}

// 5. House Robber 

class Solution {
    public int rob(int[] nums) {
        
        int n = nums.length;
        if (n == 1) return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        return dp[n - 1];
    }
}
