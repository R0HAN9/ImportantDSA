//1. Kth Largest Element in an Array

class Solution {
    public int findKthLargest(int[] nums, int k) {
        
        // Step 1: Initialize variables
        int main_max = Integer.MIN_VALUE;  // To track the largest element seen
        HashMap<Integer,Integer> hm = new HashMap<>();  // A map to store element frequencies
        
        // Step 2: Count frequencies of each number and track the maximum element
        for(int i = 0; i < nums.length; i++){
            // If the element is already in the map, increment its count
            if(hm.containsKey(nums[i])){
                hm.put(nums[i], hm.get(nums[i]) + 1);
            } else {
                // Otherwise, add it with a count of 1
                hm.put(nums[i], 1);
            }
            // Track the maximum element encountered so far
            main_max = Math.max(main_max, nums[i]);
        }
        
        // Step 3: If there's only one unique element in the array, return it
        if(hm.size() == 1){
            return main_max;
        }
        
        // Step 4: Adjust k to account for occurrences of the largest element
        k -= hm.get(main_max);  // Subtract occurrences of the largest element
        
        // Step 5: Find the (k-th largest) element
        while(k > 0){
            main_max--;  // Decrease the value to check for the next largest element
            if(hm.containsKey(main_max)){
                k -= hm.get(main_max);  // If we find this element, decrease k by its frequency
            }
        }
        
        // Step 6: Return the k-th largest element
        return main_max;
    }
}




//2. Top K Frequent Elements

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        // Step 1: Count the frequency of each element using a HashMap
        Map<Integer, Integer> counter = new HashMap<>();
        for (int n : nums) {
            counter.put(n, counter.getOrDefault(n, 0) + 1);
        }
        
        // Step 2: Create an array of lists, where index represents frequency
        List<Integer>[] freq = new ArrayList[nums.length + 1];
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }
        
        // Step 3: Populate the frequency array with elements
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            int frequency = entry.getValue();
            freq[frequency].add(entry.getKey());
        }
        
        // Step 4: Extract the top k frequent elements
        int[] res = new int[k];
        int idx = 0;
        for (int i = freq.length - 1; i >= 0; i--) {
            for (int num : freq[i]) {
                res[idx++] = num;
                if (idx == k) {
                    return res;
                }
            }
        }
        
        // Step 5: Return an empty array in case something goes wrong (shouldn't happen in this problem)
        return new int[0];        
    }
}




//3. Find K Pairs with Smallest Sums

class Solution {

    // Helper class to store sum of the pair and indices i, j of the pairs
    private static class Pair {
        int sum, i, j;

        // Constructor to initialize the sum and indices
        public Pair(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        
        // List to store the resulting pairs
        List<List<Integer>> pairs = new ArrayList<>();
        
        // Length of the first array (nums1)
        int listLength = nums1.length;

        // Min-Heap (Priority Queue) to store the pairs with their sum, 
        // ensuring the smallest sum pair is always at the top
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> a.sum - b.sum);

        // Insert the first k pairs from nums1 and the first element of nums2 into the min-heap
        for (int i = 0; i < Math.min(k, listLength); i++) {
            minHeap.add(new Pair(nums1[i] + nums2[0], i, 0));
        }

        // Counter to keep track of the number of pairs we have processed
        int counter = 0;

        // Process the min-heap to get the smallest sum pairs and build the result
        while (!minHeap.isEmpty() && counter < k) {
            
            // Poll the smallest sum pair from the heap
            Pair pair = minHeap.poll();
            int i = pair.i; // Get index of the element in nums1
            int j = pair.j; // Get index of the element in nums2

            // Add the current pair to the result list
            pairs.add(Arrays.asList(nums1[i], nums2[j]));

            // If there is another element in nums2 for the current index i, 
            // add the next pair (nums1[i], nums2[j + 1]) to the min-heap
            int nextElement = j + 1;
            if (nums2.length > nextElement) {
                minHeap.add(new Pair(nums1[i] + nums2[nextElement], i, nextElement));
            }

            // Increment the counter to track the number of processed pairs
            counter++;
        }

        // Return the final list of pairs
        return pairs;
    }
}




//4. Majority Element

class Solution {
    public int majorityElement(int[] nums) {
        
        // Initialize variables: res to store the current candidate and majority to track the count
        int res = 0;
        int majority = 0;

        // Iterate through each number in the array
        for (int n : nums) {
            // If majority count is 0, set the current number as the candidate
            if (majority == 0) {
                res = n;
            }
            // Increment or decrement the majority count based on whether the current number matches the candidate
            majority += n == res ? 1 : -1;
        }

        // After finishing the loop, res will contain the majority element
        return res;
    }
}




//5. Missing Number

class Solution {
    public int missingNumber(int[] nums) {
        
        // Initialize the result to the length of the array
        // This is because, in the sum formula, the missing number is effectively the result.
        int result = nums.length;

        // Iterate through each number in the array
        for (int i = 0; i < nums.length; i++) {
            // Add the index i to the result, and subtract nums[i] to "cancel out" the value of nums[i]
            result += i - nums[i];
        }

        // The result now contains the missing number
        return result;
    }
}
