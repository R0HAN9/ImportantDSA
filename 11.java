// 1. Clone Graph 

class Solution {
    public Node cloneGraph(Node node) {

        // Base case: If the input node is null, return null (empty graph)
        if (node == null) {
            return null;
        }

        // A HashMap to store the mapping between original nodes and their clones
        HashMap<Node, Node> visited = new HashMap<>();

        // Queue to perform BFS (Breadth-First Search) for graph traversal
        Queue<Node> queue = new LinkedList<>();

        // Create the clone of the input node and put it into the visited map
        visited.put(node, new Node(node.val));

        // Start BFS by adding the input node to the queue
        queue.offer(node);

        // BFS loop to visit all nodes and their neighbors
        while (!queue.isEmpty()) {
            // Pop a node from the queue to process
            Node currentNode = queue.poll();

            // Iterate through all the neighbors of the current node
            for (Node neighbor : currentNode.neighbors) {

                // If the neighbor is not visited, create a clone of the neighbor
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);  // Add the neighbor to the queue for later processing
                }

                // Add the clone of the neighbor to the neighbors list of the current node's clone
                visited.get(currentNode).neighbors.add(visited.get(neighbor));
            }
        }

        // Return the clone of the original node (entry point)
        return visited.get(node);
    }
}


// 2. Path Sum II

class Solution {

    // List to store all the paths that sum up to the target value
    List<List<Integer>> answer = new ArrayList<List<Integer>>();

    // Helper function for DFS traversal of the tree
    private void dfs(TreeNode node, List<Integer> path, int remainingSum) {

        // Base case: If the node is null, return (no path exists here)
        if (node == null) return;

        // Add the current node's value to the path
        path.add(node.val);

        // If it's a leaf node (no left or right child) and the remaining sum equals the node's value
        // Then we've found a valid path, so add it to the answer list
        if (node.left == null && node.right == null && remainingSum == node.val) {
            answer.add(new ArrayList<>(path));  // Add a copy of the current path to the answer
        }

        // Recursively traverse the left and right children, updating the remaining sum
        this.dfs(node.left, path, remainingSum - node.val);
        this.dfs(node.right, path, remainingSum - node.val);

        // Backtrack: Remove the current node's value from the path before returning to the parent node
        path.remove(path.size() - 1);
    }

    // Main function that initializes the DFS process and returns the result
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        
        // Initialize an empty list to store the current path during DFS
        List<Integer> path = new ArrayList<Integer>();
        
        // Start DFS from the root with the initial target sum
        dfs(root, path, targetSum);
        
        // Return all the valid paths that sum to the target value
        return answer;
    }
}


// 3. Course Schedule II

class Solution {
    public int[] findOrder(int n, int[][] prerequisites) {
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        int m = prerequisites.length;
        for (int i = 0; i < m; i++) {
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        int indegree[] = new int[n];
        for (int i = 0; i <n; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int topo[] = new int[n];
        int index = 0;

        while (!q.isEmpty()) {
            int node = q.peek();

            q.remove();
            topo[index++] = node;

            for (int it : adj.get(node)) {
                indegree[it]--;

                if (indegree[it] == 0) q.add(it);
            }
        }

        if (index == n) return topo;
        int[] arr = {};
        return arr;
    }
}


// 4. Symmetric Tree


class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }
    
    private boolean isMirror(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        
        if (n1 == null || n2 == null) {
            return false;
        }
        
        return n1.val == n2.val && isMirror(n1.left, n2.right) && isMirror(n1.right, n2.left);
    }
}

// 5. Product of Array Except Self

class Solution {
    public int[] productExceptSelf(int[] nums) {
        
        int[] output = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            output[i] = 1;
        }

        int left = 1;
        for (int i = 0; i < nums.length; i++) {
            output[i] *= left;
            left *= nums[i];
        }

        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            output[i] *= right;
            right *= nums[i];
        }

        return output;
    }
}
