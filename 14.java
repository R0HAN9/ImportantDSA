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
