package September;

import java.util.*;

public class the_6 {
    public static void main(String[] args) {
        new Solution198().rob(new int[]{2,7,9,3,1});
    }
}


class Solution828 {
    public int uniqueLetterString(String s) {
        int ans = 0;
        int[] tmp0 = new int[26]; //存储某一个字符前一个字符所在位置
        int[] tmp1 = new int[26]; //存储某个字符当前所处位置

        Arrays.fill(tmp0, -1);
        Arrays.fill(tmp1, -1);

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char now = chars[i];
            int index = now - 'A';
            if (tmp1[index] > -1) {
                ans += (i - tmp1[index]) * (tmp1[index] - tmp0[index]);
            }
            tmp0[index] = tmp1[index];
            tmp1[index] = i;
        }
        for (int i = 0; i < 26; i++) {
            if (tmp1[i] > -1) {
                ans += (tmp1[i] - tmp0[i]) * (s.length() - tmp1[i]);
            }
        }
        return ans;
    }
}

class Solution187 {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<=s.length()-10; i++) {
            String temp = s.substring(i, i+10);
            map.put(temp, map.getOrDefault(temp, 0) + 1);
            if(map.get(temp)==2)
                ans.add(temp);
        }
        return ans;
    }
}


class Solution189 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int [] arrs = new int[n];
        for(int i=0; i<n; i++) {
            arrs[(i+k)%n] = nums[i];
        }
        for(int i=0; i<n; i++) {
            nums[i] = arrs[i];
        }
    }
}

class Solution189_1 {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }
}

class Solution198 {
    public int rob(int[] nums) {
        int ans = 0;
        int n = nums.length;
        if(n==1)
            return nums[0];
        if(n==2)
            return Math.max(nums[0], nums[1]);
        int [] dp = new int[n];
        for(int i=0; i<n; i++) {
            if(i<2) {
                dp[i] = nums[i];
            } else {
                dp[i] = Math.max( dp[i-1]-nums[i-1], dp[i-2]) + nums[i];
            }
        }
        return Math.max(dp[n-1], dp[n-2]);
    }
}

class Solution199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if(root==null)
            return ans;
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for(int i=0; i<n; i++) {
                TreeNode temp = queue.poll();
                if(i==0)
                    ans.add(temp.val);
                if(temp.right!=null) queue.add(temp.right);
                if(temp.left!=null) queue.add(temp.left);
            }
        }
        return ans;
    }
}

class Solution200 {
    int m, n;
    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        boolean [][] flag = new boolean[m][n];

        int count = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j]=='1'&& !flag[i][j]) {
                    DFS(grid, flag, i, j);
                    count++;
                }
            }
        }
        return count++;
    }

    private void DFS(char[][] grid, boolean[][] flag, int i, int j) {
        if(i<0 || j<0 || i>=m || j>=n)
            return;
        if(grid[i][j]=='1'&& !flag[i][j]) {
            flag[i][j] = true;
            DFS(grid, flag, i+1, j);
            DFS(grid, flag, i-1, j);
            DFS(grid, flag, i, j+1);
            DFS(grid, flag, i, j-1);
        }

    }
}
