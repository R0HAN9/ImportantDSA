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
    public List<List<Integer>> subsets(int[] nums) {
        
        // Result list to store all subsets
        List<List<Integer>> result = new ArrayList<>();
        
        // Temporary list to represent a current subset during recursion
        List<Integer> subset = new ArrayList<>();

        // Start the recursive process to generate all subsets
        createSubset(nums, 0, result, subset);

        // Return the final list of subsets
        return result;
    }

    private void createSubset(int[] nums, int index, List<List<Integer>> result, List<Integer> subset) {

        // Base case: If the index has reached the end of the array, 
        // add the current subset to the result and return
        if (index == nums.length) {
            result.add(new ArrayList<>(subset)); // Add a copy of the current subset
            return;
        }

        // **Include the current element in the subset**
        subset.add(nums[index]); // Add the element at index to the current subset
        createSubset(nums, index + 1, result, subset); // Recur for the next index

        // **Exclude the current element from the subset**
        subset.remove(subset.size() - 1); // Remove the last added element
        createSubset(nums, index + 1, result, subset); // Recur for the next index
    }
}


// 3. N-Queens

class Solution {
    public List<List<String>> solveNQueens(int n) {
        
        List<List<String>> results = new ArrayList<>();

        if (n == 1) {
            List<String> solution = new ArrayList<>();
            solution.add("Q");
            results.add(solution);
            return results;

        }
        if (n == 2 || n == 3) return results;

        int[] solution = new int[n];
        for (int i = 0; i < n; i++) {
            solution[i] = -1;
        }

        solveNQueensRec(n, solution, 0, results);
        return results;
        
    }
    private void solveNQueensRec(int n, int[] solution, int row, List<List<String>> results) {

        if (row == n) {

            List<String> solutionStr = constructSolutionString(solution);
            results.add(solutionStr);
            return;
        }

        for (int i = 0; i < n; i++) {

            if (isValidMove(row, i, solution)) {
                solution[row] = i;
                solveNQueensRec(n, solution, row + 1, results);
                solution[row] = -1;
            }
        }
    }

    private boolean isValidMove(int proposedRow, int proposedCol, int[] solution) {

        for (int i = 0; i < proposedRow; i++) {

            int oldRow = i;
            int oldCol = solution[i];
            int diagonalOffSet = proposedRow - oldRow;

            if (oldCol == proposedCol || oldCol == proposedCol - diagonalOffSet || oldCol == proposedCol + diagonalOffSet) {
                return false;
            }
        }
        return true;
    }

    private List<String> constructSolutionString(int[] solution) {

        List<String> returnAll = new ArrayList<>();
        for (int i = 0; i < solution.length; i++) {

            char[] row = new char[solution.length];
            for (int j = 0; j < solution.length; j++) {
                row[j] = '.';
            }

            row[solution[i]] = 'Q';
            returnAll.add(new String(row));
        }

        return returnAll;
    }
}


// 4. Min Stack

class MinStack {
    private List<int[]> stack;

    public MinStack() {
        stack = new ArrayList<>();
    }
    
    public void push(int val) {
        
        int[] top = stack.isEmpty() ? new int[]{val, val} : stack.get(stack.size() - 1);
        int minValue = top[1];
        
        if (minValue > val) {
            minValue = val;
        }
        stack.add(new int[]{val, minValue});
    }
    
    public void pop() {
        stack.remove(stack.size() - 1);
    }
    
    public int top() {
        return stack.isEmpty() ? -1 : stack.get(stack.size() - 1)[0];
    }
    
    public int getMin() {
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
