//1. Kth Largest Element in an Array

class Solution {
    public int findKthLargest(int[] nums, int k) {
        
        int main_max = Integer.MIN_VALUE;
        HashMap<Integer,Integer> hm = new HashMap<>();
        
        for(int i=0;i<nums.length;i++){
            if(hm.containsKey(nums[i])){
                hm.put(nums[i],hm.get(nums[i]) +1);
            } else{
                hm.put(nums[i],1);
            }
            main_max = Math.max(main_max,nums[i]);
        }
        
        if(hm.size()==1){
            return main_max;
        }
        
        k -= hm.get(main_max);
        while(k>0){
            main_max--;
            if(hm.containsKey(main_max)){
                k -= hm.get(main_max);
            }
        }
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
