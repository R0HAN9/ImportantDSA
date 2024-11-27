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
        
        // Get the dimensions of the grid
        int m = grid.length;  // Number of rows
        int n = grid[0].length;  // Number of columns
        
        // To track visited cells, we can directly use the grid itself as it already has the status of each orange.
        int[][] visited = grid;

        // Queue to perform BFS (Breadth-First Search), stores coordinates of rotten oranges
        Queue<int[]> queue = new LinkedList<>();
        // Count of fresh oranges that still need to rot
        int countFreshOrange = 0;

        // Traverse the grid to find all rotten oranges and count fresh oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 2) {  // Rotten orange found, add it to the queue
                    queue.offer(new int[] {i, j});
                }
                if (visited[i][j] == 1) {  // Fresh orange found, increment the fresh orange counter
                    countFreshOrange++;
                }
            }
        }

        // If no fresh oranges exist, no time is needed, return 0
        if (countFreshOrange == 0) return 0;

        // If there are fresh oranges but no rotten oranges to start the process, return -1
        if (queue.isEmpty()) return -1;

        // Minutes variable to track the time taken to rot all fresh oranges
        int minutes = -1;

        // Directions array to check the four possible directions (up, down, left, right)
        int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        // Perform BFS to rot the fresh oranges
        while (!queue.isEmpty()) {
            // Get the number of rotten oranges at the current level (minutes)
            int size = queue.size();

            // Process all rotten oranges at the current level
            while (size-- > 0) {
                int[] cell = queue.poll();  // Get the next rotten orange's coordinates

                int x = cell[0];
                int y = cell[1];

                // Check all 4 possible directions around the current rotten orange
                for (int[] dir : dirs) {
                    int i = x + dir[0];  // New row coordinate
                    int j = y + dir[1];  // New column coordinate

                    // Ensure the new coordinates are within the grid bounds and the orange is fresh (1)
                    if (i >= 0 && i < m && j >= 0 && j < n && visited[i][j] == 1) {
                        visited[i][j] = 2;  // Make the fresh orange rotten
                        countFreshOrange--;  // Decrease the count of fresh oranges
                        queue.offer(new int[] {i, j});  // Add this new rotten orange to the queue
                    }
                }
            }
            // Increment the minutes after processing one level of BFS
            minutes++;
        }

        // If there are no fresh oranges left, return the time taken (minutes)
        if (countFreshOrange == 0) {
            return minutes;
        }

        // If there are still fresh oranges left, return -1 (not all oranges can rot)
        return -1;
    }
}


// 3. Word Ladder

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // Convert the word list to a set for quick lookups
        Set<String> wordSet = new HashSet<>();
        Boolean isPresent = false;

        // Add all words from the wordList into the set for efficient checking
        wordSet.addAll(wordList);

        // Check if the endWord is present in the wordSet; if not, return 0
        for (String currWord : wordList) {
            if (endWord.equals(currWord)) {
                isPresent = true;
                break;
            }
        }

        // If the endWord is not in the wordList, no valid transformation exists
        if (!isPresent)
            return 0;

        // Queue to perform BFS
        Queue<String> wordQueue = new LinkedList<>();
        
        // Start with the beginWord
        wordQueue.add(beginWord);
        // Initialize distance as 0
        int distance = 0;

        // Perform BFS to find the shortest transformation sequence
        while (!wordQueue.isEmpty()) {
            // Get the number of words at the current level (i.e., all words at the same distance)
            int size = wordQueue.size();
            // Increment the distance for the current level
            distance++;

            // Process all words at the current level
            while (size-- != 0) {
                String currWord = wordQueue.poll();  // Get the current word to transform
                
                // Try all possible transformations of the current word
                for (int i = 0; i < currWord.length(); i++) {
                    char[] temp = currWord.toCharArray();  // Convert word to char array for modification

                    // Replace each character with every letter from 'a' to 'z'
                    for (char j = 'a'; j <= 'z'; j++) {
                        temp[i] = j;  // Modify the i-th character

                        // Convert char array back to string
                        String newWord = new String(temp);

                        // If the new word is the endWord, return the current distance + 1 (since it's the last step)
                        if (newWord.equals(endWord))
                            return distance + 1;

                        // If the new word is in the word set, add it to the queue for further processing
                        if (wordSet.contains(newWord)) {
                            wordQueue.add(newWord);
                            // Remove the new word from the set to avoid revisiting it
                            wordSet.remove(newWord);

                            // Optional: Print the newly generated word for debugging
                            System.out.println(newWord);
                        }
                    }
                }
            }
        }

        // If we finish the BFS without finding the endWord, return 0 (no valid transformation)
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
