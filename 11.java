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

        // Step 1: Create an adjacency list to represent the graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 2: Fill the adjacency list based on the prerequisites array
        // prerequisites[i] = [course, prerequisite]
        int m = prerequisites.length;
        for (int i = 0; i < m; i++) {
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        // Step 3: Calculate the indegree for each node (number of prerequisites for each course)
        int indegree[] = new int[n];
        for (int i = 0; i < n; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        // Step 4: Initialize a queue to perform topological sorting (using Kahn's algorithm)
        Queue<Integer> q = new LinkedList<>();
        
        // Step 5: Add nodes with no prerequisites (indegree == 0) to the queue
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // Step 6: Perform BFS to generate the topological order
        int topo[] = new int[n];
        int index = 0;

        while (!q.isEmpty()) {
            int node = q.peek();  // Get the node with no prerequisites

            q.remove();           // Remove it from the queue
            topo[index++] = node; // Add it to the topological order array

            // Step 7: Decrease the indegree of neighbors (courses dependent on the current course)
            // If a neighbor's indegree becomes 0, add it to the queue
            for (int it : adj.get(node)) {
                indegree[it]--;

                // If this neighbor has no remaining prerequisites, add it to the queue
                if (indegree[it] == 0) q.add(it);
            }
        }

        // Step 8: If all courses are added to the topological order, return it
        // If not, there is a cycle, and we cannot finish all courses
        if (index == n) return topo;

        // If there is a cycle (some courses couldn't be completed), return an empty array
        int[] arr = {};
        return arr;
    }
}



// 4. Symmetric Tree


class Solution {
    
    // Main function to check if the tree is symmetric
    public boolean isSymmetric(TreeNode root) {
        // A tree is symmetric if the left subtree is a mirror image of the right subtree
        return isMirror(root.left, root.right);
    }

    // Helper function to check if two subtrees are mirror images of each other
    private boolean isMirror(TreeNode n1, TreeNode n2) {
        // If both nodes are null, they are symmetric (empty trees are symmetric)
        if (n1 == null && n2 == null) {
            return true;
        }
        
        // If only one of the nodes is null, they are not symmetric
        if (n1 == null || n2 == null) {
            return false;
        }
        
        // Check if the current nodes are the same and recursively check their subtrees
        // The left subtree of n1 should be a mirror of the right subtree of n2
        // and the right subtree of n1 should be a mirror of the left subtree of n2
        return n1.val == n2.val &&
               isMirror(n1.left, n2.right) && // Check left of n1 with right of n2
               isMirror(n1.right, n2.left);   // Check right of n1 with left of n2
    }
}


// 5. Product of Array Except Self

class Solution {
    public int[] productExceptSelf(int[] nums) {
        
        // Initialize an output array to store the result.
        // Each element in output will eventually hold the product of all elements except itself.
        int[] output = new int[nums.length];
        
        // Initialize the output array with 1, as we will be multiplying the elements later.
        for (int i = 0; i < nums.length; i++) {
            output[i] = 1;
        }

        // Compute the product of all elements to the left of each index.
        int left = 1; // Variable to track the product of elements to the left.
        for (int i = 0; i < nums.length; i++) {
            output[i] *= left; // Multiply the current value of output[i] with the product of elements to the left.
            left *= nums[i]; // Update left to include the current element nums[i] for the next iteration.
        }

        // Compute the product of all elements to the right of each index.
        int right = 1; // Variable to track the product of elements to the right.
        for (int i = nums.length - 1; i >= 0; i--) {
            output[i] *= right; // Multiply the current value of output[i] with the product of elements to the right.
            right *= nums[i]; // Update right to include the current element nums[i] for the next iteration.
        }

        // The output array now contains the product of all elements except the one at each index.
        return output;
    }
}

