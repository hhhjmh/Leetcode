package August;

import java.util.*;

public class the_21 {
    public static void main(String[] args) {
        /*new Solution51().check(new boolean[][] {
                {false,true,false,false},
                {false,false,false,true},
                {true,false,false,false},
                {false,false,true,false}
        });*/

//        new Solution51().solveNQueens(8);

//        new Solution53().maxSubArray(new int[]{5,4,-1,7,8});

//        new Solution121().maxProfit(new int[]{7,6,4,3,1});

//        new Solution152().maxProduct(new int[]{3,-1,4});

//        new Solution697().findShortestSubArray(new int[]{1,2,2,3,1});

        new Solution978().maxTurbulenceSize(new int[]{100});

    }
}

class Solution1455 {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String [] ss = sentence.split(" ");
        for(int i=0; i<ss.length; i++) {
            if(ss[i].indexOf(searchWord)==0)
                return i+1;
        }
        return -1;
    }
}


class Solution51 {
    List<List<String>> res = new ArrayList<>();
    int len;
    public List<List<String>> solveNQueens(int n) {
        len = n;
        boolean[][] visited = new boolean[n][n];
        backTrack(visited, 0);

        return res;
    }

    private void backTrack(boolean[][] visited, int index) {
        if(index==len){
            List<String> list = new ArrayList<>();
            for(int i=0; i<len; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j=0; j<len; j++) {
                    if(visited[i][j])
                        sb.append('Q');
                    else
                        sb.append('.');
                }
                list.add(sb.toString());
            }
            res.add(list);
        }
        for(int i=0; i<len && index<len; i++) {
            visited[index][i]=true;
            if(check(visited))
                backTrack(visited, index+1);
            visited[index][i]=false;
        }
    }


    public boolean check(boolean[][] visited) {
        int n = visited.length;
        for(int i=0; i<n; i++) {
            int count1 = 0, count2 = 0;
            for(int j=0; j<n; j++) {
                if(visited[i][j])
                    count1++;
                if(visited[j][i])
                    count2++;
                if(count1>1 || count2>1)
                    return false;
            }
        }

        for(int i=0; i<n; i++) {
            int count1 = 0, count2 = 0;
            for(int j=0; j<=i; j++) {
                if(visited[i-j][j])
                    count1++;
                if(visited[n-1-i+j][n-1-j])
                    count2++;
                if(count1>1 || count2>1)
                    return false;
            }
        }

        for(int i=n-1; i>=0; i--) {
            int count1 = 0, count2 = 0;
            for(int j=i; j<n; j++) {
                if(visited[j][j-i])
                    count1++;
                if(visited[j-i][j])
                    count2++;
                if(count1>1 || count2>1)
                    return false;
            }
        }

        return true;
    }
}

class Solution52 {
    List<List<String>> res = new ArrayList<>();
    int len;
    public int totalNQueens(int n) {
        len = n;
        boolean[][] visited = new boolean[n][n];
        backTrack(visited, 0);

        return res.size();
    }

    private void backTrack(boolean[][] visited, int index) {
        if(index==len){
            List<String> list = new ArrayList<>();
            for(int i=0; i<len; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j=0; j<len; j++) {
                    if(visited[i][j])
                        sb.append('Q');
                    else
                        sb.append('.');
                }
                list.add(sb.toString());
            }
            res.add(list);
        }
        for(int i=0; i<len && index<len; i++) {
            visited[index][i]=true;
            if(check(visited))
                backTrack(visited, index+1);
            visited[index][i]=false;
        }
    }


    public boolean check(boolean[][] visited) {
        int n = visited.length;
        for(int i=0; i<n; i++) {
            int count1 = 0, count2 = 0;
            for(int j=0; j<n; j++) {
                if(visited[i][j])
                    count1++;
                if(visited[j][i])
                    count2++;
                if(count1>1 || count2>1)
                    return false;
            }
        }

        for(int i=0; i<n; i++) {
            int count1 = 0, count2 = 0;
            for(int j=0; j<=i; j++) {
                if(visited[i-j][j])
                    count1++;
                if(visited[n-1-i+j][n-1-j])
                    count2++;
                if(count1>1 || count2>1)
                    return false;
            }
        }

        for(int i=n-1; i>=0; i--) {
            int count1 = 0, count2 = 0;
            for(int j=i; j<n; j++) {
                if(visited[j][j-i])
                    count1++;
                if(visited[j-i][j])
                    count2++;
                if(count1>1 || count2>1)
                    return false;
            }
        }

        return true;
    }
}

class Solution1001_2 {
    int[][] dirs = new int[][]{{0,0},{0,-1},{0,1},{-1,0},{-1,-1},{-1,1},{1,0},{1,-1},{1,1}};
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        long N = n;
        Map<Integer, Integer> row = new HashMap<>(), col = new HashMap<>();
        Map<Integer, Integer> left = new HashMap<>(), right = new HashMap<>();
        Set<Long> set = new HashSet<>();
        for (int[] l : lamps) {
            int x = l[0], y = l[1];
            int a = x + y, b = x - y;
            if (set.contains(x * N + y)) continue;
            increment(row, x); increment(col, y);
            increment(left, a); increment(right, b);
            set.add(x * N + y);
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int x = q[0], y = q[1];
            int a = x + y, b = x - y;
            if (row.containsKey(x) || col.containsKey(y) || left.containsKey(a) || right.containsKey(b)) ans[i] = 1;

            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                int na = nx + ny, nb = nx - ny;
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (set.contains(nx * N + ny)) {
                    set.remove(nx * N + ny);
                    decrement(row, nx); decrement(col, ny);
                    decrement(left, na); decrement(right, nb);
                }
            }
        }
        return ans;
    }
    void increment(Map<Integer, Integer> map, int key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }
    void decrement(Map<Integer, Integer> map, int key) {
        if (map.get(key) == 1) map.remove(key);
        else map.put(key, map.get(key) - 1);
    }
}

// [-2,1,-3,4,-1,2,1,-5,4]
class Solution53 {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];
        for(int i = 1; i<nums.length; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }
}

//[7,1,5,3,6,4]
class Solution121 {
    public int maxProfit(int[] prices) {
        int min = prices[0], ans = 0;
        for(int i=1; i<prices.length; i++) {
            ans = Math.max(ans, prices[i]-min);
            min = Math.min(min, prices[i]);
        }
        return ans;
    }
}

// [-3,-2,-1,2,3,4]
class Solution152 {
    public int maxProduct(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }
}

class Solution697 {
    public int findShortestSubArray(int[] nums) {
        int count = 0;
        int[] arrs = new int[50000];
        for(int num : nums) {
            arrs[num]++;
            count = Math.max(count, arrs[num]);
        }

        int len = Integer.MAX_VALUE;

        for(int i=0; i<arrs.length; i++) {
            if(count==arrs[i]) {
                for(int left=0,right=nums.length-1; left<=right; ) {
                    if(nums[left]!=i)
                        left++;
                    if(nums[right]!=i)
                        right--;
                    if(nums[right]==i && nums[left]==i) {
                        len = Math.min(len, right-left+1);
                        break;
                    }
                }
            }
        }

        return len;
    }
}

class Solution978 {
    public int maxTurbulenceSize(int[] arr) {
        boolean up = false;
        int res = 1;
        if(arr.length==1)
            return 1;
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for(int i=1; i<arr.length; i++) {
            if(arr[i]==arr[i-1]) {
                dp[i] = 1;
            } else {
                if(dp[i-1]==1) {
                    dp[i] = 2;
                } else {
                    if( (arr[i]>arr[i-1] && !up) || (arr[i]<arr[i-1] && up) )
                        dp[i] = dp[i-1] + 1;
                    else
                        dp[i] = 2;
                }
                if(arr[i]>arr[i-1])
                    up = true;
                else
                    up = false;
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
