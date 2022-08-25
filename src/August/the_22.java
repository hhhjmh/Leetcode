package August;

import java.util.*;

public class the_22 {
    public static void main(String[] args) {
/*        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t1.left = t2;t1.right=t3;t2.right=t4;
        new Solution655().printTree(t1);*/

/*        new Solution57().insert(new int[][] {
                {0,1},
                {2,3},
                {7,8},
                {9,9},
        }, new int[]{19,26});*/

//        new Solution763().partitionLabels("ababcbacadefegdehijhklij");

        // firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
/*        new Solution986().intervalIntersection(new int[][] {
                {0,2},
                {5,10},
                {13,23},
                {24,25}
        }, new int[][] {
                {1,5},
                {8,12},
                {15,24},
                {25,26}
        });*/

//        new Solution58().lengthOfLastWord("   fly me   to   the moon  ");

//        new Solution62().uniquePaths(100, 100);

        // [[-2,-3,3],[-5,-10,1],[10,30,-5]]
        new Solution174().calculateMinimumHP(new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5},

        });

    }
}

class Solution655 {
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> printTree(TreeNode root) {

        int height = depth(root);
        int m = (int)Math.pow(2, height) - 1;
        for(int i=0; i<height; i++) {
            List<String> row = new ArrayList<>();
            for(int j=0; j<m; j++) {
                row.add("");
            }
            ans.add(row);
        }

        DFS(root, 0, (m-1)/2, height-1);

        return ans;
    }

    private void DFS(TreeNode root, int r, int c, int height) {
        if(root!=null) {
            ans.get(r).set(c, String.valueOf(root.val));
            DFS(root.left, r+1, c-(int)Math.pow(2, height-r-1), height);
            DFS(root.right, r+1, c+(int)Math.pow(2, height-r-1), height);
        }
    }

    private int depth(TreeNode root) {
        if(root==null)
            return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}

class Solution56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for(int i=0; i<intervals.length; i++) {
            if(intervals[index][1]>=intervals[i][0]) {
                intervals[index][1] = Math.max(intervals[index][1], intervals[i][1]);
            } else {
                index = i;
            }
            map.put(intervals[index][0], intervals[index][1]);
        }
        int n = map.size();
        int [][] ans = new int[n][2];
        int i=0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans[i][0] = entry.getKey();
            ans[i][1] = entry.getValue();
            i++;
        }

        return ans;
    }
}

class Solution57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        int len = intervals.length;
        int[][] old = new int[len+1][2];
        int k=0, j=0;
        for(; k<old.length && j<len; k++) {
            if(newInterval[0]<intervals[j][0]) {
                old[k][0] = newInterval[0];
                old[k][1] = newInterval[1];
                newInterval[0] = Integer.MAX_VALUE;
            } else {
                old[k][0] = intervals[j][0];
                old[k][1] = intervals[j][1];
                j++;
            }
        }
        if(k<old.length) {
            old[k][0] = newInterval[0];
            old[k][1] = newInterval[1];
        }



        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for(int i=0; i<old.length; i++) {
            if(old[index][1]>=old[i][0]) {
                old[index][1] = Math.max(old[index][1], old[i][1]);
            } else {
                index = i;
            }
            map.put(old[index][0], old[index][1]);
        }
        int n = map.size();
        int [][] ans = new int[n][2];
        int i=0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans[i][0] = entry.getKey();
            ans[i][1] = entry.getValue();
            i++;
        }

        Arrays.sort(ans, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        return ans;
    }
}

// 715
class RangeModule {
    TreeMap<Integer, Integer> intervals;

    public RangeModule() {
        intervals = new TreeMap<Integer, Integer>();
    }

    public void addRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        if (entry != intervals.firstEntry()) {
            Map.Entry<Integer, Integer> start = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
            if (start != null && start.getValue() >= right) {
                return;
            }
            if (start != null && start.getValue() >= left) {
                left = start.getKey();
                intervals.remove(start.getKey());
            }
        }
        while (entry != null && entry.getKey() <= right) {
            right = Math.max(right, entry.getValue());
            intervals.remove(entry.getKey());
            entry = intervals.higherEntry(entry.getKey());
        }
        intervals.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        if (entry == intervals.firstEntry()) {
            return false;
        }
        entry = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
        return entry != null && right <= entry.getValue();
    }

    public void removeRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        if (entry != intervals.firstEntry()) {
            Map.Entry<Integer, Integer> start = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
            if (start != null && start.getValue() >= right) {
                int ri = start.getValue();
                if (start.getKey() == left) {
                    intervals.remove(start.getKey());
                } else {
                    intervals.put(start.getKey(), left);
                }
                if (right != ri) {
                    intervals.put(right, ri);
                }
                return;
            } else if (start != null && start.getValue() > left) {
                if (start.getKey() == left) {
                    intervals.remove(start.getKey());
                } else {
                    intervals.put(start.getKey(), left);
                }
            }
        }
        while (entry != null && entry.getKey() < right) {
            if (entry.getValue() <= right) {
                intervals.remove(entry.getKey());
                entry = intervals.higherEntry(entry.getKey());
            } else {
                intervals.put(right, entry.getValue());
                intervals.remove(entry.getKey());
                break;
            }
        }
    }
}

class Solution763 {
    public List<Integer> partitionLabels(String s) {
        int[][] arr = new int[26][2];

        for(int i=0; i<26; i++) {
            arr[i][0] = -1;
            arr[i][1] = -1;
        }

        for(int i=0; i<s.length(); i++) {
            int temp = s.charAt(i)-'a';
            if(arr[temp][0]==-1) {
                arr[temp][0] = i;
            }
            arr[temp][1] = i;

        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        Map<Integer, Integer> map = new HashMap<>();

        int index = 0;
        for(int i=0; i<26; i++) {
            if(arr[i][0]>=0) {
                if(arr[i][0]<=arr[index][1]) {
                    arr[index][1] = Math.max(arr[index][1], arr[i][1]);
                } else {
                    index = i;
                }
                map.put(arr[index][0], arr[index][1]);
            }
        }

        List<Integer> ans = new ArrayList<>();

        int [][] arr1 = new int[map.size()][2];
        int k = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            arr1[k][0] = entry.getKey();
            arr1[k][1] = entry.getValue();
            k++;
        }
        Arrays.sort(arr1, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for(int i=0; i<arr1.length; i++) {
            ans.add(arr1[i][1]-arr1[i][0]+1);
        }

        return ans;
    }
}

class Solution763_1 {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }
}

// firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
class Solution986 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[] > list = new ArrayList<>();
        for(int i=0,j=0; i<firstList.length && j<secondList.length; ) {
            if(firstList[i][0]> secondList[j][1])
                j++;
            else if(firstList[i][1]<secondList[j][0])
                i++;
            else {
                int min = Math.max(firstList[i][0], secondList[j][0]);
                int max = Math.min(firstList[i][1], secondList[j][1]);
                list.add(new int[]{min, max});
                if(firstList[i][1]<secondList[j][1]) {
                    i++;
                } else {
                    j++;
                }
            }
        }

        return list.toArray(new int[list.size()][2]);
    }
}

class Solution58 {
    public int lengthOfLastWord(String s) {
        String[] ss = s.trim().split(" ");
        return ss[ss.length-1].length();
    }
}

class Solution62 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++)
            dp[i][0] = 1;
        for(int j=0; j<n; j++)
            dp[0][j] = 1;
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}

class Solution63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0]==1)
            return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++) {
            if(obstacleGrid[i][0]==0)
                dp[i][0] = 1;
            else
                break;
        }

        for(int j=0; j<n; j++) {
            if(obstacleGrid[0][j]==0)
                dp[0][j] = 1;
            else
                break;
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(obstacleGrid[i][j]==0)
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                else
                    dp[i][j] = 0;
            }
        }
        return dp[m-1][n-1];
    }
}

class Solution64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i=1; i<m; i++)
            dp[i][0] = grid[i][0] + dp[i-1][0];
        for(int j=1; j<n; j++)
            dp[0][j] = grid[0][j] + dp[0][j-1];
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}

class Solution174 {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m][n];
        for(int i=m-1; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                if(i==m-1 && j==n-1) {
                    dp[i][j] = Math.max(1, 1-dungeon[i][j]);
                } else if(i==m-1) {
                    dp[i][j] = Math.max(1, dp[i][j+1]-dungeon[i][j]);
                } else if(j==n-1) {
                    dp[i][j] = Math.max(1, dp[i+1][j]-dungeon[i][j]);
                } else {
                    dp[i][j] = Math.max(1, Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j]);
                }
            }
        }
        return dp[0][0];
    }
}