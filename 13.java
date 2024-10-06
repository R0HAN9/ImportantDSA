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
