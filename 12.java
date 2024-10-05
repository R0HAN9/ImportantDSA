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

// 3. Word Ladder
