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

// 3. Course Schedule II
