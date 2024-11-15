// 1. Merge Intervals

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                merged.add(prev);
                prev = interval;
            }
        }

        merged.add(prev);

        return merged.toArray(new int[merged.size()][]);         
    }
}



// 2. Insert Interval

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0;
        int n = intervals.length;
        List<int[]> result = new ArrayList<>();
        
        while (i < intervals.length) {
            if (intervals[i][1] < newInterval[0])
                result.add(intervals[i]);
            else if (intervals[i][0] > newInterval[1]){
                break;
            } else {
                //Overlap : merge them
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
            i++;
        }
        
        result.add(newInterval);
        while (i < n){
            result.add(intervals[i++]);
        }
        
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
