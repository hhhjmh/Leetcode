package September;

import java.util.*;

public class the_29 {
    public static void main(String[] args) {
        new Solution498().findDiagonalOrder(new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        });
    }
}

// 493
/*class Solution {
    class BIT {
        int[] tree;
        int n;

        public BIT(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        public int lowbit(int x) {
            return x & (-x);
        }

        public void update(int x, int d) {
            while (x <= n) {
                tree[x] += d;
                x += lowbit(x);
            }
        }

        public int query(int x) {
            int ans = 0;
            while (x != 0) {
                ans += tree[x];
                x -= lowbit(x);
            }
            return ans;
        }
    }

    public int reversePairs(int[] nums) {
        Set<Long> allNumbers = new TreeSet<Long>();
        for (int x : nums) {
            allNumbers.add((long) x);
            allNumbers.add((long) x * 2);
        }
        // 利用哈希表进行离散化
        Map<Long, Integer> values = new HashMap<Long, Integer>();
        int idx = 0;
        for (long x : allNumbers) {
            values.put(x, idx);
            idx++;
        }

        int ret = 0;
        BIT bit = new BIT(values.size());
        for (int i = 0; i < nums.length; i++) {
            int left = values.get((long) nums[i] * 2), right = values.size() - 1;
            ret += bit.query(right + 1) - bit.query(left + 1);
            bit.update(values.get((long) nums[i]) + 1, 1);
        }
        return ret;
    }
}*/

class Solution494 {
    int ret = 0;
    int len;
    public int findTargetSumWays(int[] nums, int target) {
        len = nums.length;
        DFS(nums, 0, 0, target, len);
        return ret;
    }

    private void DFS(int[] nums, int sum, int index, int target, int len) {
        if(index==len) {
            if(target==sum)
                ret++;
            return;
        }
        DFS(nums, sum+nums[index], index+1, target, len);
        DFS(nums, sum-nums[index], index+1, target, len);
    }
}

class Solution494_1 {
    public int findTargetSumWays(int[] nums, int t) {
        int n = nums.length;
        int s = 0;
        for (int i : nums) s += Math.abs(i);
        if (Math.abs(t) > s) return 0;
        int[][] f = new int[n + 1][2 * s + 1];
        f[0][0 + s] = 1;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = -s; j <= s; j++) {
                if ((j - x) + s >= 0) f[i][j + s] += f[i - 1][(j - x) + s];
                if ((j + x) + s <= 2 * s) f[i][j + s] += f[i - 1][(j + x) + s];
            }
        }
        return f[n][t + s];
    }
}

// 497
/*
class Solution {
    int[][] rs;
    int[] sum;
    int n;
    Random random = new Random();
    public Solution(int[][] rects) {
        rs = rects;
        n = rs.length;
        sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + (rs[i - 1][2] - rs[i - 1][0] + 1) * (rs[i - 1][3] - rs[i - 1][1] + 1);
    }
    public int[] pick() {
        int val = random.nextInt(sum[n]) + 1;
        int l = 0, r = n;
        while (l < r) {
            int mid = l + r >> 1;
            if (sum[mid] >= val) r = mid;
            else l = mid + 1;
        }
        int[] cur = rs[r - 1];
        int x = random.nextInt(cur[2] - cur[0] + 1) + cur[0], y = random.nextInt(cur[3] - cur[1] + 1) + cur[1];
        return new int[]{x, y};
    }
}*/

class Solution498 {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] ans = new int[m*n];
        int k = 0;
        for(int j=0; j<n; j++) {
            int x = 0, y = j, k1 = k;
            while (y>=0 && x<m) {
                ans[k] = mat[x][y];
                x++; y--; k++;
            }
            if(j%2==0) {
                reverse(ans, k1, k-1);
            }
        }

        for(int i=1; i<m; i++) {
            int x = i, y = n-1, k1 = k;
            while (y>=0 && x<m) {
                ans[k] = mat[x][y];
                x++; y--; k++;
            }
            if((x+y)%2==0) {
                reverse(ans, k1, k-1);
            }
        }
        return ans;
    }

    private void reverse(int[] ans, int i, int j) {
        while (i<j) {
            int temp = ans[i];
            ans[i] = ans[j];
            ans[j] = temp;
            i++; j--;
        }
    }
}