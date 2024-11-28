// 1. Flood Fill 

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // Get the current color of the starting pixel
        int val = image[sr][sc];
        
        // Perform Depth-First Search (DFS) to fill the connected region
        dfs(image, sr, sc, val, newColor);
        
        // Return the modified image
        return image;
    }

    public void dfs(int[][] image, int i, int j, int val, int newColor) {
        // Base condition to stop recursion:
        // - Out of bounds (i or j is outside the image)
        // - Pixel is already filled with the new color
        // - Pixel color is not the target color (val)
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || 
            image[i][j] == newColor || image[i][j] != val) {
            return;
        }

        // Fill the current pixel with the new color
        image[i][j] = newColor;

        // Recursive calls to visit all 4 neighboring pixels (up, down, left, right)
        dfs(image, i-1, j, val, newColor); // Move up
        dfs(image, i+1, j, val, newColor); // Move down
        dfs(image, i, j-1, val, newColor); // Move left
        dfs(image, i, j+1, val, newColor); // Move right
    }
}


// 2. Number of Islands 

import java.util.*;

class Solution {
    public int numIslands(char[][] grid) {
        
        // Variable to count the number of islands
        int island = 0;

        // Get the number of rows and columns in the grid
        int rows = grid.length;
        int cols = grid[0].length;

        // Set to track visited cells to avoid reprocessing
        Set<String> visited = new HashSet<>();

        // Directions array for moving up, down, left, and right
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        // Iterate over each cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                
                // If the current cell is land ('1') and hasn't been visited yet
                if (grid[r][c] == '1' && !visited.contains(r + "," + c)) {
                    
                    // Increment the island count
                    island++;
                    
                    // Perform BFS to mark all connected land cells as visited
                    bfs(grid, r, c, visited, directions, rows, cols);
                }
            }
        }

        // Return the total number of islands found
        return island;
    }

    private void bfs(char[][] grid, int r, int c, Set<String> visited, int[][] directions, int rows, int cols) {
        // Queue for BFS traversal
        Queue<int[]> queue = new LinkedList<>();
        
        // Mark the starting cell as visited
        visited.add(r + "," + c);
        
        // Add the starting cell to the queue
        queue.add(new int[]{r, c});

        // BFS traversal
        while (!queue.isEmpty()) {
            // Dequeue the current cell
            int[] point = queue.poll();
            int row = point[0], col = point[1];

            // Check all four directions (up, down, left, right)
            for (int[] direction : directions) {
                int nr = row + direction[0], nc = col + direction[1];

                // Check if the new cell is within bounds and is land ('1') and not visited
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == '1' && !visited.contains(nr + "," + nc)) {
                    // Add the new cell to the queue and mark it as visited
                    queue.add(new int[]{nr, nc});
                    visited.add(nr + "," + nc);
                }
            }
        }
    }
}


// 3. Surrounded Regions 


class Solution {
    int row = 0;
    int col = 0;
    
    public void solve(char[][] board) {
        row = board.length;
        col = board[0].length;
        
        // Mark 'O' cells on the borders and initiate DFS from them
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][col - 1] == 'O') {
                dfs(board, i, col - 1);
            }
        }
        for (int j = 0; j < col; j++) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }
            if (board[row - 1][j] == 'O') {
                dfs(board, row - 1, j);
            }
        }
        
        // Convert remaining 'O' cells to 'X', and revert marked cells back to 'O'
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void dfs(char[][] board, int i, int j) {
        // Out of bounds check
        if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] != 'O') {
            return;
        }
        // Mark current 'O' cell as visited
        board[i][j] = '1';
        
        // DFS in all four directions
        dfs(board, i, j - 1); // left
        dfs(board, i - 1, j); // up
        dfs(board, i, j + 1); // right
        dfs(board, i + 1, j); // down
    }
}

// 4. Number of 1 Bits

class Solution {
    public int hammingWeight(int n) {
        
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (((n >> i) & 1) == 1) {
                result += 1;
            }
        }
        return result;
    }
}

// 5. Validate Binary Search Tree


class Solution {
    public boolean isValidBST(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean valid(TreeNode node, long minimum, long maximum) {
        if (node == null) return true;

        if (!(node.val > minimum && node.val < maximum)) return false;

        return valid(node.left, minimum, node.val) && valid(node.right, node.val, maximum);
    }
}
