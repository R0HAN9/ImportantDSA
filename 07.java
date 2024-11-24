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
        Map<Integer, Integer> counter = new HashMap<>();
        for (int n : nums) {
            counter.put(n, counter.getOrDefault(n, 0) + 1);
        }
        
        List<Integer>[] freq = new ArrayList[nums.length + 1];
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }
        
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            int frequency = entry.getValue();
            freq[frequency].add(entry.getKey());
        }
        
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
        
        return new int[0];        
    }
}



//3. Find K Pairs with Smallest Sums

class Solution {

    private static class Pair {
        int sum, i, j;

        public Pair(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
    }
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        
        List<List<Integer>> pairs = new ArrayList<>();
        int listLength = nums1.length;

        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> a.sum - b.sum);

        for (int i = 0; i < Math.min(k, listLength); i++) {
            minHeap.add(new Pair(nums1[i] + nums2[0], i, 0));
        }

        int counter = 0;
        while (!minHeap.isEmpty() && counter < k) {
            
            Pair pair = minHeap.poll();
            int i = pair.i;
            int j = pair.j;

            pairs.add(Arrays.asList(nums1[i], nums2[j]));
            int nextElement = j + 1;

            if (nums2.length > nextElement) {
                minHeap.add(new Pair(nums1[i] + nums2[nextElement], i, nextElement));
            }
            counter++;
        }
        return pairs;
    }
}



//4. Majority Element

class Solution {
    public int majorityElement(int[] nums) {
        
        int res = 0;
        int majority = 0;

        for (int n : nums) {
            if (majority == 0) {
                res = n;
            }
            majority += n == res ? 1 : -1;
        }
        return res;
    }
}



//5. Missing Number

class Solution {
    public int missingNumber(int[] nums) {
        
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result += i - nums[i];
        }
        return result;
    }
}
