// 1. Clone Graph 

class Solution {
    public Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }

        HashMap<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        visited.put(node, new Node(node.val));
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            for (Node neighbor : currentNode.neighbors) {
                if (!visited.containsKey(neighbor)) {

                    visited.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }

                visited.get(currentNode).neighbors.add(visited.get(neighbor));
            }
        }
        return visited.get(node);
    }
}

// 2. Path Sum II

class Solution {

    List<List<Integer>> answer = new ArrayList<List<Integer>>();

    private void dfs(TreeNode node, List<Integer> path, int remainingSum) {

        if (node == null) return;
        path.add(node.val);

        if (node.left == null && node.right == null && remainingSum == node.val) answer.add(new ArrayList<>(path));
        this.dfs(node.left, path, remainingSum - node.val);
        this.dfs(node.right, path, remainingSum - node.val);

        path.remove(path.size() - 1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        
        List<Integer> path = new ArrayList<Integer>();
        dfs(root, path, targetSum);
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
