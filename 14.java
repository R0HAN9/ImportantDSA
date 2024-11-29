// 1. Permutations

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        
        // Create a list to store all permutations of the input array
        List<List<Integer>> result = new ArrayList<>();

        // Base case: if the array has only one element, return a list containing that element as a single permutation
        if (nums.length == 1) {

            // Create a single-element list and add the element from the input array
            List<Integer> singleList = new ArrayList<>();
            singleList.add(nums[0]);

            // Add the single-element list to the result list
            result.add(singleList);

            // Return the result containing one permutation
            return result;
        }

        // Iterate over each element in the array to consider it as the first element in a permutation
        for (int i = 0; i < nums.length; i++) {

            // Pick the current element to be fixed in the current permutation
            int n = nums[i];

            // Create a new array to hold all elements except the current element
            int[] remainingNums = new int[nums.length - 1];
            int index = 0;

            // Populate the remainingNums array with all elements except the one at index 'i'
            for (int j = 0; j < nums.length; j++) {
                if (j != i) {
                    remainingNums[index] = nums[j];
                    index++;
                }
            }

            // Recursively find all permutations of the remaining elements
            List<List<Integer>> perms = permute(remainingNums);

            // For each permutation of the remaining elements, add the current fixed element 'n' to it
            for (List<Integer> p : perms) {
                p.add(n);
            }

            // Add all permutations created in this iteration to the final result
            result.addAll(perms);
        }

        // Return the complete list of permutations
        return result;
    }
}


// 2. Subsets 

class Solution {
    public List<List<String>> solveNQueens(int n) {
        
        // List to store all valid solutions
        List<List<String>> results = new ArrayList<>();

        // Handle trivial cases:
        // n = 1: Single queen can be placed in a 1x1 board
        if (n == 1) {
            List<String> solution = new ArrayList<>();
            solution.add("Q");
            results.add(solution);
            return results;
        }

        // n = 2 and n = 3 have no solutions as queens can't be placed without attacking each other
        if (n == 2 || n == 3) return results;

        // Initialize the `solution` array to keep track of queen positions
        // solution[i] = column index of the queen in row `i`
        int[] solution = new int[n];
        for (int i = 0; i < n; i++) {
            solution[i] = -1; // -1 indicates no queen is placed in that row yet
        }

        // Start the recursive backtracking to find solutions
        solveNQueensRec(n, solution, 0, results);
        return results;
    }

    // Recursive function to place queens row by row
    private void solveNQueensRec(int n, int[] solution, int row, List<List<String>> results) {

        // Base case: If all rows are filled, construct the solution string and add to results
        if (row == n) {
            List<String> solutionStr = constructSolutionString(solution);
            results.add(solutionStr);
            return;
        }

        // Iterate through each column in the current row to try placing a queen
        for (int i = 0; i < n; i++) {

            // Check if placing a queen at (row, i) is valid
            if (isValidMove(row, i, solution)) {
                // Place the queen at column `i` in the current row
                solution[row] = i;

                // Recur to the next row
                solveNQueensRec(n, solution, row + 1, results);

                // Backtrack: Remove the queen from the current row
                solution[row] = -1;
            }
        }
    }

    // Helper function to check if placing a queen is valid
    private boolean isValidMove(int proposedRow, int proposedCol, int[] solution) {

        // Check all previously placed queens
        for (int i = 0; i < proposedRow; i++) {

            int oldRow = i;             // Row of the previous queen
            int oldCol = solution[i];   // Column of the previous queen
            int diagonalOffset = proposedRow - oldRow; // Distance between rows

            // Conditions for invalid placement:
            // 1. Same column: `oldCol == proposedCol`
            // 2. Same diagonal: Check if the column difference matches the row difference
            if (oldCol == proposedCol || 
                oldCol == proposedCol - diagonalOffset || 
                oldCol == proposedCol + diagonalOffset) {
                return false;
            }
        }
        // If no conflicts, the move is valid
        return true;
    }

    // Helper function to convert the `solution` array into a list of strings
    private List<String> constructSolutionString(int[] solution) {

        // List to store the string representation of the board
        List<String> returnAll = new ArrayList<>();

        // Construct the string for each row
        for (int i = 0; i < solution.length; i++) {

            // Create a row filled with '.' to represent an empty board
            char[] row = new char[solution.length];
            for (int j = 0; j < solution.length; j++) {
                row[j] = '.';
            }

            // Place the queen ('Q') in the column specified by `solution[i]`
            row[solution[i]] = 'Q';
            returnAll.add(new String(row)); // Convert the row to a string and add to the list
        }

        return returnAll;
    }
}



// 4. Min Stack

class MinStack {
    // List to store the stack elements along with the current minimum at each level
    private List<int[]> stack;

    // Constructor to initialize the stack
    public MinStack() {
        stack = new ArrayList<>(); // Initialize the stack as an ArrayList
    }
    
    // Push a value onto the stack
    public void push(int val) {
        
        // If the stack is empty, the current minimum is the value itself
        int[] top = stack.isEmpty() ? new int[]{val, val} : stack.get(stack.size() - 1);
        int minValue = top[1]; // Get the current minimum value

        // Update the current minimum if the new value is smaller
        if (val < minValue) {
            minValue = val;
        }

        // Add the value and the updated minimum to the stack
        stack.add(new int[]{val, minValue});
    }
    
    // Remove the top element from the stack
    public void pop() {
        // Remove the last element of the stack
        stack.remove(stack.size() - 1);
    }
    
    // Get the top element of the stack
    public int top() {
        // Return the top value if the stack is not empty, otherwise -1
        return stack.isEmpty() ? -1 : stack.get(stack.size() - 1)[0];
    }
    
    // Get the minimum value in the stack
    public int getMin() {
        // Return the minimum value if the stack is not empty, otherwise -1
        return stack.isEmpty() ? -1 : stack.get(stack.size() - 1)[1];
    }
}


// 5. Contains Duplicate 

class Solution {
    public boolean containsDuplicate(int[] nums) {
        
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }
}
