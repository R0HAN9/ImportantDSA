// 1. Flood Fill 

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        
        int val = image[sr][sc];
        dfs(image, sr, sc, val, newColor);
        return image;

    }
    public void dfs(int[][] image, int i, int j, int val, int newColor) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || 
        image[i][j] == newColor || image[i][j] != val) {
            return;
        }

        image[i][j] = newColor;

        dfs(image, i-1, j, val, newColor);
        dfs(image, i+1, j, val, newColor);
        dfs(image, i, j-1, val, newColor);
        dfs(image, i, j+1, val, newColor);
    }
}

// 2. Number of Islands 

class Solution {
    public int numIslands(char[][] grid) {
        
        int island = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Set<String> visited = new HashSet<>();

        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1' && !visited.contains(r + "," + c)) {
                    island++;
                    bfs(grid, r, c, visited, directions, rows, cols);
                }
            }
        }
        return island;
    }

    private void bfs(char[][] grid, int r, int c, Set<String> visited, int[][] directions, int rows, int cols) {
        Queue<int[]> queue = new LinkedList<>();
        visited.add(r + "," + c);
        queue.add(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int row = point[0], col = point[1];

            for (int[] direction : directions) {
                int nr = row + direction[0], nc = col + direction[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == '1' && !visited.contains(nr + "," + nc)) {
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
