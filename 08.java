// 1. Merge Intervals

class Solution {
    public int[][] merge(int[][] intervals) {
        // Step 1: Sort the intervals based on their start times.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Initialize a list to store merged intervals and set the first interval as the previous interval.
        List<int[]> merged = new ArrayList<>();
        int[] prev = intervals[0];

        // Step 3: Iterate through the intervals starting from the second one.
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i]; // Current interval being processed.

            // Check if the current interval overlaps with the previous one.
            if (interval[0] <= prev[1]) {
                // If they overlap, merge them by updating the end of the previous interval.
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                // If they don't overlap, add the previous interval to the list and update the previous interval.
                merged.add(prev);
                prev = interval;
            }
        }

        // Step 4: Add the last processed interval to the merged list.
        merged.add(prev);

        // Step 5: Convert the list to a 2D array and return the result.
        return merged.toArray(new int[merged.size()][]);         
    }
}




// 2. Insert Interval

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0; // Index to iterate through intervals
        int n = intervals.length; // Total number of intervals
        List<int[]> result = new ArrayList<>(); // List to store the resulting intervals
        
        // Step 1: Add all intervals that come before the new interval (no overlap)
        while (i < intervals.length) {
            if (intervals[i][1] < newInterval[0]) { // Current interval ends before the new interval starts
                result.add(intervals[i]); // Add the current interval to the result
            } else {
                break; // Found an overlap or the position for the new interval
            }
            i++;
        }
        
        // Step 2: Merge overlapping intervals with the new interval
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            // Update the new interval's start and end to include the overlap
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        // Add the merged new interval to the result
        result.add(newInterval);
        
        // Step 3: Add all intervals that come after the new interval (no overlap)
        while (i < n) {
            result.add(intervals[i++]); // Add remaining intervals to the result
        }
        
        // Convert the result list to a 2D array and return
        return result.toArray(new int[result.size()][2]);
    }
}





// 3. Non-Overlapping Intervals

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int res = 0;

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int prev_end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (prev_end > intervals[i][0]) {
                res++;
            } else {
                prev_end = intervals[i][1];
            }
        }

        return res;        
    }
}



// 4. Reverse String

class Solution {
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}


// 5. Diameter of Binary Tree

class Solution {
    int maxx = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return maxx;
    }
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftd = maxDepth(root.left);
        int rightd = maxDepth(root.right); 
        maxx = Math.max(maxx,leftd+rightd);
        return Math.max(leftd,rightd)+1;
    }
}
