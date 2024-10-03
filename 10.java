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

// 3. PostOrder → Binary Tree Maximum Path Sum 
