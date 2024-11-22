// 1. Maximum Average Subarray I

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        
        // Initialize the sum of the first 'k' elements
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        // Initialize maxSum with the sum of the first 'k' elements
        int maxSum = sum;

        // Iterate through the array starting from index 'k' to find the maximum sum of 'k' consecutive elements
        for (int i = k; i < nums.length; i++) {
            // Update the sum by adding the current element and removing the element that is now out of the window
            sum += nums[i] - nums[i - k];
            
            // Update maxSum if the current sum is greater
            maxSum = Math.max(maxSum, sum);
        }
        
        // Return the maximum average by dividing maxSum by 'k'
        return (double)maxSum / k;
    }
}




// 2. Longest Substring Without Repeating Characters 

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Initialize maxLength to store the length of the longest substring found
        int maxLength = 0;
        
        // 'left' pointer to track the start of the current window
        int left = 0;
        
        // HashMap to store the count of characters in the current window
        Map<Character, Integer> count = new HashMap<>();

        // Iterate through the string with 'right' pointer to expand the window
        for (int right = 0; right < s.length(); right++) {
            // Get the character at the current 'right' index
            char c = s.charAt(right);
            
            // Update the count of the current character in the window
            count.put(c, count.getOrDefault(c, 0) + 1);
            
            // If there are duplicate characters in the window, shrink the window from the left
            while (count.get(c) > 1) {
                // Get the character at the 'left' index
                char leftChar = s.charAt(left);
                
                // Decrease the count of the character at the 'left' index
                count.put(leftChar, count.get(leftChar) - 1);
                
                // Move the 'left' pointer to the right to shrink the window
                left++;
            }
            
            // Update maxLength to the larger of the current maxLength and the length of the current window
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        // Return the length of the longest substring without repeating characters
        return maxLength;       
    }
}




// 3. Minimum Window Substring

class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> charCount = new HashMap<>();
        for (char ch : t.toCharArray()) {
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }

        int targetCharsRemaining = t.length();
        int[] minWindow = {0, Integer.MAX_VALUE};
        int startIndex = 0;

        for (int endIndex = 0; endIndex < s.length(); endIndex++) {
            char ch = s.charAt(endIndex);
            if (charCount.containsKey(ch) && charCount.get(ch) > 0) {
                targetCharsRemaining--;
            }
            charCount.put(ch, charCount.getOrDefault(ch, 0) - 1);

            if (targetCharsRemaining == 0) {
                while (true) {
                    char charAtStart = s.charAt(startIndex);
                    if (charCount.containsKey(charAtStart) && charCount.get(charAtStart) == 0) {
                        break;
                    }
                    charCount.put(charAtStart, charCount.getOrDefault(charAtStart, 0) + 1);
                    startIndex++;
                }

                if (endIndex - startIndex < minWindow[1] - minWindow[0]) {
                    minWindow[0] = startIndex;
                    minWindow[1] = endIndex;
                }

                charCount.put(s.charAt(startIndex), charCount.getOrDefault(s.charAt(startIndex), 0) + 1);
                targetCharsRemaining++;
                startIndex++;
            }
        }

        return minWindow[1] >= s.length() ? "" : s.substring(minWindow[0], minWindow[1] + 1);        
    }
}



// 4.  Valid Palindrome

class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        s = s.replaceAll("[^a-zA-Z0-9]", "");
        if(s.length()<1){
            return true;
        }
        int i = 0;
        return Solution.rec(i,s);
    }
    static private boolean rec(int i, String s){
        
            // Base Condition
            // If i exceeds half of the string, means all the elements 
            // are compared, we return true.
            if(i>=s.length()/2) return true;
            
            // If start is not equal to end, not palindrome.
            if(s.charAt(i)!=s.charAt(s.length()-i-1)) return false;
            
            // If both characters are same, increment i and check start+1 and end-1.
            return rec(i+1,s);
    }
}



// 5. Invert Binary Tree

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        invertTree(root.left);
        invertTree(root.right);
        
        return root;        
    }
}
