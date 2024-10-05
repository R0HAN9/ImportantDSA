// 1. Binary Tree Level Order Traversal

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> finalAns = new ArrayList<List<Integer>>();

        if (root == null) return finalAns;
        queue.add(root);

        while (!queue.isEmpty()) {
            int levels = queue.size();

            List<Integer> subLevels = new ArrayList<>();
            for (int i = 0; i < levels; i++) {
                
                if (queue.peek().left != null) {
                    queue.add(queue.peek().left);
                }
                if (queue.peek().right != null) {
                    queue.add(queue.peek().right);
                }

                subLevels.add(queue.remove().val);
            }

            finalAns.add(subLevels);
        }
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


