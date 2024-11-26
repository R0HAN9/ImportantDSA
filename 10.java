// 1. PreOrder → Binary Tree Paths 

class Solution {

    // Main method to return all root-to-leaf paths in the binary tree
    public List<String> binaryTreePaths(TreeNode root) {
        // List to store the final result
        List<String> result = new ArrayList<>();
        
        // Perform Depth-First Search (DFS) to find all paths
        dfs(root, new StringBuilder(), result);
        
        // Return the collected paths
        return result;
    }

    // Helper function to perform DFS and construct paths
    private void dfs(TreeNode node, StringBuilder path, List<String> result) {
        // Base case: If the node is null, return (end of a path)
        if (node == null) return;

        // Store the current length of the path (used to backtrack later)
        int len = path.length();

        // Append "->" if this is not the first node in the path
        if (len > 0) {
            path.append("->");
        }

        // Append the current node's value to the path
        path.append(node.val);

        // If the current node is a leaf (no left or right child)
        if (node.left == null && node.right == null) {
            // Add the constructed path to the result list
            result.add(path.toString());
        } else {
            // Recursively traverse the left and right children
            dfs(node.left, path, result);
            dfs(node.right, path, result);
        }

        // Backtrack: Restore the path to its previous state
        path.setLength(len);
    }
}




// 2. InOrder → Kth Smallest Element in a BST 

class Solution {

    // Main method to return the kth smallest element in the BST
    public int kthSmallest(TreeNode root, int k) {
        // An array to keep track of the count of nodes visited during the in-order traversal
        int[] count = new int[]{0};
        
        // An array to store the kth smallest element
        int[] kthSmallest = new int[] {Integer.MIN_VALUE};

        // Perform in-order traversal to find the kth smallest element
        inOrderHelper(root, count, k, kthSmallest);
        
        // Return the kth smallest element found
        return kthSmallest[0];
    }

    // Helper function to perform in-order traversal and find the kth smallest element
    public void inOrderHelper(TreeNode root, int[] count, int k, int[] kthSmallest) {
        // Base case: If the node is null or we've already found the kth smallest element
        if (root == null || count[0] >= k) return;

        // Traverse the left subtree first (in-order traversal)
        inOrderHelper(root.left, count, k, kthSmallest);

        // Increment the count of nodes visited
        count[0]++;

        // If we've visited the kth node, store its value as the kth smallest element
        if (count[0] == k) {
            kthSmallest[0] = root.val;
            return;  // We found the kth smallest, no need to continue
        }

        // Traverse the right subtree
        inOrderHelper(root.right, count, k, kthSmallest);
    }
}




// 3. PostOrder → Binary Tree Maximum Path Sum 

class Solution {

    // Helper function to calculate the maximum path sum, returning the maximum sum from the root to any node
    private int findMaxPathSum(TreeNode root, int[] maximum) {
        // Base case: if the node is null, return 0 (no contribution to the sum)
        if (root == null) return 0;

        // Recursively compute the maximum path sum of the left and right subtrees
        // We use Math.max(0, ...) to ignore negative path sums, as they wouldn't contribute positively
        int leftSum = Math.max(0, findMaxPathSum(root.left, maximum));
        int rightSum = Math.max(0, findMaxPathSum(root.right, maximum));

        // Update the global maximum path sum if the sum of the current node and both left and right children is greater
        maximum[0] = Math.max(maximum[0], leftSum + rightSum + root.val);

        // Return the maximum path sum that can be obtained starting from the current node and going downwards
        // This would be either the path going through the left or right subtree (whichever is greater)
        return root.val + Math.max(leftSum, rightSum); 
    }

    // Main function to calculate the maximum path sum of the tree
    public int maxPathSum(TreeNode root) {
        // Array to store the global maximum path sum (updated during the traversal)
        int[] maximum = new int[1];
        // Initialize the global maximum with the smallest possible integer value
        maximum[0] = Integer.MIN_VALUE;

        // Call the helper function to calculate the path sum
        findMaxPathSum(root, maximum);

        // Return the global maximum path sum
        return maximum[0];
    }
}

