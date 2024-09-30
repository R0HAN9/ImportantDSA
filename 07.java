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

//4. Majority Element

//5. Missing Number
