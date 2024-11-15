// 1. PreOrder → Binary Tree Paths 

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        
        List<String> result = new ArrayList<>();
        dfs(root, new StringBuilder(), result);
        return result;

    }
    private void dfs(TreeNode node, StringBuilder path, List<String> result) {
        if (node == null) return;
        int len = path.length();
        
        if (len > 0) {
            path.append("->");
        }

        path.append(node.val);
        if (node.left == null && node.right == null) {
            result.add(path.toString());
        }
        else {
            dfs(node.left, path, result);
            dfs(node.right, path, result);
        }

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
