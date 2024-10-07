// 1. Permutations

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 1) {

            List<Integer> singleList = new ArrayList<>();
            singleList.add(nums[0]);
            result.add(singleList);
            return result;

        }
        for (int i = 0; i < nums.length; i++) {

            int n = nums[i];
            int[] remainingNums = new int[nums.length - 1];
            int index = 0;

            for (int j = 0; j < nums.length; j++) {
                if (j != i) {
                    remainingNums[index] = nums[j];
                    index++;
                }
            }
            List<List<Integer>> perms = permute(remainingNums);
            for (List<Integer> p : perms) {
                p.add(n);
            }

            result.addAll(perms);
        }

        return result;
    }
}

// 2. Subsets 

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();

        createSubset(nums, 0, result, subset);
        return result;
    }

    private void createSubset(int[] nums, int index, List<List<Integer>> result, List<Integer> subset) {

        if (index == nums.length) {
            result.add(new ArrayList<>(subset));
            return;
        }

        subset.add(nums[index]);
        createSubset(nums, index + 1, result, subset);

        subset.remove(subset.size() - 1);
        createSubset(nums, index + 1, result, subset);
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
