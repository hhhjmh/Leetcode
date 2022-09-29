package September;

import java.util.*;

public class the_21 {
    public static void main(String[] args) {
//        new Solution414().thirdMax(new int[]{5,2,2});

//        new Solution413().numberOfArithmeticSlices(new int[]{1,2,3,8,9,10});

//        new Solution416().canPartition(new int[]{2,2,3,5});

        new Solution419().countBattleships(new char[][] {
                {'.', 'X', '.', '.', 'X'},
                {'.', 'X', '.', '.', 'X'},
                {'.', '.', '.', '.', 'X'},
                {'X', '.', 'X', 'X', '.'},
                {'X', '.', '.', '.', 'X'},
        });

    }
}


class Solution854 {
    int res = Integer.MAX_VALUE;
    public int kSimilarity(String s1, String s2) {
        return DFS(s1.toCharArray(), s2.toCharArray(), 0, 0);
    }

    private int DFS(char[] sc1, char[] sc2, int start, int current) {
        if(current>=res) return res;
        if(start == sc1.length-1) return res = Math.min(res, current);

        for(int i=start; i<sc1.length; i++) {
            if(sc1[i]!=sc2[i]) {
                for(int j=i+1; j<sc1.length; j++) {
                    if(sc2[j]==sc1[i] && sc1[j]!=sc2[j]) {
                        swap(sc2, i, j);
                        DFS(sc1, sc2, i+1, current+1);
                        swap(sc2, i, j);
                    }
                }
                return res;
            }
        }

        return res = Math.min(res, current);
    }

    private void swap(char[] sc2, int i, int j) {
        char temp = sc2[i];
        sc2[i] = sc2[j];
        sc2[j] = temp;
    }
}

class Solution413 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int ans = 0;
        if(n<3)
            return 0;
        for(int i=0; i<n-2; i++) {
            int count = 0;
            for(int j=i+2; j<n; j++) {
                if((nums[j]+nums[j-2]) == (nums[j-1]*2))
                    count++;
                else
                    break;
            }
            ans += count;
        }
        return ans;
    }
}

class Solution413_1 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int d = nums[0] - nums[1], t = 0;
        int ans = 0;
        // 因为等差数列的长度至少为 3，所以可以从 i=2 开始枚举
        for (int i = 2; i < n; ++i) {
            if (nums[i - 1] - nums[i] == d) {
                ++t;
            } else {
                d = nums[i - 1] - nums[i];
                t = 0;
            }
            ans += t;
        }
        return ans;
    }
}

class Solution414 {
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int count = 1, max = nums[nums.length-1];
        if(nums.length<3)
            return max;
        for(int i=nums.length-2; i>=0; i--) {
            if(nums[i]!=nums[i+1]) {
                count++;
            }
            if(count==3)
                return nums[i];
        }
        return max;
    }
}

class Solution414_1 {
    public int thirdMax(int[] nums) {
        long a = Long.MIN_VALUE, b = Long.MIN_VALUE, c = Long.MIN_VALUE;
        for (long num : nums) {
            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (a > num && num > b) {
                c = b;
                b = num;
            } else if (b > num && num > c) {
                c = num;
            }
        }
        return c == Long.MIN_VALUE ? (int) a : (int) c;
    }
}

class Solution416 {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }
}

class Solution417 {
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] heights;
    int m, n;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(i, 0, pacific);
        }
        for (int j = 1; j < n; j++) {
            dfs(0, j, pacific);
        }
        for (int i = 0; i < m; i++) {
            dfs(i, n - 1, atlantic);
        }
        for (int j = 0; j < n - 1; j++) {
            dfs(m - 1, j, atlantic);
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> cell = new ArrayList<Integer>();
                    cell.add(i);
                    cell.add(j);
                    result.add(cell);
                }
            }
        }
        return result;
    }

    public void dfs(int row, int col, boolean[][] ocean) {
        if (ocean[row][col]) {
            return;
        }
        ocean[row][col] = true;
        for (int[] dir : dirs) {
            int newRow = row + dir[0], newCol = col + dir[1];
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && heights[newRow][newCol] >= heights[row][col]) {
                dfs(newRow, newCol, ocean);
            }
        }
    }
}

class Solution419 {
    public int countBattleships(char[][] board) {
        int ans = 0, m = board.length, n = board[0].length;
        boolean[][] flag = new boolean[m][n];
        int dir = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(flag[i][j]) {
                    continue;
                }
                flag[i][j] = true;
                if(board[i][j]=='X') {
                    ans++;
                    if(DFS(board, i+1, j))
                        dir = 1;
                    else if(DFS(board, i, j+1))
                        dir = 2;
                    if(dir==1) {
                        int k = i;
                        while (k<m) {
                            if(board[k][j]=='X')
                                flag[k][j] = true;
                            else
                                break;
                            k++;
                        }
                    } else if(dir==2) {
                        int k = j;
                        while (k<n) {
                            if(board[i][k]=='X')
                                flag[i][k] = true;
                            else
                                break;
                            k++;
                        }
                    }
                }
                dir = 0;
            }
        }
        return ans;
    }

    private boolean DFS(char[][] board, int i, int j) {
        if(i<0 || j<0 || i>=board.length || j>=board[0].length)
            return false;
        if(board[i][j]=='X')
            return true;
        return false;
    }
}

class Solution419_1 {
    public int countBattleships(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int ans = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == 'X') {
                    board[i][j] = '.';
                    for (int k = j + 1; k < col && board[i][k] == 'X'; ++k) {
                        board[i][k] = '.';
                    }
                    for (int k = i + 1; k < row && board[k][j] == 'X'; ++k) {
                        board[k][j] = '.';
                    }
                    ans++;
                }
            }
        }
        return ans;
    }
}

