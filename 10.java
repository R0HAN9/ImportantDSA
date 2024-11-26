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
    public int kthSmallest(TreeNode root, int k) {
        
        int[] count = new int[]{0};
        int[] kthSmallest = new int[] {Integer.MIN_VALUE};

        inOrderHelper(root, count, k, kthSmallest);
        return kthSmallest[0];

    }
    public void inOrderHelper(TreeNode root, int[] count, int k, int[] kthSmallest) {
        if (root == null || count[0] >= k) return;

        inOrderHelper(root.left, count, k, kthSmallest);
        count[0]++;

        if (count[0] == k) {
            kthSmallest[0] = root.val;
            return;
        } 

        inOrderHelper(root.right, count, k, kthSmallest);
    }
}



// 3. PostOrder → Binary Tree Maximum Path Sum 

class Solution {

    private int findMaxPathSum(TreeNode root, int[] maximum) {

        if (root == null) return 0;
        int leftSum = Math.max(0, findMaxPathSum(root.left, maximum));
        int rightSum = Math.max(0, findMaxPathSum(root.right, maximum));

        maximum[0] = Math.max(maximum[0], leftSum + rightSum + root.val);
        return root.val + Math.max(leftSum, rightSum); 
    }

    public int maxPathSum(TreeNode root) {
        
        int[] maximum = new int[1];
        maximum[0] = Integer.MIN_VALUE;
        findMaxPathSum(root, maximum);

        return maximum[0];
    }
}
